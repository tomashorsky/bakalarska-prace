package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractResolver<R, T extends InfluenceOperation> {
    private static final int BASE_IMPACT = 10;
    private static final int BONUS_IMPACT_DIVIDER = 9;
    private static final int MAX_TRUST_IN_STATE = 71;
    private static final float TRUST_IN_STATE_IMPACT_PERCENT = 0.15f;

    protected final CountryRepository countryRepository;
    protected final OpinionSupportRepository opinionSupportRepository;

    public abstract int resolveExecution(R operation, Team team, Country country);

    protected abstract int calculateImpactByType(R operation, Team team, Country country);

    protected void recordImpact(Country country, T operation, Team team) {
        OpinionSupport support = opinionSupportRepository.findByCountryIdAndTeamId(country.getId(), team.getId());

        support.setSupportersCount(support.getSupportersCount() + operation.getImpact());
        opinionSupportRepository.save(support);

        country.setTrustInState(country.getTrustInState() - operation.getTrustInStateImpact());
        countryRepository.save(country);
    }

    protected float calculateTrustInStateImpact(Country country) {
        return country.getTrustInState() * TRUST_IN_STATE_IMPACT_PERCENT;
    }

    protected int calculateImpactNegative(int statValue) {
        return calculateImpact(100 - statValue);
    }

    protected int calculateImpact(int statValue) {
        if (statValue <= 0) {
            return BASE_IMPACT;
        }
        return BASE_IMPACT + statValue / BONUS_IMPACT_DIVIDER;
    }

    protected int getNormalizedOpinionSupporters(Country country, Team team) {
        OpinionSupport opinionSupport = opinionSupportRepository.findByCountryIdAndTeamId(country.getId(), team.getId());
        float supporters = opinionSupport.getSupportersCount();
        if (supporters <= 0) {
            return 0;
        }
        float res = (supporters / BASE_IMPACT) * 3f;
        if (res > 100) {
            return 100;
        }
        return Math.round(res);
    }

    protected int getNormalizedTrustInState(Country country) {
        float trustInState = country.getTrustInState();
        float constant = 100f / MAX_TRUST_IN_STATE;
        return Math.round(trustInState * constant);
    }
}
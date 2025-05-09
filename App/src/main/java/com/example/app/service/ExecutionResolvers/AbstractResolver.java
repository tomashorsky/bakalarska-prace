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
    private static final float TRUST_IN_STATE_IMPACT_PERCENT = 0.012f;
    private static final int OPINION_SUPPORT_THRESHOLD_LOW = 350;
    private static final int OPINION_SUPPORT_THRESHOLD_HIGH = 500;

    protected final CountryRepository countryRepository;
    protected final OpinionSupportRepository opinionSupportRepository;

    /**
     * Resolves the execution of an operation based on the type of operation and the country.
     *
     * @param operation The operation to be executed.
     * @param team      The team executing the operation.
     * @param country   The country affected by the operation.
     * @return The result of the execution.
     */
    public abstract int resolveExecution(R operation, Team team, Country country);

    /**
     * Calculates the impact of the operation based on the type of operation and the country.
     *
     * @param operation The operation to be executed.
     * @param team      The team executing the operation.
     * @param country   The country affected by the operation.
     * @return The calculated impact.
     */
    protected abstract int calculateImpactByType(R operation, Team team, Country country);

    /**
     * Records the impact of the operation on the country and opinion support.
     *
     * @param country   The country affected by the operation.
     * @param operation The operation to be executed.
     * @param team      The team executing the operation.
     */
    protected void recordImpact(Country country, T operation, Team team) {
        OpinionSupport support = opinionSupportRepository.findByCountryIdAndTeamId(country.getId(), team.getId());

        support.setSupportersCount(support.getSupportersCount() + operation.getImpact());
        opinionSupportRepository.save(support);

        country.setTrustInState(country.getTrustInState() - operation.getTrustInStateImpact());
        countryRepository.save(country);
    }

    protected int calculateImpactNegative(int opinionSupport, int statValue) {
        return calculateImpact(opinionSupport, 100 - statValue);
    }

    /**
     * Calculates the impact as a base impact plus a bonus impact.
     * Bonus impact is calculated by dividing the stat value by a constant.
     * High opinion support will lower the impact.
     *
     * @param opinionSupport The opinion support value to calculate the impact for.
     * @param statValue      The stat value to calculate the impact for. Should be between 0 and 100.
     * @return The calculated impact.
     */
    protected int calculateImpact(int opinionSupport, int statValue) {
        int res;
        if (statValue <= 0) {
            res = BASE_IMPACT;
        } else {
            res = BASE_IMPACT + statValue / BONUS_IMPACT_DIVIDER;
        }

        // Lower the impact based on opinion support thresholds
        if (opinionSupport > OPINION_SUPPORT_THRESHOLD_HIGH) {
            return res / 2;
        }
        if (opinionSupport > OPINION_SUPPORT_THRESHOLD_LOW) {
            return (res * 2) / 3;
        }

        return res;
    }

    /**
     * Calculates the trust in state impact.
     * Its calculated as a small percentage of the country's trust in state.
     *
     * @param country The country affected by the operation.
     * @return The calculated trust in state impact.
     */
    protected float calculateTrustInStateImpact(Country country) {
        return country.getTrustInState() * TRUST_IN_STATE_IMPACT_PERCENT;
    }

    /**
     * Normalizes the opinion supporters count to a number between 0 and 100.
     *
     * @param country The country affected by the operation.
     * @param team    The team executing the operation.
     * @return The normalized opinion supporters count.
     */
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

    /**
     * Normalizes the trust in state to a number between 0 and 100.
     *
     * @param country The country affected by the operation.
     * @return The normalized trust in state.
     */
    protected int getNormalizedTrustInState(Country country) {
        float trustInState = country.getTrustInState();
        float constant = 100f / MAX_TRUST_IN_STATE;
        return Math.round(trustInState * constant);
    }
}
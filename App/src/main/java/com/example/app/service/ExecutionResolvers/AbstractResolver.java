package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractResolver<R ,T extends InfluenceOperation> {
    protected final CountryRepository countryRepository;
    protected final OpinionSupportRepository opinionSupportRepository;

    public abstract int resolveExecution(R operation, Team team, Country country);
    protected abstract int calculateImpact(R operation, Country country);

    protected void recordImpact(Country country, T operation, Team team) {
        OpinionSupport support =  country.getOpinionSupportByTeamId(team.getId());

        support.setSupportersCount(support.getSupportersCount() + operation.getImpact());
        opinionSupportRepository.save(support);

        country.setTrustInState(country.getTrustInState() - operation.getTrustInStateImpact());
        countryRepository.save(country);
    }

    protected float calculateTrustInStateImpact(T operation, Country country) {
        //TODO: implement calculation based on country trustInState
       return operation.getImpact() / 100f;
    }

}
//TODO: do helper class instead of abstract class?
//TODO: R should have some common interface?
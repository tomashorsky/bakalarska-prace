package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractResolver<T extends InfluenceOperation> {
    protected final CountryRepository countryRepository;
    protected final OpinionSupportRepository opinionSupportRepository;


    public void recordImpact(Country country, T operation, Team team) {
        OpinionSupport support =  country.getOpinionSupportByTeamId(team.getId());

        support.setSupportersCount(support.getSupportersCount() + operation.getImpact());
        opinionSupportRepository.save(support);

        country.setTrustInState(country.getTrustInState() - operation.getTrustInStateImpact());
        countryRepository.save(country);
    }

    public float calculateTrustInStateImpact(T operation, Country country) {
       return operation.getImpact() / 100f;
    }

}
//TODO: do helper class instead of abstract class?
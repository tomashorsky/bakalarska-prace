package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.ActionRepository;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionResolver extends AbstractResolver<Action, Action> {
    private final ActionRepository actionRepository;

    public ActionResolver(CountryRepository countryRepository, OpinionSupportRepository opinionSupportRepository, ActionRepository actionRepository) {
        super(countryRepository, opinionSupportRepository);
        this.actionRepository = actionRepository;
    }

    @Override
    public int resolveExecution(Action action, Team team, Country country) {
        int impact = calculateImpact(action, country);

        action.setName(action.getType().getName());
        action.setTeam(team);
        action.setCountry(country);
        action.setImpact(impact);
        action.setTrustInStateImpact(calculateTrustInStateImpact(action, country));
        actionRepository.save(action);

       return impact;
    }

    protected int calculateImpact(Action action, Country country) {
        return switch (action.getType()) {
            case DISINFORMATION -> calculateDisinformationImpact(country);
            case MICRO_TARGETING -> calculateMicroTargetingImpact(country);
            case ASTROTURFING -> calculateAstroturfingImpact(country);
            case ECHO_CHAMBERS -> calculateEchoChambersImpact(country);
            case MALINFORMATIONS -> calculateMalinformationsImpact(country);
        };
    }

    private int calculateDisinformationImpact(Country country) {
        return 1;
    }

    private int calculateMicroTargetingImpact(Country country) {
        return 2;
    }

    private int calculateAstroturfingImpact(Country country) {
        return 3;
    }

    private int calculateEchoChambersImpact(Country country) {
        return 4;
    }

    private int calculateMalinformationsImpact(Country country) {
        return 5;
    }
}

package cz.cvut.fit.service.ExecutionResolvers;

import cz.cvut.fit.data.entity.*;
import cz.cvut.fit.data.repository.ActionRepository;
import cz.cvut.fit.data.repository.CountryRepository;
import cz.cvut.fit.data.repository.OpinionSupportRepository;
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
        int impact = calculateImpactByType(action, team, country);

        action.setName(action.getType().getLabel());
        action.setTeam(team);
        action.setCountry(country);
        action.setImpact(impact);
        action.setTrustInStateImpact(calculateTrustInStateImpact(country));
        actionRepository.save(action);

        recordImpact(country, action, team);
        return impact;
    }

    protected int calculateImpactByType(Action action, Team team, Country country) {
        int economy = country.getEconomy().getValue();
        int mediaLiteracy = country.getMediaLiteracy().getValue();
        int trustInState = getNormalizedTrustInState(country);
        int opinionSupport = opinionSupportRepository.findByCountryIdAndTeamId(country.getId(), team.getId()).getSupportersCount();
        int opinionSupportNorm = getNormalizedOpinionSupporters(country, team);

        return switch (action.getType()) {
            case DISINFORMATION -> calculateImpact(opinionSupport, economy);
            case MICRO_TARGETING -> calculateImpactNegative(opinionSupport, mediaLiteracy);
            case ASTROTURFING -> calculateImpactNegative(opinionSupport, opinionSupportNorm);
            case ECHO_CHAMBERS -> calculateImpactNegative(opinionSupport, economy);
            case MALINFORMATIONS -> calculateImpact(opinionSupport, trustInState);
        };
    }
}

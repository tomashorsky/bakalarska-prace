package com.example.app.service;

import com.example.app.config.GameMessageProperties;
import com.example.app.entity.Action;
import com.example.app.entity.Country;
import com.example.app.entity.Team;
import com.example.app.enums.ActionType;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.TeamRepository;
import com.example.app.service.ExecutionResolvers.ActionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;


@Service
@RequiredArgsConstructor
public class ActionService {
    private final TeamRepository teamRepository;
    private final CountryRepository countryRepository;
    private final ActionResolver actionResolver;
    private final GameMessageProperties gameMessages;

    /**
     * Executes an action for a given team and country, using ActionResolver.
     *
     * @param teamId    the ID of the team
     * @param countryId the ID of the country
     * @param actionType the type of action to execute
     * @return a message indicating the result of the action execution
     */
    @Transactional
    public String executeAction(Long teamId, Long countryId, String actionType) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new IllegalArgumentException("Country not found"));

        Action action = new Action();
        action.setType(ActionType.valueOf(actionType));
        int result = actionResolver.resolveExecution(action, team, country);
        return MessageFormat.format(gameMessages.getActionSuccess(), country.getName(), result);
    }
}

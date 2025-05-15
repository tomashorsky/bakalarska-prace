package cz.cvut.fit.presentation.controller;

import cz.cvut.fit.service.dto.CountryDTO;
import cz.cvut.fit.service.dto.TeamDTO;
import cz.cvut.fit.data.enums.ActionType;
import cz.cvut.fit.service.ActionService;
import cz.cvut.fit.service.CountryService;
import cz.cvut.fit.service.GameService;
import cz.cvut.fit.service.TeamService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class ActionController {
    private final ActionService actionService;
    private final TeamService teamService;
    private final CountryService countryService;
    private final GameService gameService;


    @GetMapping("/teams/{teamId}/actions")
    public String getActions(@PathVariable Long teamId, Model model) {
        TeamDTO team = teamService.getTeam(teamId);
        List<CountryDTO> countries = countryService.getAllCountriesFromGame(team.getGameId());
        Boolean gameInProgress = gameService.isGameInProgress(team.getGameId());

        model.addAttribute("actionTypes", ActionType.values());
        model.addAttribute("team", team);
        model.addAttribute("countries", countries);
        model.addAttribute("gameInProgress", gameInProgress);
        return "actions/execute";
    }

    @PostMapping("/teams/{teamId}/actions/execute")
    public String executeAction(
            @PathVariable Long teamId,
            @RequestParam Long countryId,
            @RequestParam String actionType,
            RedirectAttributes redirectAttributes
    ) {
        String message = actionService.executeAction(teamId, countryId, actionType);

        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/teams/" + teamId;
    }
}


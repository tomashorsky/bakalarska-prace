package cz.cvut.fit.presentation.controller;

import cz.cvut.fit.service.dto.TeamDTO;
import cz.cvut.fit.service.TeamService;
import org.springframework.ui.Model;
import cz.cvut.fit.service.dto.GameDTO;
import cz.cvut.fit.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final GameService gameService;

    @GetMapping("teams/select")
    public String selectTeam(@RequestParam Long gameId, Model model) {
        GameDTO game = gameService.getGame(gameId);
        List<TeamDTO> teams = teamService.getAllTeamsFromGame(gameId);

        model.addAttribute("game", game);
        model.addAttribute("teams", teams);
        return "teams/select";
    }


    @GetMapping("teams/{id}")
    public String getTeamDetail(@PathVariable Long id, Model model) {
        TeamDTO team = teamService.getTeam(id);
        model.addAttribute("team", team);
        return "teams/detail";
    }
}

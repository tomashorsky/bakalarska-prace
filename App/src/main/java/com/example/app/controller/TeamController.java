package com.example.app.controller;

import com.example.app.dto.TeamDTO;
import com.example.app.service.TeamService;
import org.springframework.ui.Model;
import com.example.app.dto.GameDTO;
import com.example.app.service.GameService;
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

package com.example.app.controller;

import com.example.app.dto.CountryDTO;
import com.example.app.dto.TeamDTO;
import com.example.app.service.CountryService;
import com.example.app.service.TeamService;
import com.example.app.enums.GamePhase;
import org.springframework.ui.Model;
import com.example.app.dto.GameDTO;
import com.example.app.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final TeamService teamService;
    private final CountryService countryService;

    @GetMapping("admin/games")
    public String getAllGames(Model model) {
        List<GameDTO> games = gameService.getAllGames();

        model.addAttribute("games", games);
        model.addAttribute("title", "Games");
        return "games/list";
    }

    @GetMapping("admin/games/{id}")
    public String getGameDetail(@PathVariable Long id, Model model) {
        GameDTO game = gameService.getGame(id);
        List<TeamDTO> teams = teamService.getAllTeamsFromGame(id);
        List<CountryDTO> countries = countryService.getAllCountriesFromGame(id);

        model.addAttribute("game", game);
        model.addAttribute("teams", teams);
        model.addAttribute("countries", countries);
        return "games/detail";
    }

    @GetMapping("admin/games/new")
    public String showCreateForm(Model model) {
        GameDTO game =  GameDTO.builder().numberOfCountries(4).build();

        model.addAttribute("game",game);
        return "games/add";
    }

    @PostMapping("admin/games")
    public String createGame(@ModelAttribute GameDTO gameDTO) {
        GameDTO createdGame = gameService.createGame(gameDTO);
        return "redirect:/admin/games/" + createdGame.getId();
    }

    //TODO: is it ok to use post instead of patch and delete?

    @PostMapping("admin/games/{id}/phase")
    public String changeGamePhase(@PathVariable Long id, @RequestParam GamePhase phase) {
        gameService.changeGamePhase(id, phase);
        return "redirect:/admin/games/" + id;
    }

    @PostMapping("admin/games/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return "redirect:/admin/games";
    }

}

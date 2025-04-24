package com.example.app.controller;

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

    @GetMapping("admin/games")
    public String getAllGames(Model model) {
        List<GameDTO> games = gameService.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("title", "Games");

        return "games/list";
    }

    @GetMapping("admin/games/{id}")
    public String getGame(@PathVariable Long id, Model model) {
        GameDTO game = gameService.getGame(id);
        model.addAttribute("game", game);
        model.addAttribute("title", "Game Details");

        return "games/detail";
    }

    @GetMapping("admin/games/new")
    public String showCreateForm(Model model) {
        GameDTO game =  GameDTO.builder().numberOfCountries(4).build();
        model.addAttribute("game",game);
        model.addAttribute("title", "Create New Game");
        return "games/add";
    }


    @PostMapping("admin/games")
    public String createGame(@ModelAttribute GameDTO gameDTO) {
        GameDTO createdGame = gameService.createGame(gameDTO);
        return "redirect:/admin/games/" + createdGame.getId();
    }

    @PostMapping("admin/games/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return "redirect:/admin/games";
    }

}

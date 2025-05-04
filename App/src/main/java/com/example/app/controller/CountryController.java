package com.example.app.controller;

import com.example.app.dto.CountryDTO;
import com.example.app.dto.TeamDTO;
import com.example.app.service.CountryService;
import com.example.app.service.TeamService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final TeamService teamService;

    @GetMapping("/teams/{teamId}/countries")
    public String getCountries(@PathVariable Long teamId, Model model) {
        TeamDTO team = teamService.getTeam(teamId);
        List<CountryDTO> countries = countryService.getAllCountriesDetailFromGame(team.getGameId());

        model.addAttribute("countries", countries);
        model.addAttribute("team", team);
        return "countries/list";
    }
}

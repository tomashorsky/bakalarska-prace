package com.example.app.controller;

import com.example.app.dto.CountryDTO;
import com.example.app.service.CountryService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("games/{gameId}/countries")
    public String getCountries(@PathVariable Long gameId, Model model) {
        List<CountryDTO> countries = countryService.getAllCountriesFromGame(gameId);
        model.addAttribute("countries", countries);
        return "countries/list";
    }
}

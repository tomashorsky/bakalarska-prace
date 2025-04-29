package com.example.app.service;


import com.example.app.config.GameDefaultsProperties;
import com.example.app.entity.Game;
import com.example.app.entity.OpinionRate;
import com.example.app.entity.Team;
import com.example.app.entity.Country;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionRateRepository;
import com.example.app.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameInitializer {

    private final CountryRepository countryRepository;
    private final TeamRepository teamRepository;
    private final OpinionRateRepository opinionRateRepository;
    private final GameDefaultsProperties gameDefaults;


    public void initializeGame(Game game) {
        initializeCountries(game);
        initializeTeams(game);
        initializeOpinionsRates(game);
    }

    /**
     * Initializes the countries for the game based on the game defaults.
     * @param game The game instance to initialize countries for.
     */
    private void initializeCountries(Game game) {
        int i = 0;
        for (GameDefaultsProperties.CountryData countryData : gameDefaults.getCountries()) {
            if(i >= game.getNumberOfCountries()) {
                break;
            }
            Country country = new Country();
            country.setName(countryData.getName());
            country.setMediaLiteracy(countryData.getPolarization());
            country.setTrustInState(countryData.getTrustInState());
            country.setEconomy(countryData.getEconomy());
            country.setGame(game);
            game.getCountries().add(country);
            countryRepository.save(country);
        }
    }

    /**
     * Initializes the teams for the game based on the game defaults.
     * @param game The game instance to initialize teams for.
     */
    private void initializeTeams(Game game) {
        int i = 0;
        for (GameDefaultsProperties.TeamData teamData : gameDefaults.getTeams()) {
            if(i >= game.getNumberOfTeams()) {
                break;
            }
            i++;
            Team team = new Team();
            team.setName(teamData.getName());
            team.setColorHex(teamData.getColorHex());
            team.setManipulation(teamData.getManipulation());
            team.setGame(game);
            game.getTeams().add(team);
            teamRepository.save(team);
        }
    }

    /**
     * Initializes the opinion rates for each country and team.
     * @param game The game instance to initialize opinion rates for.
     */
    private void initializeOpinionsRates(Game game) {
        for (Country country : game.getCountries()) {
            for (Team team : game.getTeams()) {
                OpinionRate opinionRate = new OpinionRate();
                opinionRate.setCountry(country);
                opinionRate.setTeam(team);
                opinionRate.setRate(0.0f);
                opinionRateRepository.save(opinionRate);
            }
        }
    }
}


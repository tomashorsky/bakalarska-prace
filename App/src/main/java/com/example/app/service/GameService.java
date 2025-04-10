package com.example.app.service;

import com.example.app.dto.GameDTO;
import com.example.app.entity.Game;
import com.example.app.enums.GamePhase;
import com.example.app.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameInitializer gameInitializer;

    public GameDTO getGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        return GameToDTO(game);
    }

    public GameDTO createGame(GameDTO gameDTO) {
        if (gameDTO.getNumberOfTeams() <= 0) {
            throw new IllegalArgumentException("Number of teams must be greater than 0");
        }
        if (gameDTO.getNumberOfCountries() <= 0) {
            throw new IllegalArgumentException("Number of countries must be greater than 0");
        }
        Game game = new Game();
        game.setNumberOfTeams(gameDTO.getNumberOfTeams());
        game.setNumberOfCountries(gameDTO.getNumberOfCountries());
        game.setPhase(GamePhase.SETUP);

        // initialize teams, countries and opinion rates with default values
        gameInitializer.initializeGame(game);

        return GameToDTO(gameRepository.save(game));
    }

    public GameDTO changeGamePhase(Long id, GamePhase phase) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        game.setPhase(phase);
        return GameToDTO(gameRepository.save(game));
    }

    private GameDTO GameToDTO(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .numberOfTeams(game.getNumberOfTeams())
                .phase(game.getPhase())
                .build();
    }
}

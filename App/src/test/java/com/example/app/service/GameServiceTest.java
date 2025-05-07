package com.example.app.service;

import com.example.app.entity.Country;
import com.example.app.entity.Game;
import com.example.app.enums.GamePhase;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private GameService gameService;

    private Game testGame;

    @BeforeEach
    void setUp() {
        testGame = new Game();
        testGame.setId(42L);
        Country country = new Country();
        country.setEconomy(null);
        testGame.setCountries(List.of(country));
    }


    @Test
    void testChangeGamePhase_fromPausedToInProgress_shouldUpdateEconomyAndSetPhase() {
        testGame.setPhase(GamePhase.PAUSED);

        Mockito.when(gameRepository.findById(42L)).thenReturn(Optional.of(testGame));
        Mockito.when(gameRepository.save(any(Game.class))).thenAnswer(i -> i.getArgument(0));

        gameService.changeGamePhase(42L, GamePhase.IN_PROGRESS);

        verify(countryRepository, times(1)).save(any(Country.class));
        assertEquals(GamePhase.IN_PROGRESS, testGame.getPhase());
        assertNotNull(testGame.getCountries().get(0).getEconomy());
    }


    @Test
    void testChangeGamePhase_regularChange_shouldOnlySetPhase() {
        testGame.setPhase(GamePhase.SETUP);

        Mockito.when(gameRepository.findById(42L)).thenReturn(Optional.of(testGame));
        Mockito.when(gameRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        gameService.changeGamePhase(42L, GamePhase.FINISHED);

        verify(countryRepository, never()).save(any());
        assertEquals(GamePhase.FINISHED, testGame.getPhase());
        assertNull(testGame.getCountries().get(0).getEconomy());
    }
}

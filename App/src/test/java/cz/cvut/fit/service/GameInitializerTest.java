package cz.cvut.fit.service;

import cz.cvut.fit.config.GameDefaultsProperties;
import cz.cvut.fit.data.entity.Country;
import cz.cvut.fit.data.entity.Game;
import cz.cvut.fit.data.entity.OpinionSupport;
import cz.cvut.fit.data.entity.Team;
import cz.cvut.fit.data.repository.CountryRepository;
import cz.cvut.fit.data.repository.OpinionSupportRepository;
import cz.cvut.fit.data.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameInitializerTest {

    @Mock private CountryRepository countryRepository;
    @Mock private TeamRepository teamRepository;
    @Mock private OpinionSupportRepository opinionSupportRepository;
    @Mock private GameDefaultsProperties defaults;

    @InjectMocks
    private GameInitializer initializer;
    private GameDefaultsProperties.CountryData c1, c2, c3;
    private GameDefaultsProperties.TeamData t1, t2, t3;

    @BeforeEach
    void setUp() {
        // Mock country data
        c1 = new GameDefaultsProperties.CountryData();
        c2 = new GameDefaultsProperties.CountryData();
        c3 = new GameDefaultsProperties.CountryData();
        c1.setName("Country 1");
        c1.setTrustInState(50f);
        when(defaults.getCountries()).thenReturn(List.of(c1, c2, c3));

        // Mock team data
        t1 = new GameDefaultsProperties.TeamData();
        t2 = new GameDefaultsProperties.TeamData();
        t3 = new GameDefaultsProperties.TeamData();
        t1.setName("Team 1");
        t1.setColorHex("#FF0000");
        when(defaults.getTeams()).thenReturn(List.of(t1, t2, t3));
    }


    @Test
    void initializeGame_createsExpectedEntities() {
        Game game = new Game();
        game.setNumberOfCountries(3);
        game.setNumberOfTeams(2);

        initializer.initializeGame(game);

        // Assert countries saved
        verify(countryRepository, times(3)).save(any(Country.class));
        assertEquals(3, game.getCountries().size());

        // Assert teams saved
        verify(teamRepository, times(2)).save(any(Team.class));
        assertEquals(2, game.getTeams().size());

        // Assert opinion supports saved (2 teams x 3 countries = 6 opinion supports)
        verify(opinionSupportRepository, times(6)).save(any(OpinionSupport.class));
    }

    @Test
    void initializeGame_setsData() {
        Game game = new Game();
        game.setNumberOfCountries(1);
        game.setNumberOfTeams(1);

        initializer.initializeGame(game);

        // Assert countries and teams are created
        assertEquals(1, game.getCountries().size());
        assertEquals(1, game.getTeams().size());

        // Assert values of the first country
        Country country = game.getCountries().get(0);
        assertEquals("Country 1", country.getName());
        assertEquals(50f, country.getTrustInState());

        // Assert values of the first team
        Team team = game.getTeams().get(0);
        assertEquals("Team 1", team.getName());
        assertEquals("#FF0000", team.getColorHex());

    }
}


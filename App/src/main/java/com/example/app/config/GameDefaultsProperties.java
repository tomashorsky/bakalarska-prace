package com.example.app.config;

import com.example.app.enums.EconomicSituation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "game-defaults")
@Getter
@Setter
public class GameDefaultsProperties {
    private List<CountryData> countries;
    private List<TeamData> teams;

    @Getter
    @Setter
    public static class CountryData {
        private String name;
        private float polarization;
        private float trustInState;
        private EconomicSituation economy;
    }

    @Getter
    @Setter
    public static class TeamData {
        private String name;
        private String colorHex;
        private String manipulation;
    }
}

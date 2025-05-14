package cz.cvut.fit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "game-messages")
@Getter
@Setter
public class GameMessageProperties {
    private String actionSuccess;
    private String toolSuccess;
    private String toolAdded;
}

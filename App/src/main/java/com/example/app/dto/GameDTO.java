package com.example.app.dto;

import com.example.app.enums.GamePhase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDTO {
    private Long id;
    private int numberOfTeams;
    private GamePhase phase;
}

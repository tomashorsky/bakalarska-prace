package com.example.app.dto;

import com.example.app.enums.GamePhase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private LocalDateTime createdAt;
    private int numberOfTeams;
    private int numberOfCountries;
    private GamePhase phase;
}

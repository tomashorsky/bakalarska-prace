package com.example.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {
    private Long id;
    private String color;
    private String colorHex;
    private String manipulation;
    private Long gameId;
}

package com.example.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDTO {
    private Long id;
    private String name;
    private Long teamId;
}
package com.example.app.dto;

import com.example.app.enums.ToolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolDTO {
    private Long id;
    private ToolType type;
    private int level;
    private Long teamId;
    private LocalDateTime canBeUsedAt;
}

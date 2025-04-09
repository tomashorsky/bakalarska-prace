package com.example.app.dto;

import com.example.app.enums.ToolType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolDTO {
    private Long id;
    private ToolType type;
    private int level;
    private Long teamId;
}

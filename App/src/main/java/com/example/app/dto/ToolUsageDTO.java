package com.example.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolUsageDTO {
    private Long id;
    private Long toolId;
    private Long countryId;

    private float impact;
    private float polarizationImpact;
    private float trustInStateImpact;
    private LocalDateTime usedAt;
}

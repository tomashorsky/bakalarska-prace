package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolUsageDTO {
    private Long id;
    private Long toolId;
    private Long countryId;

    private float impact;
    private float trustInStateImpact;
    private LocalDateTime usedAt;
}

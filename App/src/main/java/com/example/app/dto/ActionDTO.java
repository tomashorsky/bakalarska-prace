package com.example.app.dto;

import com.example.app.enums.ActionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ActionDTO {
    private Long id;
    private ActionType actionType;
    private Long teamId;
    private Long countryId;

    private float impact;
    private float polarizationImpact;
    private float trustInStateImpact;
    private LocalDateTime usedAt;
}

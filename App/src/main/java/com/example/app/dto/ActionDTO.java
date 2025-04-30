package com.example.app.dto;

import com.example.app.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO {
    private Long id;
    private ActionType actionType;
    private Long teamId;
    private Long countryId;

    private float impact;
    private float trustInStateImpact;
    private LocalDateTime usedAt;
}

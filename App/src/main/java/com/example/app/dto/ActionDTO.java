package com.example.app.dto;

import com.example.app.entity.Country;
import com.example.app.entity.Team;
import com.example.app.enums.ActionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionDTO {
    private Long id;
    private ActionType actionType;
    private Long teamId;
    private Long countryId;
}

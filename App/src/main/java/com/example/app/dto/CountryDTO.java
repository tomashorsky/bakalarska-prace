package com.example.app.dto;

import com.example.app.enums.EconomicSituation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDTO {
    private Long id;
    private String name;
    private float polarization;
    private float trustInState;
    private EconomicSituation economy;
    private Long gameId;
}

package com.example.app.dto;

import com.example.app.enums.EconomicSituation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    private String name;
    private float polarization;
    private float trustInState;
    private EconomicSituation economy;
    private Long gameId;
}

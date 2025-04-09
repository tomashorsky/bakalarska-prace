package com.example.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionRateDTO {
    private Long id;
    private float rate;
    private Long countryId;
    private Long teamId;
}

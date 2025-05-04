package com.example.app.dto;

import com.example.app.enums.SituationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    private String name;
    private SituationLevel mediaLiteracy;
    private float trustInState;
    private SituationLevel economy;
    private Long gameId;
    private List<OpinionSupportDTO> opinionSupports = null; // Optional
}

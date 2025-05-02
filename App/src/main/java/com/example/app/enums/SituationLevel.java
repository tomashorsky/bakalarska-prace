package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituationLevel {
    VERY_BAD("Velmi špatná",0),
    BAD("Špatná",25),
    AVERAGE("Průměrná",50),
    GOOD("Dobrá",75),
    VERY_GOOD("Velmi dobrá",100);
    
    private final String name;
    private final int value;

}

package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    DISINFORMATION(
            "Dezinformace",
            "Šíření zavádějících informací s cílem destabilizace."
    ),
    DEEPFAKE(
            "Deepfake",
            "Použití manipulativního videa či hlasu ke klamání cílové skupiny."
    ),
    FAKE_NEWS(
            "Fake news",
            "Publikace falešných zpráv za účelem ovlivnění veřejnosti."
    );

    private final String name;

    private final String description;

}

package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToolType {
    TROLL_FARM(
            "Trollí farma",
            "Trolí farmy jsou skupiny lidí, kteří se snaží manipulovat veřejným míněním na internetu."
    ),
    DEEPFAKE_MAKER(
            "AI nástroj na deepfake videa",
            "AI nástroj pro vytváření falešných videí, obrázků, audiozánamů která mohou být použita k manipulaci."
    ),
    //TODO delete?
    /*
    FAKE_EXPERTS(
            "Falešný expert",
            "Člověk, který se pasuje do role odborníka nebo vědce, aby dodal důvěryhodnost názoru."
    ),*/
    FAKE_NEWS_SITE(
            "Falešný zpravodajský web",
            "Web vydávající se za důvěryhodné médium, který šíří zmanipulované nebo nepravdivé zprávy."
    );

    private final String name;

    private final String description;
}

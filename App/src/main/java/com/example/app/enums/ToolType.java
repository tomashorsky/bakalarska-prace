package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToolType {
    TROLL_FARM(
            "Trollí farma",
            "Firma která zaměstnává desítky lidí, kteří ovládají tisíce falešných účtů na sociálních " +
                    "sítích, prostřednictvým kterých píše provokatvní komentáře a šíří dezinformace. "
    ),
    DEEPFAKE_MAKER(
            "AI nástroj na deepfakes",
            "AI nástroj pro vytváření falešných videí, obrázků, audiozánamů která mohou být použita k manipulaci."
    ),
    FAKE_NEWS_SITE(
            "Falešný zpravodajský web",
            "Web vydávající se za důvěryhodné médium, který šíří zmanipulované nebo nepravdivé zprávy."
    ),
    //TODO delete?
    /*
    FAKE_EXPERTS(
            "Falešný expert",
            "Člověk, který se pasuje do role odborníka nebo vědce, aby dodal důvěryhodnost názoru."
    ),*/;

    private final String label;

    private final String description;
}

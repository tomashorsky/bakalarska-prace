package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    DISINFORMATION(
            "Dezinformace",
            " Úmyslné šíření nepravdivých informací s cílem ovlivnit názory, nebo rozhodnutí lidí."
    ),
    MICRO_TARGETING(
            "Mikrocílení (Microtargeting)",
            "Využití AI k analýze dat uživatelů a zobrazení jim takového příspěvku, který u nich vyvolá" +
                    " emoční reakci a má největší šanci je ovlivnit."
    ),
    ASTROTURFING(
            "Umělá podpora (Astroturfing)",
            "Simulace spontánní veřejné podpory určitého postoje pomocí falešných příspěvků, komentářů či" +
                    " organizací za účelem ovlivnění veřejného mínění."
    ),
    ECHO_CHAMBERS(
            "Echo chambers",
            "Uzavřené online prostředí, kde člověk vnímá převážně souhlasné názory, čímž se jeho postoje" +
                    " utvrzují a mohou radikalizovat. (Například skupiny Facebooku, nebo Telegramu)"
    ),
    MALIFORMATIONS(
            "Malinformace",
            "Zveřejnění pravdivých, často zveličených informací ve strategickém čase s cílem někoho poškodit." +
                    " (Například zveřejnění osobního skandálu před volbami)"
    );

    private final String name;

    private final String description;

}

package com.example.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    DISINFORMATION(
            "Rozšířit dezinformaci",
            "Úmyslně rozšiř nepravdivou informaci s cílem ovlivnit názory a rozhodnutí lidí."
    ),
    MICRO_TARGETING(
            "Využít mikrocílení (Microtargeting)",
            "Využijte AI k analýze dat uživatelů soc. sítí a zobrazujte jim takové příspěvky, které v nich" +
                    " vyvolají emoční reakci a ovlivní jejich názor."
    ),
    ASTROTURFING(
            "Uměle podpořit názor (Astroturfing)",
            "Simuluj spontální veřejnou podporu tvého názoru pomocí falešných příspěvků, reakcí, nebo dokonce svoláním demostrace" +
                    " tvůj názor se tak bude zdát podporovanější než ve skutečnosti je."
    ),
    ECHO_CHAMBERS(
            "Vytvořit skupinu (Echo chambers)",
            "Vytvoř uzavřené online prostředí (např. skupinu na Facebooku, nebo Telegramu), ve které podporuj v diskuzi, utvrzuj a radikalizuj své podporovatele."
    ),
    MALIFORMATIONS(
            "Zveřejnit malinformaci",
            "Najdi, zvelič a zveřejni pravdivou informaci, která poškodí nějakého výrazného odporovatele tvého názoru. " +
                    "Napríklad zveřejni jeho soukromé zprávy, fotky, nebo třeba nějakou aféru."
    );

    private final String name;

    private final String description;

}

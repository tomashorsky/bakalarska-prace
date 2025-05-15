package cz.cvut.fit.data.enums;

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
            "Využít mikrocílení (microtargeting)",
            "Využij AI k analýze dat uživatelů soc. sítí. Vytvářej a zobrazuj jim takové příspěvky, které v nich" +
                    " vyvolají emoční reakci a ovlivní jejich názor."
    ),
    ASTROTURFING(
            "Uměle podpořit názor (astroturfing)",
            "Simuluj spontální veřejnou podporu tvého názoru pomocí falešných příspěvků, reakcí, nebo dokonce svoláním demostrace." +
                    " Tvůj názor se tak bude zdát podporovanější než ve skutečnosti je."
    ),
    ECHO_CHAMBERS(
            "Vytvořit skupinu (echo chambers)",
            "Vytvoř uzavřené online prostředí (např. skupinu na Facebooku, nebo Telegramu) pro podporovatele. " +
                    "Ve skupině se budou vaši podporovatelé, s tvojí asistencí, navzájem utvrzovat a radikalizovat."
    ),
    MALINFORMATIONS(
            "Zveřejnit malinformaci",
            "Najdi, nafoukni a zveřejni pravdivou informaci, která poškodí nějakého výrazného odpůrce tvého názoru. " +
                    "Napríklad zveřejni jeho soukromé zprávy, fotky, nebo nějakou aféru."
    );

    private final String label;

    private final String description;

}

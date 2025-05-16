# Vývojářská dokumentace

Vývojářská dokumentace slouží jako technický průvodce pro vývojáře, kteří chtějí aplikaci *InfoWars* upravovat nebo dále rozvíjet.

## Lokální zprovoznění aplikace

### Požadavky:
Pro úspěšné spuštění a úpravu aplikace na lokálním počítači je potřeba mít připravené:

- Git
- IDE pro úpravu kódu (Napříkald IntelliJ Idea)
- Java SDK (minimálně verzi 17)
- Maven
- Spuštěnou PostgreSQL databázi ([Například podle tohoto návodu](https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/))

### Stažení projektu
Projekt lze naklonovat z GitLab repozitáře pomocí příkazu:
git clone https://gitlab.fit.cvut.cz/horskto2/bakalarska-prace.git


### Připojení k databázi
Pro připojení k databázi souboru je třeba upravit následující nastavení připojení v souboru `src/main/resources/application.properties`:


## Publikování nové verze

Nová verze se automaticky publikuje při jakékoliv změně ve větvi *deplyo*. V případě, že se změny neprojeví, tak pravděpodobně došlo k chybě při sestavení aplikace. V tomto případě kontaktujte administrátora. Nová verze by po úspěšném sestavení měla být dostupná na:

[https://bakalarska-prace-6h4x.onrender.com/admin/games](https://bakalarska-prace-6h4x.onrender.com/admin/games)

## Úprava herních proměnných

V souboru `src/main/resources/application.yaml` se nachází nastavení výchozího stavu států a týmů. Zde můžete upravit například názory pro nově vytvořené týmy, nebo výchozí stav států.


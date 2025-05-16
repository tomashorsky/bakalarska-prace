# Hra InfoWars
Toto je aplikace ke skautké hře InfoWars, vytvořené v rámci bakalářské práce Strategická hra pro skauty s interaktivní webovou apliakací.

## Lokální zprovoznění aplikace

### Požadavky:
Pro úspěšné spuštění a úpravu aplikace na lokálním počítači je potřeba mít připravené:

- Git
- IDE pro úpravu kódu (Napříkald IntelliJ Idea)
- Java SDK (minimálně Java 17)
- Java JRE (minimálně Java 17)
- Spuštěnou PostgreSQL databázi ([Například podle tohoto návodu](https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/))
- Maven

### Stažení projektu
Projekt lze naklonovat z GitLab repozitáře pomocí příkazu:
git clone https://gitlab.fit.cvut.cz/horskto2/bakalarska-prace.git


### Připojení k databázi
Pro připojení k databázi souboru je třeba upravit následující nastavení připojení v souboru `src/main/resources/application.properties`.

### Spuštění aplikace
Po úspěšném nastavení připojení k databázi spusť aplikaci pomocí IDE, nebo následujícími příkazy:  
`./mvnw clean package -DskipTests`  
`java -jar target/InfoWars-1.0.0.jar`

Aplikace bude poté dostupná na adrese: [http://localhost:8080/admin/games](http://localhost:8080/admin/games)

## Publikování nové verze

Nová verze se automaticky publikuje při jakékoliv změně ve větvi `/deploy`. V případě, že se změny neprojeví, tak pravděpodobně došlo k chybě při sestavení aplikace. V tomto případě kontaktujte administrátora. Nová verze by po úspěšném sestavení měla být dostupná na:

[https://bakalarska-prace-6h4x.onrender.com/admin/games](https://bakalarska-prace-6h4x.onrender.com/admin/games)

## Úprava herních proměnných

V souboru `src/main/resources/application.yaml` se nachází nastavení výchozího stavu států a týmů. Zde můžete upravit například názory pro nově vytvořené týmy, nebo výchozí stav států.


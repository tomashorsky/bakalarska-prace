# 1. Stage: build the app
FROM maven:eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Stage: run the app
FROM openjdk:eclipse-temurin:21-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080

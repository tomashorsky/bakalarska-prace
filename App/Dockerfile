
FROM openjdk:17-jdk-alpine

COPY App/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080

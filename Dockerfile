# Utiliser une image plus légère avec Java pour l'exécution
FROM eclipse-temurin:17-jdk-alpine

ADD target/simplonapp.jar simplonapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/simplonapp.jar"]


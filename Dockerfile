# Utiliser une image plus légère avec Java pour l'exécution
FROM eclipse-temurin:17-jdk-alpine

LABEL description="Application Spring Boot pour la formation Simplon, pour la gestion des participants. Version 0.0.1-SNAPSHOT"

# Définir le répertoire de travail dans l'image
WORKDIR /app


# Copier le fichier JAR de l'étape de build
COPY target/simplonapp-0.0-1-SNAPSHOT.jar app.jar

# Exposer le port 8080 de l'image
EXPOSE 8080

# Exécuter la commande pour démarrer l'application Spring Boot
CMD ["java", "-jar", "app.jar"]

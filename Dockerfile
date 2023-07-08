# Utiliser une image de base avec Java
FROM eclipse-temurin:17-jdk-alpine

LABEL description="Application Spring Boot pour la formation Simplon, pour la gestion des participants. Version 0.0.1-SNAPSHOT"
# Définir le répertoire de travail dans l'image
WORKDIR /app

# Exposer le port 8080 de l'image
EXPOSE 8080

# Copier le fichier JAR de  l'application dans l'image.
COPY target/simplonapp-0.0.1-SNAPSHOT.jar app.jar

# Exécuter la commande pour démarrer  l'application Spring Boot.
CMD ["java", "-jar", "app.jar"]



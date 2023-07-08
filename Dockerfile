# Utiliser une image de base avec Java
FROM eclipse-temurin:17-jdk-alpine

LABEL description="Application Spring Boot pour la formation Simplon, pour la gestion des participants. Version 0.0.1-SNAPSHOT"
# Définir le répertoire de travail dans l'image
WORKDIR /app

# Exécuter mvn clean package pour nettoyer et construire le projet
RUN mvn clean package -DskipTests

#renommer le fichier jar
RUN mv target/simplonapp-0.0.1-SNAPSHOT.jar app.jar
# Exposer le port 8080 de l'image
EXPOSE 8080

# Copier le fichier JAR de  l'application dans l'image.
COPY app.jar app.jar

# Exécuter la commande pour démarrer  l'application Spring Boot.
CMD ["java", "-jar", "app.jar"]



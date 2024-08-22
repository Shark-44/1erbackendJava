# Utiliser une image de base avec Java installé
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/*.jar app.jar

# Copiez le script wait-for-it.sh dans l'image
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
# Définir le point d'entrée pour utiliser wait-for-it.sh
ENTRYPOINT ["/usr/local/bin/wait-for-it.sh", "mysql-server:3306", "-t", "60", "--", "java", "-jar", "/app/app.jar"]

# Exposer le port que l'application utilise
EXPOSE 8080



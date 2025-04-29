FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier les fichiers source Java
COPY ./ .

# Compiler le fichier Java
RUN javac *.java

EXPOSE 9000

# Lancer le fichier compilé
ENTRYPOINT ["java", "Controleur"]
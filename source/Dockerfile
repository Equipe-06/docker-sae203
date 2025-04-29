FROM debian:latest

WORKDIR /app

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

COPY ./ .

RUN javac -encoding UTF-8 *.java

EXPOSE 9000

ENTRYPOINT ["java", "Controleur"]

FROM java:openjdk-8-jdk-alpine

WORKDIR /app
COPY target/findingtrudeau-1.0-SNAPSHOT.jar  findingtrudeau.jar
COPY config.yml  config.yml

EXPOSE 8080
ENTRYPOINT ["java","-jar","findingtrudeau.jar", "server", "config.yml"]
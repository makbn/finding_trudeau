FROM java:openjdk-8-jdk-alpine

WORKDIR /app
ADD https://github.com/makbn/finding_trudeau/releases/download/v1.0-SNAPSHOT/findingtrudeau-1.0-SNAPSHOT.jar  findingtrudeau.jar
COPY config.yml  config.yml

EXPOSE 8080
ENTRYPOINT ["java","-jar","findingtrudeau.jar", "server", "config.yml"]
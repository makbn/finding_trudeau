FROM zenika/alpine-maven

WORKDIR /app
ADD ./* ./

RUN mvn clean package

EXPOSE 8080
ENTRYPOINT ["java","-jar","target/findingtrudeau-1.0-SNAPSHOT.jar", "server", "config.yml"]
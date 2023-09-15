FROM maven:3.9-eclipse-temurin-17-focal as build

WORKDIR /app

COPY . .

RUN mvn package

FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

ENV JAVA_OPTS=""

COPY --from=build /app/target/Backend-0.0.1-SNAPSHOT.jar lombokerBackend.jar

ENTRYPOINT java -jar ${JAVA_OPTS} lombokerBackend.jar
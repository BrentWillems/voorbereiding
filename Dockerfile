FROM maven:3.6-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE="usr/src/app/target/exercise-0.0.1-SNAPSHOT.jar"
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
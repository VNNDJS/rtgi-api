FROM ubuntu:latest
LABEL authors="smesy"
RUN apt update && apt install -y openjdk-21-jdk maven

WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
COPY --from=0 /app/build/libs/rtgi-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Étape 1 : builder le jar
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/realtime-chat-service-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
EXPOSE 8081
ENTRYPOINT ["/wait-for-it.sh", "postgres", "5432", "--", "/wait-for-it.sh", "redis", "6379", "--", "java", "-jar", "app.jar"]
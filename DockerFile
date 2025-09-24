
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY EverGrowth-server/EverGrowth-server/target/EverGrowth-server-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]

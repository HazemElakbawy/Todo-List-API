FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/todo-app-0.0.1-SNAPSHOT.jar /app/todo-app.jar

ENTRYPOINT ["java", "-jar", "/app/todo-app.jar"]
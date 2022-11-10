FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Docker контейнер (Spring Boot App) буде слухати на порту 8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

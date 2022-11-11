#Stage 1
FROM maven:3.8.1-openjdk-17 AS builder

# set working directory
WORKDIR app
COPY pom.xml /app/pom.xml
COPY src /app/src

# очистити, зібрати, упакувати
RUN mvn clean package 

#Stage 2
FROM openjdk:17
ARG JAR_FILE=app/target/*.jar
COPY --from=builder ${JAR_FILE} app/app.jar

# Docker контейнер (Spring Boot App) буде слухати на порту 8080
EXPOSE 8080
CMD ["java", "-jar", "app/app.jar"]

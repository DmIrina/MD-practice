FROM maven:3.8.5-openjdk-17-slim@sha256:5ba3fae0f77cbe08deac4984dfa4f5397345d5ba8221871285a96e2ef8f16808 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests


FROM openjdk:17-alpine@sha256:a996cdcc040704ec6badaf5fecf1e144c096e00231a29188596c784bcf858d05
RUN mkdir /app
ARG JAR_FILE=/project/target/*.jar
COPY --from=build ${JAR_FILE} /app/build.jar
WORKDIR /app
CMD java -jar build.jar
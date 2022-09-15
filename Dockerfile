# Fase de contrucción
FROM maven:3.6-openjdk-8-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -DskipTests -f /app/pom.xml clean package

# Fase ejecucion
FROM openjdk:8-jre-alpine
COPY --from=build /app/target/backend*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
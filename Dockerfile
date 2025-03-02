# Stage 1: Build the application using Maven and JDK 21
FROM maven:3.9.2-eclipse-temurin-21 AS build
WORKDIR /build
# Copia il pom e la directory src nel container
COPY pom.xml .
COPY src ./src
# Compila il progetto e crea l'uber-jar, saltando i test per velocità
RUN mvn clean package -DskipTests

# Stage 2: Run the application using JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copia il jar generato dalla fase di build
COPY --from=build /build/target/*-runner.jar app.jar
# Espone la porta configurata (ad esempio 8085)
EXPOSE 8085
# Avvia l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]

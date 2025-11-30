# ---- Etapa 1: Construcción ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiar todo el proyecto (incluye oracle_wallet)
COPY . .

# Instalar Maven dentro del contenedor
RUN apt-get update && apt-get install -y maven

# Compilar el backend sin tests
RUN mvn -B clean package -DskipTests

# ---- Etapa 2: Runtime ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/target/*.jar app.jar

# Copiar el wallet de Oracle a la ruta esperada
COPY oracle_wallet /app/oracle_wallet

# Variable para Oracle
ENV TNS_ADMIN=/app/oracle_wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

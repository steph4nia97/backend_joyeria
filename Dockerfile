// Etapa 1: Construcción
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

// Copiar todos los archivos del backend
COPY . .

//Instalar Maven dentro del contenedor
RUN apt-get update && apt-get install -y maven

//Crear Maven Wrapper dentro del contenedor (tú no lo tienes localmente)
RUN mvn -N io.takari:maven:wrapper

//Compilar el proyecto sin tests
RUN ./mvnw clean package -DskipTests

//Etapa 2: Imagen final y liviana
FROM eclipse-temurin:17-jre
WORKDIR /app

//Copiar el JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto 8080 (Render lo detecta solo)
EXPOSE 8080

# Ejecutar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
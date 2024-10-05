# Fase 1: Build de la aplicación
FROM maven:3.8.6-openjdk-17-slim AS build

# Establecer el directorio de trabajo en el contenedor para la fase de build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .

# Descargar dependencias del proyecto
RUN mvn dependency:go-offline -B

# Copiar el código fuente al contenedor
COPY src ./src

# Compilar el proyecto y generar el archivo JAR
RUN mvn clean package -DskipTests

# Fase 2: Imagen para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor para la fase de ejecución
WORKDIR /app

# Copiar el archivo JAR generado en la fase de build
COPY --from=build /app/target/estudiante-api-0.0.1-SNAPSHOT.jar estudiante-api.jar

# Exponer el puerto 8080 para la aplicación
EXPOSE 8080

# Variables de entorno para la conexión a PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://52.45.141.206:8009/mydb
ENV SPRING_DATASOURCE_USERNAME=pq
ENV SPRING_DATASOURCE_PASSWORD=rimac

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "estudiante-api.jar"]

# Usa una imagen base con Java y Maven para la fase de construcción de Amazon ECR Public
FROM public.ecr.aws/docker/library/maven:3.9.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descarga las dependencias (aprovecha el cache de Docker)
RUN mvn dependency:go-offline

# Copia el resto del código fuente
COPY src ./src
COPY application.properties .

# Construye la aplicación Spring Boot en un JAR ejecutable
RUN mvn clean install -DskipTests

# Usa una imagen base más ligera para la fase de ejecución de Amazon ECR Public
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR ejecutable desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Copia el application.properties actualizado
COPY --from=build /app/application.properties application.properties

# Expone el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
# Etapa 1: Construir la aplicación
FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootjar --no-daemon

# Etapa 2: Preparar el contenedor de producción
FROM eclipse-temurin:17.0.8.1_1-jre-alpine

# Crear directorio de la aplicación
RUN mkdir /app

# Copiar el JAR generado desde la etapa de construcción a la imagen de producción
COPY --from=build /home/gradle/src/build/libs/*.jar /app/appp.jar

# Configurar la variable de entorno del puerto
ENV PORT=8080

# Crear un volumen para los logs
VOLUME /logs

# Exponer el puerto definido
EXPOSE ${PORT}

# Establecer el punto de entrada para el contenedor
ENTRYPOINT ["java", "-jar", "/app/appp.jar", "--server.port=${PORT}"]

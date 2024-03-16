# Utiliza una imagen base de OpenJDK
FROM openjdk:17.0.2-jdk

# Copia el archivo JAR generado de tu proyecto a la imagen
COPY target/your-project.jar /app/your-project.jar

# Define el directorio de trabajo dentro de la imagen
WORKDIR /app

# Expone el puerto en el que tu aplicación Spring Boot escucha
EXPOSE 8080

# Comando para ejecutar tu aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "your-project.jar"]

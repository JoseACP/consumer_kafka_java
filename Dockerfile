FROM openjdk:8-jdk-slim

WORKDIR /app

# Copiar el archivo JAAS
COPY kafka_client_jaas.conf .

# Copiar el JAR compilado
COPY target/spring-boot-kafka-consumer-example-0.0.1-SNAPSHOT.jar .

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", \
  "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
  "-Djava.security.auth.login.config=/app/kafka_client_jaas.conf", \
  "-jar", \
  "/app/spring-boot-kafka-consumer-example-0.0.1-SNAPSHOT.jar"]

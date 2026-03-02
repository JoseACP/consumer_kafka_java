# Spring Boot Kafka Consumer Docker

## Construir la imagen Docker

```bash
# Compilar el proyecto
./mvnw clean package -DskipTests

# Construir la imagen Docker
docker build -t spring-boot-kafka-consumer:latest .
```

## Ejecutar en Docker

```bash
docker run -d \
  --name kafka-consumer \
  spring-boot-kafka-consumer:latest
```

## Subir a Docker Hub

```bash
# Login en Docker Hub
docker login

# Tagear la imagen con tu usuario
docker tag spring-boot-kafka-consumer:latest TU_USUARIO/spring-boot-kafka-consumer:latest

# Subir al repositorio
docker push TU_USUARIO/spring-boot-kafka-consumer:latest
```

## Subir a Amazon ECR (para EC2)

```bash
# Crear repositorio en ECR
aws ecr create-repository --repository-name spring-boot-kafka-consumer --region us-east-1

# Login en ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com

# Tagear la imagen
docker tag spring-boot-kafka-consumer:latest YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/spring-boot-kafka-consumer:latest

# Subir a ECR
docker push YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/spring-boot-kafka-consumer:latest
```

## En tu instancia EC2

```bash
# Instalar Docker
sudo yum update -y
sudo yum install docker -y
sudo systemctl start docker
sudo usermod -a -G docker ec2-user

# Descargar la imagen de ECR
docker pull YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/spring-boot-kafka-consumer:latest

# Ejecutar el contenedor
docker run -d \
  --name kafka-consumer \
  YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/spring-boot-kafka-consumer:latest

# Ver los logs
docker logs -f kafka-consumer
```

## Notas

- El archivo `kafka_client_jaas.conf` se copia automáticamente en el Docker
- La aplicación se conecta a Kafka con las credenciales configuradas en `KafkaConfiguration.java`
- Los logs se mostrarán en la salida estándar del contenedor

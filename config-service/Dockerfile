#FROM openjdk:11
FROM openjdk:11-jre-slim-buster
COPY "./target/config-service-0.0.1-SNAPSHOT.jar" "ms-config-service"
EXPOSE 8081
ENTRYPOINT ["java","-jar","ms-config-service"]

#CREAR IMAGEN
#docker build -t "ms-config-service" .

#BORRAR IMAGEN
#docker rmi ms-product:latest

#LEVANTAR CONTAINER
#docker run -d --name product-service  -p :8080:8091 ms-product
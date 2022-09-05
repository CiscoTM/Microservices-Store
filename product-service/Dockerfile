#FROM openjdk:11
FROM openjdk:11-jre-slim-buster
COPY "./target/product-service-0.0.1-SNAPSHOT.jar" "ms-product.jar"
EXPOSE 0
ENTRYPOINT ["java","-jar","ms-product.jar"]

#CREAR IMAGEN
#docker build -t "ms-product" .

#BORRAR IMAGEN
#docker rmi ms-product:latest

#LEVANTAR CONTAINER
#docker run -d --name product-service  -p :8080:8091 ms-product
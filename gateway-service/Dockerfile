FROM openjdk:11-jre-slim-buster
COPY "./target/gateway-service-0.0.1-SNAPSHOT.jar" "ms-gateway-service"
EXPOSE 8060
ENTRYPOINT ["java","-jar","ms-gateway-service"]

#CREAR IMAGEN
#docker build -t "ms-gateway-service" .
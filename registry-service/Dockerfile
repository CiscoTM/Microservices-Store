FROM openjdk:11-jre-slim-buster
COPY "./target/registry-service-0.0.1-SNAPSHOT.jar" "ms-eureka-service"
EXPOSE 8761
ENTRYPOINT ["java","-jar","ms-eureka-service"]

#CREAR IMAGEN
#docker build -t "ms-eureka-service" .
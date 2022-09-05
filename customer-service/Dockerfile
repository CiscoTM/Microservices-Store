FROM openjdk:11-jre-slim-buster
COPY "./target/customer-service-0.0.1-SNAPSHOT.jar" "ms-customer.jar"
EXPOSE 0
ENTRYPOINT ["java","-jar","ms-customer"]

# docker build -t "ms-customer" .
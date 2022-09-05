FROM openjdk:11-jre-slim-buster
COPY "./target/shopping-service-0.0.1-SNAPSHOT.jar" "ms-shopping"
EXPOSE 0
ENTRYPOINT ["java","-jar","ms-shopping"]

# docker build -t "ms-shopping" .
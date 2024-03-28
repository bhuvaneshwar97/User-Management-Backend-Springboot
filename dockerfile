FROM openjdk:8
ADD target/campaign-service-0.0.1-SNAPSHOT.jar campaign-service.jar
ENTRYPOINT ["java","-jar","campaign-service.jar"]
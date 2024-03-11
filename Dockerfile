FROM openjdk:17-jdk-alpine
MAINTAINER grupo3.com
COPY target/second-tech-challenge-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
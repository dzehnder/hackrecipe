FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD DB_Service_0.0.1.-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
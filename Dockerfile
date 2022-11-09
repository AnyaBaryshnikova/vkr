FROM openjdk:8-jdk-alpine
VOLUME /main-app
ADD target/rt-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
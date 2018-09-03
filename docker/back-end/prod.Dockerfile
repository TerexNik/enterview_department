FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./department-0.1.jar app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
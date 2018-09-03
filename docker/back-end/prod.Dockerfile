FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV PORT=80
COPY ./department-0.1.jar app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 80
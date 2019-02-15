FROM openjdk:8-jre-alpine

LABEL version="0.1"

WORKDIR /usr/app
COPY target/hello.jar .

EXPOSE 8080

CMD ["java", "-jar", "hello.jar"]

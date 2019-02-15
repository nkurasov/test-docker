FROM openjdk:8-jre-alpine

LABEL version="0.1"

WORKDIR /usr/app
COPY target/surname.jar .

CMD ["java", "-jar", "surname.jar"]

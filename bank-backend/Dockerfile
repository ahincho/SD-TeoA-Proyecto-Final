# Package This Project Using Maven Running this Command on Terminal
# > mvn clean
# > mvn package
# Run this Command on Terminal to Create the Docker Image
# > docker build -t springboot-bank:3.0 .
FROM amazoncorretto:17.0.8-alpine
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=target/bank-3.0.jar
ADD ${JAR_FILE} bank.jar
LABEL name="bank-springboot"
LABEL authors="Angel"
LABEL mainteiner="ahincho"
ENTRYPOINT ["java","-jar","/bank.jar"]

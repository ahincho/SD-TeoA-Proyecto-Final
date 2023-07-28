FROM openjdk:17
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=target/bank-2.0.jar
ADD ${JAR_FILE} bank.jar
LABEL name="bank-springboot"
LABEL authors="Angel"
LABEL mainteiner="ahincho"
ENTRYPOINT ["java","-jar","/bank.jar"]
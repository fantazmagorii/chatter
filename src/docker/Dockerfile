FROM amazoncorretto:11.0.9
ARG JAR_FILE
COPY ${JAR_FILE} chatapp.jar
ENTRYPOINT ["java","-jar","/chatapp.jar"]


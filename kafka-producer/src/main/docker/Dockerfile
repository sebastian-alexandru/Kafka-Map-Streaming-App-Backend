FROM openjdk:17-ea-7
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} producer.jar
ENTRYPOINT ["java", "-jar", "producer.jar"]
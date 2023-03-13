FROM openjdk:17-ea-7
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} consumer.jar
ENTRYPOINT ["java", "-jar", "consumer.jar"]
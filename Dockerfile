FROM adoptopenjdk
FROM maven
WORKDIR /app
COPY . /app
RUN mvn install
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]
FROM amazoncorretto:17-alpine-jdk

ENV SPRING_PROFILE=${SPRING_PROFILE}
ARG JAR_FILE=./backend/build/libs/atwoz-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE}", "-Xms620m", "-Xmx1024m", "-jar", "/app.jar"]

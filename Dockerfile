FROM gradle:7.1.1-jdk16-hotspot as build
COPY build.gradle.kts /home/gradle/
COPY settings.gradle.kts /home/gradle/
COPY ./src /home/gradle/src
WORKDIR /home/gradle
RUN gradle bootJar --no-daemon --no-build-cache

FROM openjdk:16-jdk-alpine
MAINTAINER steinhof
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app
COPY --from=build /home/gradle/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080

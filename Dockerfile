FROM gradle:8.2-jdk17 AS build
LABEL authors="stslex"
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :app:test --tests "com.stslex.TestAppConfig"
RUN gradle buildFatJar --no-daemon
RUN rm -rf /home/gradle/src/app/src/main/resources/application.conf

FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/app/build/libs/*.jar /app/ktor-docker-sample.jar
COPY --from=build /home/gradle/src/documentation/documentation.yaml /documentation/documentation.yaml
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]
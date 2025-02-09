FROM eclipse-temurin:21.0.6_7-jre-ubi9-minimal

WORKDIR /spring-kafka

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/*.jar app.jar 

EXPOSE 8080 

ENTRYPOINT ["java", "-jar", "app.jar"]


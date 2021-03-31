FROM openjdk:8-jre-alpine
EXPOSE 8084
WORKDIR /app
COPY target/loki-1.0.0.jar .
ENTRYPOINT [ "java", "-jar", "loki-1.0.0.jar" ]
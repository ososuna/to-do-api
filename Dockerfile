FROM openjdk:11
ENV datasource_url host.docker.internal
COPY ./target/todo-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3001
ENTRYPOINT [ "java", "-jar", "app.jar" ]
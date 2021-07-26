FROM openjdk
ADD target/metadata_task-0.0.1-SNAPSHOT.jar metadataPostgres.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "metadataPostgres.jar"]

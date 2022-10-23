FROM openjdk:8
EXPOSE 8090
ADD /target/location-velo-microserive-0.0.1-SNAPSHOT.jar Location-velo.jar
ENTRYPOINT ["java", "-jar", "Location-velo.jar"]

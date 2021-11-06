FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/reservations-1.0.jar msreservation.jar
ENTRYPOINT ["java","-jar","/msreservation.jar"]
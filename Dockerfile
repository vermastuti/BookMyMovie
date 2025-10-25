FROM openjdk:25
COPY ./target/BookMyMovie-0.0.1-SNAPSHOT.jar BookMyMovie-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/BookMyMovie-0.0.1-SNAPSHOT.jar"]
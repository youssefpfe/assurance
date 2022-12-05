FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
ADD target/assurance.jar assurance.jar
ENTRYPOINT ["java","-jar","/assurance.jar"]
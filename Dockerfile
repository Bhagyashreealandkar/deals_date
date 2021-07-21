FROM openjdk:11
EXPOSE 8082
ADD target/dealsdatefile.jar dealsdatefile.jar
ENTRYPOINT ["java","-jar","dealsdatefile.jar"]
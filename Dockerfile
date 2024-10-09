FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD build/libs/Homework_Spring_Boot_purpose_internal_structure_v1-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
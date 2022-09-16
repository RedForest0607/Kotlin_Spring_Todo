FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/ToDo-5.4.2.jar
COPY ${JAR_FILE} toDo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/toDo.jar"]
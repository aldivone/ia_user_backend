FROM amd64/openjdk:16-jdk
EXPOSE 8080
ARG JAR_FILE=target/usuario-backend-0.0.1.jar
ADD ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-jar","/hackend.jar"]

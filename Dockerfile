FROM openjdk:8
ADD target/SpringBootHelloWorld-0.0.1-SNAPSHOT.jar SpringBootHelloWorld-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar", "SpringBootHelloWorld-0.0.1-SNAPSHOT.jar", "--spring.datasource.url=jdbc:mysql://host.docker.internal:3306/demodb"]
FROM openjdk:8-jdk-alpine
LABEL maintainer="Mohammed Yehia <mohammedyehia99@gmail.com>"
LABEL developer="Mohammed Yehia"
EXPOSE 9090
ADD target/spring-boot-ecommerce.jar spring-boot-ecommerce.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-ecommerce.jar"]
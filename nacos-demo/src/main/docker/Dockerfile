#FROM openjdk:8-jre-alpine 注册中心
FROM hub.c.163.com/library/java:8-jdk
VOLUME /tmp
ADD *.jar app.jar
#RUN bash -c 'touch /app.jar'
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "-jar","/app.jar"]

#FROM openjdk:8-jdk-alpine
FROM hub.c.163.com/library/java:8-jdk
VOLUME /tmp

ADD *.jar app.jar

# copy arthas
#COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

#RUN bash -c 'touch /app.jar'

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

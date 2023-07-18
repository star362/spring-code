#基础镜像，如果本地没有，会从远程仓库拉取。
FROM openjdk:8-jdk-alpine

#镜像的制作人
MAINTAINER star362

# CentOS 容器和宿主机时间同步
RUN echo "Asia/Shanghai" > /etc/timezone

ENV SPRING_CONFIG_LOCATION = bootstrap-test.yml
ENV SPRING_PROFILES_ACTIVE = test

#在容器中创建挂载点，可以多个
VOLUME ["/tmp"]

#定义参数
#ARG JAR_FILE

# 拷贝本地文件到镜像中
#COPY ${JAR_FILE} app.jar

#jenkins
COPY   target/*.jar app.jar

#指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
ENTRYPOINT ["sh", "-c", "java \
              -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=2048m -Xms1024m -Xmx2048m -Xmn1024m -Xss512k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC  \
              -Djava.security.egd=file:/dev/./urandom \
              -jar  app.jar  \
              --spring.config.location=$SPRING_CONFIG_LOCATION --spring-profiles-active=$SPRING_PROFILES_ACTIVE"]

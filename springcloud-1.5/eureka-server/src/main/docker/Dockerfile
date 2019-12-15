#基础镜像信息
FROM openjdk:8-jdk-alpine

#指明该镜像的作者和其电子邮件
MAINTAINER "tinTin"<xxx@163.com>

#在构建镜像时，指定镜像的工作目录，之后的命令都是基于此工作目录，如果不存在，则会创建目录
#WORKDIR /opt
#WORKDIR app

#在新镜像内部执行的命令，比如安装一些软件、配置一些基础环境，可使用\来换行
RUN echo 'hello docker!'

#用来向基于镜像创建的容器添加卷。比如你可以将mongodb镜像中存储数据的data文件指定为主机的某个文件
#注意:VOLUME 主机目录 容器目录
#VOLUME  /Users/ding/docker/myapp   /home

WORKDIR /home
#将主机的文件复制到镜像中，跟COPY一样，限制条件和使用方式都一样
ADD eureka-1.0-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

#暴露镜像的端口供主机做映射，启动镜像时，使用-P参数来讲镜像端口与宿主机的随机端口做映射
EXPOSE 8100

#容器启动时需要执行的命令,CMD的命令会被 docker run 的命令覆盖而ENTRYPOINT不会
#CMD java -jar app.jar

#容器启动时需要执行的命令,CMD的命令会被 docker run 的命令覆盖而ENTRYPOINT不会
#ENTRYPOINT  exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/app.jar
ENTRYPOINT exec java -jar /home/app.jar
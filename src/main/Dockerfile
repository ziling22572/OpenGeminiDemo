# 使用官方 OpenJDK 8 镜像作为基础镜像
FROM openjdk:8-jdk-alpine

# 作者信息（可选）
LABEL maintainer="your-email@example.com"

# 设置应用程序的工作目录
WORKDIR /app

# 将 Maven 构建生成的 jar 文件复制到镜像中
COPY target/open-gemini-demo-1.0-SNAPSHOT.jar app.jar

# 运行应用程序时的 JVM 参数（可以根据需要调整）
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# 启动容器时运行的命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# 暴露 Spring Boot 的默认端口
EXPOSE 8080

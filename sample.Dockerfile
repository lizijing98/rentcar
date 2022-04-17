FROM ubuntu:18.04
WORKDIR /app
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y curl sudo vim screen openjdk-8-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
COPY ./target/rentcar-1.0.jar /app/rentcar-1.0.jar
COPY ./src/main/resources/application.yml /app
COPY ./src/main/resources/application-dev.yml /app
COPY ./src/main/resources/application-docker.yml /app
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
EXPOSE 8002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/app/rentcar-1.0.jar"]
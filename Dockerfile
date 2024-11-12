FROM alpine:latest as builder

RUN apk update && \
    apk add --no-cache \
    openjdk21 \
    maven \
    bash \
    curl \
    tar \
    wget \
    && rm -rf /var/cache/apk/*

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM alpine:latest as runtime

RUN apk update && \
    apk add --no-cache \
    openjdk21 \
    nginx \
    && rm -rf /var/cache/apk/*

RUN wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.33/src/apache-tomcat-10.1.33-src.tar.gz && \
    tar -xzvf apache-tomcat-10.1.33-src.tar.gz && \
    mv apache-tomcat-10.1.33-src.tar.gz /usr/local/tomcat

COPY --from=builder /app/target/saySalut-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/mobileApp.war
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80 8080

CMD ["sh", "-c", "nginx && /usr/local/tomcat/bin/catalina.sh run"]
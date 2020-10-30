FROM openjdk:8u272-jdk-slim as builder

RUN apt-get update -qqy \
  && apt-get install -qqy \
   --no-install-recommends \
   curl \
   ca-certificates \
   unzip

ENV MAVEN_VER=3.6.3

RUN mkdir -p /opt/ && cd /opt/ \
    && echo "Downloading maven.." \
    && curl -sSfL "https://downloads.apache.org/maven/maven-3/${MAVEN_VER}/binaries/apache-maven-${MAVEN_VER}-bin.tar.gz" -o maven-$MAVEN_VER-bin.tar.gz \
    && tar -xzf maven-${MAVEN_VER}-bin.tar.gz \
    && rm maven-${MAVEN_VER}-bin.tar.gz

# Export some environment variables
ENV MAVEN_HOME=/opt/apache-maven-$MAVEN_VER/
ENV PATH=$PATH:$MAVEN_HOME/bin

RUN mkdir -p /home/app/libs

WORKDIR /home/app

COPY . /home/app/

RUN mvn clean package -f function/pom.xml
RUN find . 

FROM openfaas/of-watchdog:0.7.6 as watchdog

FROM openjdk:8u272-jre-slim as ship
RUN apt-get update -qqy \
  && apt-get install -qqy \
   --no-install-recommends \
   unzip
RUN addgroup --system app \
    && adduser --system --ingroup app app

COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
RUN chmod +x /usr/bin/fwatchdog

WORKDIR /home/app
COPY --from=builder /home/app/function/target/distributions/function.zip ./function.zip
user app
RUN unzip ./function.zip

WORKDIR /home/app/

ENV upstream_url="http://127.0.0.1:8082"
ENV mode="http"
ENV CLASSPATH="/home/app/function/function.jar:/home/app/function/lib/*"

ENV fprocess="java -XX:+UseContainerSupport com.openfaas.entrypoint.App"
EXPOSE 8080

HEALTHCHECK --interval=5s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]

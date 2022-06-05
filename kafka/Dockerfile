FROM openjdk:11.0.10-jre-buster

RUN apt-get update && \
    apt-get -y install curl
ENV KAFKA_VERSION 3.2.0
ENV SCALA_VERSION 2.13

RUN  mkdir /tmp/kafka && \
     curl "https://dlcdn.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz" -o /tmp/kafka/kafka.tgz && \
   mkdir /kafka && cd /kafka/ &&\
   tar -zxvf /tmp/kafka/kafka.tgz --strip 1

COPY start-kafka.sh  /usr/bin
RUN chmod +x  /usr/bin/start-kafka.sh

CMD ["start-kafka.sh"]

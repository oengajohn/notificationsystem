package com.systech.notificationsystem.kafka;

import org.aerogear.kafka.cdi.annotation.KafkaConfig;

//@KafkaConfig(bootstrapServers = "kafka:9092")
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class Config {

    static final String SMS_TOPIC_ENTITY = "sms";

    static final String SMS_GROUP_ID_ENTITY = "sms_group";

    static final String EMAIL_TOPIC_ENTITY = "mails";

    static final String EMAIL_GROUP_ID_ENTITY = "mails_group";



}
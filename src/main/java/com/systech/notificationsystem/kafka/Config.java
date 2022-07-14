package com.systech.notificationsystem.kafka;

import org.aerogear.kafka.cdi.annotation.KafkaConfig;

//@KafkaConfig(bootstrapServers = "kafka:9092")
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class Config {


    static final String TO_NS_TOPIC_SMS = "to-ns-sms";
    static final String TO_NS_SMS_GROUP_ID_ENTITY = "to-ns-sms-group";
    static final String TO_FUND_MASTER_SMS_TOPIC_ENTITY = "to-fund-master-sms";


    static final String TO_NS_TOPIC_EMAIL = "to-ns-emails";
    static final String TO_NS_EMAIL_GROUP_ID_ENTITY = "to-ns-email-group";
    static final String TO_FUND_MASTER_EMAIL_TOPIC_ENTITY = "to-fund-master-emails";






}
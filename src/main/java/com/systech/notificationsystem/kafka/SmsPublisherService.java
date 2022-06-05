package com.systech.notificationsystem.kafka;

import com.systech.notificationsystem.util.JsonUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.Producer;

@ApplicationScoped
public class SmsPublisherService {

    @Producer
    private SimpleKafkaProducer<Integer, JsonObject> producer;

    public <T> void sendMessage(T entity) {
        producer.send(Config.SMS_TOPIC_ENTITY, JsonUtils.toJson(entity));
    }

}
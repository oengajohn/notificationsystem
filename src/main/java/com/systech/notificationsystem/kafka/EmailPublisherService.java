package com.systech.notificationsystem.kafka;

import com.systech.notificationsystem.util.JsonUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.Producer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;

@ApplicationScoped
public class EmailPublisherService {
    @Inject
    private Logger log;

    @Producer
    private SimpleKafkaProducer<Integer, JsonObject> producer;

    public <T> void sendMessage(T entity) {
        log.info("::::Publishing email to fund master for update::::");
        producer.send(Config.TO_FUND_MASTER_EMAIL_TOPIC_ENTITY, JsonUtils.toJson(entity), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e==null){
                    log.info("Record metadata {}",recordMetadata.topic());
                }
            }
        });
    }

}
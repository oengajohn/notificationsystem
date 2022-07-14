# Notification System

Zookeeper

```bat
 bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

Kafka Cluster

```console
 bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

Topic

```console
bin/kafka-topics.sh --create --topic to-ns-sms --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
bin/kafka-topics.sh --create --topic to-fund-master-sms --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
bin/kafka-topics.sh --create --topic to-ns-emails --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
bin/kafka-topics.sh --create --topic to-fund-master-emails --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
		
  

```

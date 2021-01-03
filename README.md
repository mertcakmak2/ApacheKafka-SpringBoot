# ApacheKafka-SpringBoot
Producer-Consumer

https://kafka.apache.org/downloads Binary Downloads 2.12

Her komut bin\windows dizininde

zookeeper start
-----------------------------
zookeeper-server-start.bat ..\..\config\zookeeper.properties


Kafka server start
-----------------------------
kafka-server-start.bat ..\..\config\server.properties


Topic olusturma
-----------------------------
kafka-topics.bat --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4


Console Producer olusturma
-----------------------------
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic


Console Consumer olusturma
-----------------------------
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning


Topic listesi
-----------------------------
kafka-topics.bat --zookeeper localhost:2181 --list

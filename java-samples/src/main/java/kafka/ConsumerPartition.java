package kafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerPartition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.195:9092");
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		//group id is required
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, "test_group_id");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

		//subscribe test topic partition
		List<TopicPartition> topicPartitions = new ArrayList<>();
		topicPartitions.add(new TopicPartition("test",0));
		consumer.assign(topicPartitions);
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
			for(ConsumerRecord<String, String> record: records) {
				System.out.println(record);
			}
		}
	}

}

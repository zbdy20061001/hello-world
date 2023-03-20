package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerTx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.195:9092");
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//needed when begin transaction
		prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transction_id_can_be_anything");

		KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
		// Transaction
		producer.initTransactions();
		producer.beginTransaction();

		try {

			for (int i = 0; i < 5; i++) {
				System.out.println("Sending " + i + " mesasge to kafka");
				producer.send(new ProducerRecord<String, String>("test", "hello" + i));
			}
			producer.commitTransaction();
		} catch (Exception e) {
			producer.abortTransaction();
		}finally {
			producer.close();
		}
	}

}

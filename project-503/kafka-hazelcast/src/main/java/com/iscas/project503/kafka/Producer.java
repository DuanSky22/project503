package com.iscas.project503.kafka;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.util.ProducerConfigParser;
import com.iscas.project503.util.TopicFactory;

public class Producer {

	private static Properties prop = new Properties();
	private static int separateTime = 1000; // the speed of produce message
	private static int topicNums = 2;
	private static String[] topics;
	@SuppressWarnings("rawtypes")
	private static kafka.javaapi.producer.Producer producer = null;

	private static int termNums = 5;
	private static int airTemperature = 40;
	private static int airHumidity = 25;
	private static int soilTemperature = 30;
	private static int soilHumidity = 35;
	private static int light = 200;
	private static int CO2Concentration = 10;
	private static long streamNumber = 0;

	private static long dataKeyCounter = 0;

	public static void main(String args[]) {
		Producer.produce();
	}

	private static void loadConfig() {
		Properties temp = ProducerConfigParser.getInstance()
				.getProducerConfig();
		Object tes = temp.get("metadata.broker.list");
		prop.put("metadata.broker.list", temp.get("metadata.broker.list"));
		prop.put("serializer.class", temp.get("serializer.class"));
		prop.put("key.serializer.class", temp.get("key.serializer.class"));
		prop.put("request.required.acks", temp.get("request.required.acks"));
		String time = temp.getProperty("produce.separater.time");
		if (time != null)
			separateTime = Integer.parseInt(time);
		String topicNumber = temp.getProperty("produce.topic.number");
		if (topicNumber != null)
			topicNums = Integer.parseInt(topicNumber);
	}

	@SuppressWarnings("unchecked")
	public static void produce() {
		loadConfig();
		producer = new kafka.javaapi.producer.Producer<String, String>(
				new ProducerConfig(prop));
		topics = TopicFactory.newTopics(topicNums);

		while (true) {
			streamNumber++;
			dataKeyCounter++;
			String topic = TopicFactory.produceTopic();
			String dataMessage = new MessageFactory(topic, termNums,
					streamNumber + "", airTemperature, airHumidity,
					soilTemperature, soilHumidity, light, CO2Concentration)
					.getMessage();
			try {
				Thread.sleep(separateTime);
				//System.out.println("topic   " + topic +"   dataKeyCounter  " + dataKeyCounter+ "   send message :"+ dataMessage);
				producer.send(new KeyedMessage<String, String>(topic,dataKeyCounter+"", dataMessage));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String[] getTopics() {
		return topics;
	}
}

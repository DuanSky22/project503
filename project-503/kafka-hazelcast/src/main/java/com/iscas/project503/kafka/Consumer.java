package com.iscas.project503.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.iscas.project503.util.Project503String;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class Consumer implements Runnable {

	private Properties prop;
	private ConsumerConfig config = null;
	private final ConsumerConnector consumer;

	public Consumer(Properties prop) {
		loadConfig(prop);
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
	}

	public void loadConfig(Properties prop) {
		this.prop = prop;
		config = new ConsumerConfig(prop);
	}

	public void run() {
		String topic = prop.getProperty(Project503String.TOPIC);
		String streamNums = prop.getProperty(Project503String.STREAM_NUMS);
		if (streamNums == null)
			throw new IllegalArgumentException(
					"Server can not get stream number!");
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

		topicCountMap.put(topic, Integer.parseInt(streamNums));

		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(
				new VerifiableProperties());

		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer
				.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

		System.out.println("----consumer----start!");
		ExecutorService executor = Executors.newCachedThreadPool();
		for (KafkaStream<String, String> stream : consumerMap.get(topic)) {
			executor.execute(new Stream(stream,topic));
		}
		System.out.println("----consumer----done!");
	}

	public String[] decodeMessage(String message) {
		return message.split(Project503String.INNER_SPLIT);
	}
}

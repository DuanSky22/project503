package com.iscas.project503.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import com.hazelcast.core.MultiMap;

public class ConsumerThread implements Runnable {

	private String topic;
	private  MultiMap<String, String> topicMap;
	private KafkaStream<String, String> stream;
	
	public ConsumerThread(String topic, MultiMap<String, String> topicMap,
			KafkaStream<String, String> stream) {
		super();
		this.topic = topic;
		this.topicMap = topicMap;
		this.stream = stream;
	}
	public void run() {
		for(ConsumerIterator<String, String> it = stream.iterator();it.hasNext();){
    		MessageAndMetadata<String, String> data=it.next();
    		System.out.println("rescive "+topic+" partition "+data.partition()+" message :"+data.key()+" "+data.message());
    		topicMap.put(data.key(),data.message());
    	}
	}
}

package com.iscas.project503.kafka;

import com.iscas.project503.hazelcast.StoreMessage;
import com.iscas.project503.hazelcast.StoreMessageInHazelcast;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class Stream implements Runnable{
	
	public static StoreMessage storeMessage=new StoreMessageInHazelcast();
	
	private KafkaStream<String, String> stream=null;
	
	private String topic=null;
	
	public Stream(KafkaStream<String, String> stream,String topic){
		this.stream=stream;
		this.topic=topic;
	}

	public void run() {
		for (ConsumerIterator<String, String> it = stream.iterator(); it.hasNext();){
			MessageAndMetadata<String, String> message = it.next();
			System.out.println("receive "+message.topic()+" "+message.message());
			storeMessage.store(message.topic(),message.message());
		}
	}

}

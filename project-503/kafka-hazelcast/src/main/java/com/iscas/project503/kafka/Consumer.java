package com.iscas.project503.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;
import com.hazelcast.instance.HazelcastInstanceFactory;
import com.hazelcast.instance.HazelcastInstanceImpl;
import com.iscas.project503.util.Project503String;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class Consumer implements Runnable {
	
	private  Properties prop;
	private  ConsumerConfig config=null;
	private final ConsumerConnector consumer;
	
	public Consumer(Properties prop){
		loadConfig(prop);
		consumer=kafka.consumer.Consumer.createJavaConsumerConnector(config);
	}
	
	public void loadConfig(Properties prop){
		this.prop=prop;
		config=new ConsumerConfig(prop);
	}

	public void run() {
		String topic=prop.getProperty(Project503String.TOPIC);
		String streamNums=prop.getProperty(Project503String.STREAM_NUMS);
		if(streamNums==null)
			throw new IllegalArgumentException("Server can not get stream number!");
		Map<String,Integer> topicCountMap=new HashMap<String,Integer>();
		topicCountMap.put(topic, Integer.parseInt(streamNums));
		
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
 
        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        
        HazelcastInstance client=Hazelcast.newHazelcastInstance();
        MultiMap<String, String> topicMap=client.getMultiMap(topic);
        System.out.println("----consumer----start!");
        ExecutorService executor=Executors.newCachedThreadPool();
        for( KafkaStream<String, String> stream : consumerMap.get(topic)){
        	//for a specific stream
         	Runnable consumerThread = new ConsumerThread(topic,topicMap,stream); 
    	    executor.execute(consumerThread);
        	
        }
        System.out.println("----consumer----done!");
	}
	
	public String[] decodeMessage(String message){
		return message.split(Project503String.INNER_SPLIT);
	}
}

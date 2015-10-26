package com.iscas.project503.kafka;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.util.ProducerConfigParser;
import com.iscas.project503.util.TopicFactory;

public class Producer {
	
	private static Properties prop=new Properties();
	private static int separateTime=1000; //the speed of produce message
	private static int topicNums=2;
	private static String[] topics;
	@SuppressWarnings("rawtypes")
	private static kafka.javaapi.producer.Producer producer=null;
	
	public static void main(String args[]){
		Producer.produce();
	}
	
	private static void loadConfig(){
		Properties temp=ProducerConfigParser.getInstance().getProducerConfig();
		Object tes=temp.get("metadata.broker.list");
		prop.put("metadata.broker.list", temp.get("metadata.broker.list"));
		prop.put("serializer.class", temp.get("serializer.class"));
		prop.put("key.serializer.class", temp.get("key.serializer.class"));
		prop.put("request.required.acks", temp.get("request.required.acks"));
		String time=temp.getProperty("produce.separater.time");
		if(time!=null)
			separateTime=Integer.parseInt(time);
		String topicNumber=temp.getProperty("produce.topic.number");
		if(topicNumber!=null)
			topicNums=Integer.parseInt(topicNumber);
	}
	
	@SuppressWarnings("unchecked")
	public static void produce(){
		loadConfig();
		producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(prop));
		
		topics=TopicFactory.newTopics(topicNums);
		//TODO 去 props里面拿topics
		topicNums=topics.length;    ///TODO maybe the factory cannot return much topic.
		int currentTopic=0;
		long key=0;
		long maxKeyValue=Long.MAX_VALUE;
		while(true){
			try {
				Thread.sleep(separateTime);
				String dataKey = String.valueOf((key++)%maxKeyValue);
	            String dataMessage = MessageFactory.getNextOridinalNumber();
	            System.out.println("topic "+topics[(currentTopic++)%topicNums]+" send message :"+dataKey+" "+dataMessage);
	            producer.send(new KeyedMessage<String, String>(topics[(currentTopic)%topicNums], dataKey ,dataMessage));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[] getTopics(){
		return topics;
	}	

	
}

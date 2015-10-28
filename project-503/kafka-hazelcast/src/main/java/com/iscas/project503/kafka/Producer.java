package com.iscas.project503.kafka;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.iscas.project503.kafka.topic.TopicFactory;
import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.util.ProducerConfigParser;
import com.iscas.project503.util.Project503String;

public class Producer {
	
	private  Properties prop=new Properties();
	private  int separateTime=1000; //the speed of produce message
	private  int topicNums;
	private  String[] topics;
	@SuppressWarnings("rawtypes")
	private static kafka.javaapi.producer.Producer producer=null;
	
	public static void main(String args[]){
		Producer p=new Producer();
		p.produce();
	}
	
	public Producer(){
		loadConfig();
	}
	
	private  void loadConfig(){
		Properties temp=ProducerConfigParser.getInstance().getProducerConfig();
		prop.put("metadata.broker.list", temp.get("metadata.broker.list"));
		prop.put("serializer.class", temp.get("serializer.class"));
		prop.put("key.serializer.class", temp.get("key.serializer.class"));
		prop.put("request.required.acks", temp.get("request.required.acks"));
		
		String time=temp.getProperty("produce.separater.time");
		separateTime=Integer.parseInt(time);
		
		topicNums=(int) temp.get(Project503String.TOPIC_COUNT);
		
		topics=new String[topicNums];
		for(int i=1;i<=topicNums;i++){
			topics[i-1]=temp.getProperty(Project503String.TOPIC+i);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void produce(){
		producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(prop));
		int currentTopic=0;
		long key=0;
		while(true){
			try {
				Thread.sleep(separateTime);
				currentTopic=(++currentTopic)%topicNums;
				String dataKey = String.valueOf(++key);
	            String dataMessage = TopicFactory.getNextMessage(topics[currentTopic]);
	            System.out.println("topic "+topics[currentTopic]+" send message :"+dataKey+" "+dataMessage);
	            producer.send(new KeyedMessage<String, String>(topics[currentTopic], dataKey ,dataMessage));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[] getTopics(){
		return topics;
	}
	
	

}
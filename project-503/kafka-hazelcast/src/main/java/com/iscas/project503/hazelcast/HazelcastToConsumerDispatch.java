package com.iscas.project503.hazelcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import java.util.concurrent.Future;

import com.iscas.project503.kafka.Consumer;
import com.iscas.project503.util.ConsumerConfigParser;
import com.iscas.project503.util.Project503String;


public class HazelcastToConsumerDispatch {
	
	private static int threadNum=0;
	
	public static void main(String args[]){
		storeInHazelcast();
	}

	public static void storeInHazelcast(){
		List<Properties> props=getProperties();
		ExecutorService service=Executors.newFixedThreadPool(threadNum);
		
		for(int i=0;i<threadNum;i++){
			service.execute(new Consumer(props.get(i)));
		} 
	}
	
	public static List<Properties> getProperties(){
		Properties prop=ConsumerConfigParser.getInstance().getConsumerConfig();
		List<Properties> result=new ArrayList<Properties>();
		int topicNumber=(Integer)prop.get(Project503String.TOPIC_COUNT);
		threadNum=topicNumber;
		String zookeeperConnect=prop.getProperty(Project503String.ZOOKEEPER_CONNECT);
		String timeOut=prop.getProperty(Project503String.ZOOKEEPER_SESSION_TIMEOUT);
		String groupId=prop.getProperty(Project503String.GROUP_ID)+Math.random();
		String autoCommit=prop.getProperty(Project503String.AUTO_COMMIT_INTERVAL);
		String offset=prop.getProperty(Project503String.AUTO_OFFSET_RESET);
		String serializer=prop.getProperty(Project503String.SERIALIZER_CLASS);
		
		for(int i=0;i<topicNumber;i++){
			Properties topicProp=new Properties();
			
			topicProp.put(Project503String.ZOOKEEPER_CONNECT,zookeeperConnect);
			topicProp.put(Project503String.ZOOKEEPER_SESSION_TIMEOUT,timeOut);
			topicProp.put(Project503String.GROUP_ID,groupId);
			topicProp.put(Project503String.AUTO_COMMIT_INTERVAL,autoCommit);
			topicProp.put(Project503String.AUTO_OFFSET_RESET,offset);
			topicProp.put(Project503String.SERIALIZER_CLASS,serializer);
			
			String topicName=(String) prop.get(Project503String.TOPIC+(i+1));
			topicProp.put(Project503String.TOPIC, topicName);
			topicProp.put(Project503String.STREAM_NUMS, prop.get(topicName));
			
			result.add(topicProp);
		}
		return result;
	}
}

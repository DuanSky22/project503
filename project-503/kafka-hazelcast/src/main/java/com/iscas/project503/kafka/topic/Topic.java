package com.iscas.project503.kafka.topic;

public abstract class Topic {
	
	private String topic="unknown";
	
	public Topic(){
		
	}
	
	public Topic(String topic){
		this.topic=topic;
	}
	
	public  abstract String getNextMessage();

}

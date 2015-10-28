package com.iscas.project503.kafka.topic;

public abstract class Topic {
	
	private String topic;
	
	
	public Topic(String topic){
		this.topic=topic;
	}
	
	public abstract String getNextMessage();

}

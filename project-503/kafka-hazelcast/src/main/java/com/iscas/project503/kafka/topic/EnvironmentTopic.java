package com.iscas.project503.kafka.topic;

public class EnvironmentTopic extends Topic{
	
	private static long termID=1;

	public EnvironmentTopic(String topic) {
		super(topic);
	}

	@Override
	public String getNextMessage() {
		String message="";
		return null;
	}

}

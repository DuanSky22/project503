package com.iscas.project503.kafka.topic;

import static com.iscas.project503.util.Project503String.*;

public class TopicFactory {
	
	public String getNextMessage(String topic){
		switch(topic){
		case TOPIC_ENVIRONMENTINFO:
			return new EnvironmentTopic().getNextMessage();
		case TOPIC_EVVIRONMENTALARM:
			return new EnvironmentAlarmTopic().getNextMessage();
		case TOPIC_SCALEINFO:
			return new ScaleInfoTopic().getNextMessage();
		case TOPIC_WEATHERINFO:
			return new WeatherInfoTopic().getNextMessage();
		case TOPIC_ASYNSENDRESPONSE:
			return new AsynSendResponseTopic().getNextMessage();
		default:
			return "";
		}
	}

}
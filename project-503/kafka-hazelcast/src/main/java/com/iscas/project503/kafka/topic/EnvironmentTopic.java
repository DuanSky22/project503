package com.iscas.project503.kafka.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.kafka.schema.EnvironmentInfo;
import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.util.Project503String;

public class EnvironmentTopic extends Topic{
	
	private static long termID=1;
	

	public EnvironmentTopic() {
		super(Project503String.ENVINFO);
	}

	@Override
	public String getNextMessage() {
		EnvironmentInfo envinfo=new EnvironmentInfo();
		envinfo.setAirHumidity(MessageFactory.getNextRandomNumber(2));
		envinfo.setAirTemperature(MessageFactory.getNextRandomNumber(2));
		envinfo.setAlarmSource(MessageFactory.getNextRandomNumber());
		envinfo.setCO2Concentration(MessageFactory.getNextRandomNumber(2));
		envinfo.setLight(MessageFactory.getNextRandomNumber(2));
		envinfo.setSoilHumidity(MessageFactory.getNextRandomNumber(2));
		envinfo.setSoilTemperature(MessageFactory.getNextRandomNumber(2));
		envinfo.setStreamNumber(MessageFactory.getNextRandomAlpha(6));
		envinfo.setTermID((termID++)+"");
		envinfo.setTimeStamp(MessageFactory.getCurrentDate());
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(envinfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]){
		System.out.println(new EnvironmentTopic().getNextMessage());
		System.out.println(new EnvironmentTopic().getNextMessage());
	}

}

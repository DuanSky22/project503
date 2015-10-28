package com.iscas.project503.kafka.topic;

import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.kafka.schema.AlarmInfo;
import com.iscas.project503.kafka.schema.EnvironmentAlarm;
import com.iscas.project503.util.MessageFactory;

public class EnvironmentAlarmTopic extends Topic {

	private static long termID=1;
	@Override
	public String getNextMessage() {
		
		EnvironmentAlarm ea=new EnvironmentAlarm();
		ea.setAirHumidity(MessageFactory.getNextRandomNumber(2));
		ea.setAirTemperature(MessageFactory.getNextRandomNumber(2));
		ea.setCO2Concentration(MessageFactory.getNextRandomNumber(2));
		ea.setLight(MessageFactory.getNextRandomNumber(2));
		ea.setSoilHumidity(MessageFactory.getNextRandomNumber(2));
		ea.setSoilTemperature(MessageFactory.getNextRandomNumber(2));
		ea.setStreamNumber(MessageFactory.getNextRandomNumber(6));
		ea.setTermID((termID++)+"");
		ea.setTimeStamp(MessageFactory.getCurrentDate());
		AlarmInfo[] list=ea.getAlarmInfo();
		for(int i=0;i<list.length;i++){
			list[i]=AlarmTopic.newAlarmInfo();
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(ea);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]){
		System.out.println(new EnvironmentAlarmTopic().getNextMessage());
	}
	

}

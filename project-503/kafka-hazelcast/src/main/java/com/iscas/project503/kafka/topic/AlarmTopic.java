package com.iscas.project503.kafka.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.kafka.schema.AlarmInfo;
import com.iscas.project503.util.Project503String;

public class AlarmTopic extends Topic{
	
	private static long termID=1;
	
	public AlarmTopic(){
		super(Project503String.ENVINFO);
	}
	
	public static AlarmInfo newAlarmInfo(){
		AlarmInfo alarm=new AlarmInfo();
		alarm.setAlarmDesp(MessageFactory.getNextRandomNumber());
		alarm.setAlarmGrade(MessageFactory.getNextRandomNumber(1));
		alarm.setAlarmId(MessageFactory.getNextRandomNumber(6));
		alarm.setAlarmTime(MessageFactory.getCurrentDate());
		alarm.setAlarmType(MessageFactory.getNextAlarmType());
		alarm.setHandler(MessageFactory.getNextRandomAlpha());
		alarm.setHandleStatus(MessageFactory.getNextRandomAlpha(1));
		alarm.setHandleTime(MessageFactory.getCurrentDate());
		alarm.setTermID((termID++) +"");
		return alarm;
	}
	
	@Override
	public String getNextMessage(){
		AlarmInfo alarm=new AlarmInfo();
		alarm.setAlarmDesp(MessageFactory.getNextRandomNumber());
		alarm.setAlarmGrade(MessageFactory.getNextRandomNumber(1));
		alarm.setAlarmId(MessageFactory.getNextRandomNumber(6));
		alarm.setAlarmTime(MessageFactory.getCurrentDate());
		alarm.setAlarmType(MessageFactory.getNextAlarmType());
		alarm.setHandler(MessageFactory.getNextRandomAlpha());
		alarm.setHandleStatus(MessageFactory.getNextRandomAlpha(1));
		alarm.setHandleTime(MessageFactory.getCurrentDate());
		alarm.setTermID((termID++) +"");
		
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(alarm);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}

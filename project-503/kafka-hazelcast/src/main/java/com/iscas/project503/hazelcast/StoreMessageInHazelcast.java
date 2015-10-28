package com.iscas.project503.hazelcast;

import static com.iscas.project503.util.Project503String.*;

import java.util.HashMap;
import java.util.Map;

import com.hazelcast.core.HazelcastInstance;
import com.iscas.project503.util.Project503HazelcastClient;

public class StoreMessageInHazelcast implements StoreMessage{
	
	HazelcastInstance client=Project503HazelcastClient.getInstance();

	public void store(String topic,String message) {
		switch(topic){
		case TOPIC_ENVIRONMENTINFO:
			storeEnvironmentInfo(message);
			break;
		case TOPIC_EVVIRONMENTALARM:
			storeEnvironmentAlarm(message);
			break;
		case TOPIC_SCALEINFO:
			storeScaleInfo(message);
			break;
		case TOPIC_WEATHERINFO:
			storeWeatherInfo(message);
			break;
		case TOPIC_ASYNSENDRESPONSE:
			storeAsynSendResponse(message);
			break;
		}
	}
	
	// environmentinfo topic <=> envinfo hazelcast
	public void storeEnvironmentInfo(String message){
		if(message==null || message.length()==0) return ;
		Map<String,String> map=client.getMap(ENVINFO);
		map.put(findKey(ENVINFO,message), message);
	}
	
	//environmentinfo topic <=> envinfo & alarm hazelcast
	public void storeEnvironmentAlarm(String message){
		if(message==null || message.length()==0) return ;
		//store envinfo in hazelcast
		Map<String,String> envinfo=client.getMap(ENVINFO);
		envinfo.put(findKey(ENVINFO,message), message);
		//store alarm in hazelcast
		storeAlarm(message);
	}
	
	public void storeAlarm(String message){
		int start=message.indexOf(ALARMINFO);
		if(start==-1)
			return;
		int left=message.indexOf(MESSAGE_JSON_LEFT_BRACE,start);
		int right=message.indexOf(MESSAGE_JSON_RIGHT_BRACE,start);
		Map<String,Map<String,String>> alarminfo=client.getMap(ALARMINFO);
		while(left!=-1 && right!=-1){
			String alarmMessage=message.substring(left,right+1); //here we get alarm info.
			String alarmType=findKey(MESSAGE_ALARM_TYPE,alarmMessage);
			Map<String,String> typeMap=new HashMap<String,String>();
			typeMap.put(alarmType, alarmMessage);
			alarminfo.put(findKey(ALARMINFO,alarmMessage), typeMap);
			left=message.indexOf(MESSAGE_JSON_LEFT_BRACE,left);
			right=message.indexOf(MESSAGE_JSON_RIGHT_BRACE,right);
		}
	}
	
	public void storeScaleInfo(String message){
		if(message==null || message.length()==0) return;
		Map<String,String> map=client.getMap(TOPIC_SCALEINFO);
		map.put(findKey(MESSAGE_PLOT_ID,message), message);
	}
	
	//TODO what the fuck???
	public void storeWeatherInfo(String message){
		if(message==null || message.length()==0) return;
		Map<String,String> map=client.getMap(WEATHER);
		map.put(findKey(MESSAGE_TERM_ID,message), message);
	}
	
	public void storeAsynSendResponse(String message){
		if(message==null || message.length()==0) return;
		Map<String,String> map=client.getMap(TOPIC_ASYNSENDRESPONSE);
		map.put(findKey(MESSAGE_TERM_ID,message), message);
	}
	
	private String findKey(String keyName,String message){
		String key="";
		if(message==null || message.length()==0)
			return key;
		int start=message.indexOf(keyName);
		if(start==-1)
			return key;
		int a,b;
		if((a=message.indexOf(MESSAGE_JSON_COMMA))!=-1 && (b=message.indexOf(MESSAGE_JSON_RIGHT_BRACE))!=-1){
			key=message.substring(start+keyName.length()+1,Math.min(a, b));
		}
		return key;
	}

}

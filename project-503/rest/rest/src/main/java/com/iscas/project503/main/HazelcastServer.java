package com.iscas.project503.main;

import java.util.HashMap;
import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.iscas.project503.util.Project503RestString;

public class HazelcastServer {
	
	private static HazelcastInstance node=Hazelcast.newHazelcastInstance();
	
	public static void main(String args[]){
		generateWeather();
		generateEnvinfo();
		generateAlarminfo();
		
	}
	
	public static void generateWeather(){
		Map<String,String> map=node.getMap(Project503RestString.WEATHER);
		map.put(Project503RestString.WEATHER_KEY, "windSpeed:1;rain");
	}
	
	public static void generateEnvinfo(){
		Map<String,String> map=node.getMap(Project503RestString.ENVINFO);
		for(int i=0;i<100;i++){
			map.put(i+"", "environment message"+i);
		}
	}
	
	public static void generateAlarminfo(){
		IMap<String,Map<String,String>> map=node.getMap(Project503RestString.ALARMINFO);
		for(int i=0;i<100;i++){
			Map<String,String> message=new HashMap<String,String>();
			for(int j=0;j<4;j++){
				message.put("field_"+j, "fieldvalue_"+j);
			}
			map.put(i+"", message);
		}
	}
}

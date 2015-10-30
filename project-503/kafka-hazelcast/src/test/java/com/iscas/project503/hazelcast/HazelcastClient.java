package com.iscas.project503.hazelcast;

import java.util.Map;
import java.util.Map.Entry;

import com.hazelcast.core.HazelcastInstance;

import static com.iscas.project503.util.Project503String.*;

public class HazelcastClient {
	
	private static HazelcastInstance client=com.hazelcast.client.HazelcastClient.newHazelcastClient();
	
	public static void main(String args[]){
		//HazelcastClient.getEnvinfo();
		HazelcastClient.getAlarm();
		//HazelcastClient.getWeather();
	}
	
	public static void getEnvinfo(){
		System.out.println("=====================environment information in hazelcast==================================");
		Map<String,String> map=client.getMap(ENVINFO);
		for(Entry<String,String> entry : map.entrySet()){
			System.out.println("hazelcast "+ENVINFO+":"+entry.getKey() + entry.getValue());
		}
	}
	
	public static void getWeather(){
		System.out.println("=====================weather information in hazelcast==================================");
		Map<String,String> map=client.getMap(WEATHER);
		for(Entry<String,String> entry : map.entrySet()){
			System.out.println("hazelcast "+WEATHER+":"+entry.getKey() + entry.getValue());
		}
	}
	
	public static void getAlarm(){
		System.out.println("=====================alarm information in hazelcast==================================");
		Map<String,Map<String,String>> map=client.getMap(ALARMINFO);
		for(Entry<String,Map<String,String>> entry : map.entrySet()){
			System.out.print("hazelcast "+ALARMINFO+":"+entry.getKey());
			for(Entry<String,String> type : entry.getValue().entrySet()){
				System.out.print(" "+entry.getKey()+" "+entry.getValue());
			}
			System.out.println();
		}
	}

}

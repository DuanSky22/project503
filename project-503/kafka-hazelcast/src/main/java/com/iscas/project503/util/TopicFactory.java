package com.iscas.project503.util;

public class TopicFactory {
	
	public static int current=0;
	
	public static String nextTopic(){
		return Topic.values()[(current++) % Topic.values().length].getDescription();
	}
	
	public static String newTopic(Topic topic){
		String result=null;
		switch(topic){
		case HUMIDITY : 
			result=Topic.HUMIDITY.getDescription();
			break;
		case TEMPERATURE:
			result=Topic.TEMPERATURE.getDescription();
			break;
		case WATER:
			result=Topic.WATER.getDescription();
		case SUNLIGHT:
			result=Topic.SUNLIGHT.getDescription();
		}
		return result;
	}
	
	public static String[] newTopics(int n){
		int size=n < Topic.values().length ? n : Topic.values().length;
		String[] topics=new String[size];
		for(int i=0;i<size;i++){
			topics[i]=Topic.values()[i].getDescription();
		}
		return topics;
	}
	
	public enum Topic{
		TEMPERATURE("temperature"),
		HUMIDITY("humidity"),
		WATER("water"),
		SUNLIGHT("sunlight");
		
		private String description;
		
		private Topic(String description){
			this.description=description;
		}
		
		public String getDescription(){
			return description;
		}
	}

}
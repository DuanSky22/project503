package com.iscas.project503.util;

public class TopicFactory {
	
	public static int current=0;

/*	public static void main(String[] args) {
		for(int i =0 ; i < 10 ; i ++) {
			System.out.println(produceTopic());
		}
	}*/
	public static String produceTopic(){
		Topic  topic = Topic.values()[((current++) % Topic.values().length)]; 
		return newTopic(topic);
	}
	public static String newTopic(Topic  topic){
		String result=null;
		switch(topic){
		case ENVIRONMENTINFO : 
			result=Topic.ENVIRONMENTINFO.getDescription();
			break;
		case ENVIRONMENTALARM:
			result=Topic.ENVIRONMENTALARM.getDescription();
			break;
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
		ENVIRONMENTINFO("environmentInfo"),
		ENVIRONMENTALARM("environmentAlarm");
		
		private  String  description;
		
		private Topic(String description){
			this.description=description;
		}
		
		public String getDescription(){
			return description;
		}
	}
}

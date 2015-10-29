package com.iscas.project503.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateMessageParseTest {

	private static Map<String, String> keyAndValuesMap = new HashMap<String, String>();
	private static List<Map<String, String>> keyAndValuesMapList = new ArrayList<Map<String, String>>();

	public static List<Map<String, String>> KeyAndValues(String message) {

		int firstPosition = message.indexOf("{");
		int lastPosition = message.lastIndexOf("}");
		String messageTemp = message.substring(firstPosition+1, lastPosition);
		if (messageTemp.contains("[")) {
			int firstPositionInner = messageTemp.indexOf("[");
			int firstPositionInner2 = messageTemp.indexOf("{");
			int nextPositionInner = 0;
			int nextPositionInner2 = 0;
			int nextPositionInner3 = 0;
			String tempMessage = messageTemp.substring(0, firstPositionInner);
			int positionComma = 0;
			 String outerMessage = "";
			 String innerMessage = "";
			if(tempMessage.contains(",".trim())) {
			     positionComma = tempMessage.lastIndexOf(",");
			     outerMessage +=  tempMessage.substring(0, positionComma);
			    // System.out.println(outerMessage);
			     nextPositionInner = messageTemp.indexOf("}", firstPositionInner2);
				 nextPositionInner2 = messageTemp.indexOf("]", firstPositionInner2) ;
				 //System.out.println(outerMessage);
				 if(nextPositionInner2 < messageTemp.length() -1){
			    	 outerMessage += messageTemp.substring(nextPositionInner2 + 1, messageTemp.length());
			    } else {
			    	 ;
			    }
			} else {
				//如果在第一段里面不包含这个值,那么他直接获得这个
				nextPositionInner2  = messageTemp.indexOf("]".trim(), firstPositionInner2);
				nextPositionInner3 = messageTemp.indexOf("}".trim(), firstPositionInner2);
				int positionComma2 = messageTemp.indexOf(",".trim(), nextPositionInner2);
				outerMessage = messageTemp.substring(positionComma2 + 1 , messageTemp.length());
			}
			 innerMessage = messageTemp.substring(firstPositionInner2 + 1, nextPositionInner);
			 System.out.println(innerMessage);
			 System.out.println(outerMessage);
             String[] innerMessageArray = innerMessage.split(",");
             String[] outerMessageArray = outerMessage.split(",");
             for(String  iMA :  innerMessageArray) {
            	 String[] innerArray = iMA.split(":");
            	 if(!keyAndValuesMap.entrySet().contains(innerArray[0])) {
            		 keyAndValuesMap.put(innerArray[0], innerArray[1]);
            		 System.out.println(innerArray[0]+"      "+innerArray[1]);
            	 }
            	 //System.out.println(innerArray[0]+"      "+innerArray[1]);
            	 keyAndValuesMapList.add(keyAndValuesMap);
             }
             
             for(String  oMA :  outerMessageArray) {
            	 String[] outerArray = oMA.split(":");
            	 if(!keyAndValuesMap.entrySet().contains(outerArray[0].trim())) {
            		 keyAndValuesMap.put(outerArray[0].trim(), outerArray[1].trim());
            		 System.out.println(outerArray[0]+"      "+outerArray[1]);
            	 }
            	 keyAndValuesMapList.add(keyAndValuesMap);
             }
		} else {
			String[] messageTempArray = messageTemp.split(",");
			for (String messageTempArrayTemp : messageTempArray) {
				String[] innerMessageArray = messageTempArrayTemp.split(":");
				keyAndValuesMap.put(innerMessageArray[0].trim(),
						innerMessageArray[1].trim());
				        System.out.println(innerMessageArray[0]+"      "+innerMessageArray[1]);
			}
			keyAndValuesMapList.add(keyAndValuesMap);
		}
		return keyAndValuesMapList;
	}

	public static void main(String[] args) {
		
		String EnvironmentInfo = "   {streamNumber : 010,termID:011,light:012,airTemperature:013,airHumidity:014,soilTemperature:015,CO2Concentration:016,soilHumidity:017,timeStamp:018,alarmSource:019}";
		
		String EnvironmentAlarm = "{"
				+ "timeStamp:030,airTemperature:031,"+"alarmInfo:[{alarmID:0001,alarmTime:0002,alarmGrade:0003,alarmDesp:0004,TermID:0005,handler:\"\",handleTime:\"\",handleStatus:unhandled}]"+",airHumidity:032,soilTemperature:033,soilHumidity:034,"
				+ "light:035,CO2Concentration:036"
				+ "}";
		
		String alarmInfo = "{alarmId : 020,alarmTime:022,alarmGrade:023,alarmType:024,alarmDesp:025,weightType:026,time:027,weight:028}";
		
		String ScaleInfo = "{plotID : 041,cropID:042,cropName:043,grade:044,weight:045,weightType:046,TermID:047}";
		
		String WeatherInfo = "{termID : 051,windSpeed:052,rain:053,airpressure:054,airHumidity:055,light:056,timeStamp:057}";
	
		//KeyAndValues(EnvironmentInfo);
	    KeyAndValues(EnvironmentAlarm);
	}
}

package com.iscas.project503.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DateMessageParser {
	
	

	private static Map<String, String> keyAndValuesMap = new HashMap<String, String>();

	public static Map<String, String>  KeyAndValues(String message) {

		int firstPosition = message.indexOf("{");
		int lastPosition = message.lastIndexOf("}");
		String messageTemp = message.substring(firstPosition + 1, lastPosition);
		messageTemp = messageTemp.replaceAll("\"", "");
		String[] messageTempArray = messageTemp.split(",");
		for (String messageTempArrayTemp : messageTempArray) {
			String[] innerMessageArray = messageTempArrayTemp.split(":");
			keyAndValuesMap.put(innerMessageArray[0].trim(),
					innerMessageArray[1].trim());
			System.out.println(innerMessageArray[0] + "      "
					+ innerMessageArray[1]);
		}
		return keyAndValuesMap;
	}

	public static void main(String[] args) {
/*
		String EnvironmentInfo = "   {streamNumber : 010,termID:011,light:012,airTemperature:013,airHumidity:014,soilTemperature:015,CO2Concentration:016,soilHumidity:017,timeStamp:018,alarmSource:019}";

		String alarmInfo = "{alarmId : 020,alarmTime:022,alarmGrade:023,alarmType:024,alarmDesp:025,weightType:026,time:027,weight:028}";

		String ScaleInfo = "{plotID : 041,cropID:042,cropName:043,grade:044,weight:045,weightType:046,TermID:047}";

		String WeatherInfo ="{\"firstName\": \"Brett\" } ";
		
		

		 KeyAndValues(WeatherInfo);
       Map<String, String> sd  = KeyAndValues(WeatherInfo);
       for (Entry<String, String> entry : sd.entrySet()) {  
    	   
    	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
    	  
    	}  */
		
	}
}

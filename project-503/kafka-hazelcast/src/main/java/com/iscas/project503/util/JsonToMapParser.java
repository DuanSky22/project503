package com.iscas.project503.util;

import static com.iscas.project503.util.Project503String.MESSAGE_JSON_COMMA;
import static com.iscas.project503.util.Project503String.MESSAGE_JSON_RIGHT_BRACE;

import java.util.HashMap;
import java.util.Map;

public class JsonToMapParser {
	
	public static Map<String,String> parseJsonToMap(String json){
		Map<String,String> map=new HashMap<String,String>();
		json=json.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"", ""); //tip don't forget transfer \\!
		String[] pairs=json.split(",");
		for(String pair : pairs){
			String[] keyValue=pair.split(":");
			map.put(keyValue[0], keyValue[1]);
		}
		return map;
	}

	public static String findKey(String keyName,String message){
		String key="";
		if(message==null || message.length()==0)
			return key;
		int start=message.indexOf(keyName);
		if(start==-1)
			return key;
		int a,b;
		a=message.indexOf(MESSAGE_JSON_COMMA,start);
		b=message.indexOf(MESSAGE_JSON_RIGHT_BRACE,start);
		key=message.substring(start+keyName.length()+3,(a==-1 ? b : a)-1);
		return key;
	}
}

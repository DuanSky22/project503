package com.iscas.project503.util;

import java.util.List;
import java.util.Map;

public class JsonDataMessageParser {
	
	private static Map<String,String> enviromentMap = null;
	private static List<Map<String,String>> alermMessageList = null;

	public static Map<String,String> enviromentMessageMap (String dataMessage) {
		return enviromentMap;
	}
	
	public static List<Map<String,String>>   enviromentAlermMessageList (String dataMessage) {
		return alermMessageList;
	}
}

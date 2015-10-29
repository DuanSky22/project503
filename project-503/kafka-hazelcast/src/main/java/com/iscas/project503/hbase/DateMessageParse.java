package com.iscas.project503.hbase;

import java.util.HashMap;
import java.util.Map;

public class DateMessageParse {

	private static Map<String, String> keyAndValuesMap = new HashMap<String, String>();

	public static Map<String, String> KeyAndValues(String message) {
		return keyAndValuesMap;
	}
}

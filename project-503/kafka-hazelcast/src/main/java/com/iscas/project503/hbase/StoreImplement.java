package com.iscas.project503.hbase;

import java.util.HashMap;
import java.util.Map;

public class StoreImplement {

	private static Map<String, String> keyAndValuesMap = new HashMap<String, String>();
	public StoreImplement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void storeMessage(String tableName,String key,String value) {
		String rowKey = "";
		String[] qualilfer = null;
		String [] values = null;
		keyAndValuesMap = DateMessageParse.KeyAndValues(value);
	}
}

package com.iscas.project503.hbase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.iscas.project503.util.Project503String;

public class StoreImplement {

	private static  Map<String,String> resultMap = new HashMap<String,String>();

	public StoreImplement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void storeMessage(String tableName,String key,String columnFamily,String value) {
			String rowKey = "";
			int i = 0;
			resultMap = DateMessageParser.KeyAndValues(value);
			String[] qualilfer = new String[resultMap.keySet().size()] ;
			String [] values = new String[resultMap.keySet().size()];
			rowKey = resultMap.get("termID") + Project503String.INNER_SPLIT + resultMap.get("timeStamp");
			
			 for (Entry<String, String> entry : resultMap.entrySet()) {  
				 qualilfer[i] = entry.getKey();
				 values[i] = entry.getValue();
				 i ++;
	    	}  
		  HBaseOperations.addRowCols(tableName, rowKey, columnFamily, qualilfer, values);
	}  
}
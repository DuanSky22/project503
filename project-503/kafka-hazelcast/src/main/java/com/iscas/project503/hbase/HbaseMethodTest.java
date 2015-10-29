package com.iscas.project503.hbase;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.iscas.project503.util.Project503String;

/**
 * 测试类;
 * 
 * */
public class HbaseMethodTest {

	public static void main(String[] args) throws IOException {
		
/*		String[] familyTermID = { "termID" };
		String HistoricEnvInfo = "HistoricEnvInfo";
		String RecoveryInfo = "RecoveryInfo";
		
		String[] familyWeather = { "Weather" };
		String WeatherInfo = "WeatherInfo";
		
		String[] familytermID_alarmType = { "termID_alarmType" };
		String HistoricAlarmInfo = "HistoricAlarmInfo";
		
		String[] familyPlotID = { "PlotID" };
		String MsasureInfo = "MsasureInfo";*/
		
		//createTable(HistoricEnvInfo, familyTermID);
		
		//createTable(RecoveryInfo, familyTermID);
		
		//createTable(WeatherInfo, familyWeather);
		
	//createTable(HistoricAlarmInfo, familytermID_alarmType);
		
	 	//createTable(MsasureInfo, familyPlotID);
		//deleteTable("blog001");
		
		//String tableName = "blog001";
	   //String[] colFamilies = { "article", "author" };
	   //  String[] colFamilies2 = {"attributeCollection"};
		//HBaseUtil.createTable(tableName, colFamilies2);
		//HBaseUtil.showTables();
		/*String[] qualifier = {
				"streamNumber",
				"termID",
				"timeStamp",
				"airTemperature",
				"airHumidity",
				"soilTemperature",
				"soilHumidity",
				"light",
				"CO2Concentration",
				"alarmSource"
		};
		String[] value = {
				"123","001", "201545455545","12.35","11.23","10.05","52.1","4454","0.23","car"
		};*/
		/*for(int i = 0 ; i < 85 ; i ++) {
			//HBaseOperations.addRowCols(tableName, i +Project503String.MESSAGE_SPLIT+"201545455545", colFamilies2[0], qualifier, value);
			//HBaseOperations.deleteRowCol(tableName, i+"");
		}*/
		/*String[] termID = {"70", "73"};
		List<List<String>> ressult = HBaseUtil.selectInfo(tableName, "201545455545", "201545455550",termID);
		for (Iterator iterator = ressult.iterator(); iterator.hasNext();) {
			List<String> list = (List<String>) iterator.next();
			System.out.println(list);
		}*/
		//HBaseOperations.addRowCol(tableName, "0012015", colFamilies2[0], "airTemperature", "00000000");
		//HBaseOperations.updateRowCol(tableName,85 +"", colFamilies2[0], "airTemperature", "1111111111111111");
	//   HBaseOperations.deleteRowCol(tableName, "201545455545","201545455550","72");
		//HBaseOperations.addRowCols(tableName, "0012015", colFamilies2[0], qualifier, value);
		//HBaseTableUtil.deleteTable(tableName);
		//HBaseTableUtil.showTables();  
	}
}

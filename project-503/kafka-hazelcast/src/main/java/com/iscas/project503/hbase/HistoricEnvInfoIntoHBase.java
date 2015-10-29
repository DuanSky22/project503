package com.iscas.project503.hbase;

import java.util.Collection;
import java.util.Map;

import com.hazelcast.core.MapStore;

public class HistoricEnvInfoIntoHBase implements MapStore<String,String> {

	private String tableName = "HistoricAlarmInfo";
	private String columnFamily = "termID";
	public HistoricEnvInfoIntoHBase() {
		super();
	}

	public String load(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> loadAll(Collection<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<String> loadAllKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	//在这个方法中调用的是
	// TODO Auto-generated constructor stub
	/**
	 *  在value中包含的是dataMessage,是生产者中产生的json的字符串,在这个方法中解析出相应的属性,
	 *        得到对应的   属相
	 * */
	public void store(String key, String value) {
		// TODO Auto-generated method stub
		StoreImplement.storeMessage(tableName,key,columnFamily,value);
	}

	public void storeAll(Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll(Collection<String> keys) {
		// TODO Auto-generated method stub
		
	}

}

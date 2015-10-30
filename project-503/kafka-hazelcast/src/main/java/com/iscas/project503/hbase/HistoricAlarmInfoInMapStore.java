package com.iscas.project503.hbase;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hazelcast.core.MapStore;

//String 是termID
//Map<alarmType, alarmInfo>
public class HistoricAlarmInfoInMapStore implements MapStore<String,Map<String,String>>{

	private String tableName = "HistoricAlarmInfo1";
	private String columnFamily = "attribute";
	public HistoricAlarmInfoInMapStore() {
		super();
	}
	@Override
	public Map<String, String> load(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Map<String, String>> loadAll(Collection<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterable<String> loadAllKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void store(String key, Map<String, String> value) {
		// TODO Auto-generated method stub
		for(Map.Entry<String, String> entry:value.entrySet()){    
			StoreImplement.storeMessage(tableName,key,columnFamily,entry.getValue());
		}   
	}
	@Override
	public void storeAll(Map<String, Map<String, String>> map) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Collection<String> keys) {
		// TODO Auto-generated method stub
		
	}

}

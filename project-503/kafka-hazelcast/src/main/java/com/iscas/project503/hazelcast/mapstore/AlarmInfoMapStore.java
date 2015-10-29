package com.iscas.project503.hazelcast.mapstore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.hazelcast.core.MapStore;
import com.iscas.project503.hbase.HbaseService;
import com.iscas.project503.util.JsonToMapParser;
import com.iscas.project503.util.Project503String;

public class AlarmInfoMapStore implements MapStore<String,Map<String,String>> {

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
		for(Entry<String,String> entry : value.entrySet()){
			String message=entry.getValue();
			
			String termID=JsonToMapParser.findKey(Project503String.MESSAGE_TERM_ID, message);
			String time=JsonToMapParser.findKey("time", message);
			
			Map<String,String> map=JsonToMapParser.parseJsonToMap(message);
			Map<String,Map<String,String>> col=new HashMap<String,Map<String,String>>();
			col.put(Project503String.COLFUMILY, map);
			
			HbaseService.insert(Project503String.HISTORICALARMINFO, termID+Project503String.ID_TIME_SPLIT+time, col);
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

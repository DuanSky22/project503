package com.iscas.project503.hazelcast.mapstore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.hazelcast.core.MapStore;
import com.iscas.project503.hbase.HbaseService;
import com.iscas.project503.util.JsonToMapParser;
import com.iscas.project503.util.Project503String;

public class EnvInfoMapStore implements MapStore<String,String>{

	@Override
	public String load(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> loadAll(Collection<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> loadAllKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(String key, String value) {
		Map<String,String> map=JsonToMapParser.parseJsonToMap(value);
		Map<String,Map<String,String>> col=new HashMap<String,Map<String,String>>();
		col.put(Project503String.COLFUMILY, map);
		String time=JsonToMapParser.findKey("time", value);
		HbaseService.insert(Project503String.HISTORICENVINFO, key+Project503String.ID_TIME_SPLIT+time, col);
	}

	@Override
	public void storeAll(Map<String, String> map) {
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

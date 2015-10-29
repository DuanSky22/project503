package com.iscas.project503.hbase;

import java.util.Collection;
import java.util.Map;

import com.hazelcast.core.MapStore;

public class EnvironmentInfoMapStore implements MapStore<String, String> {

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
		// TODO Auto-generated method stub
		
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

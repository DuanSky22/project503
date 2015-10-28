package com.iscas.project503.controller;

import static com.iscas.project503.util.Project503RestString.ALARMINFO;
import static com.iscas.project503.util.Project503RestString.REDIS_DELETE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.iscas.project503.util.Project503HazelcastClient;

@RestController
@RequestMapping(REDIS_DELETE)
public class RedisDeleteController {

	private HazelcastInstance node=Project503HazelcastClient.getInstance();
	
	@RequestMapping(ALARMINFO)
	public Map<String,String>[] deleteRealtimeAlarmInfo(@RequestParam(value = "termID[]", required = true) String [] termID,
			@RequestParam(value = "alarmType", required = true) String alarmType){
		Map[] result=null;
		if(termID!=null & termID.length!=0){
			result=new HashMap[termID.length];
			IMap<String,Map<String,String>> alarm=node.getMap(ALARMINFO);
			for(int i=0;i<termID.length;i++){
				Map<String,String> field=alarm.get(termID[i]);
				if(field.get(alarmType)!=null){
					field.remove(alarmType);
					alarm.put(termID[i], field);
				}
				result[i]=field;
			}
		}
		return result;
	}
	
}

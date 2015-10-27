package com.iscas.project503.service;

import static com.iscas.project503.util.Project503RestString.ALARMINFO;
import static com.iscas.project503.util.Project503RestString.SEPARATER;
import static com.iscas.project503.util.Project503RestString.UPDATE;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.iscas.project503.util.Project503HazelcastClient;

@RestController
@RequestMapping(UPDATE)
public class UpdateDataController {
	
	private HazelcastInstance node=Project503HazelcastClient.getInstance();;
	
    @RequestMapping(SEPARATER+ALARMINFO)
    @ResponseBody
    public boolean setRealtimeAlarmState(@RequestParam(value = "termID[]", required = true) String[] termID,
    		@RequestParam(value = "alarmType", required = true) String alarmType,
    		@RequestParam(value = "handleInfo", required = true) String handleInfo){
    	if(termID!=null && termID.length!=0){
    		IMap<String,Map<String,String>> map=node.getMap(ALARMINFO);
    		for(int i=0;i<termID.length;i++){
    			Map<String,String> field=map.get(termID[i]);
    			field.put(alarmType, handleInfo);
    			map.put(termID[i], field);
    		}
    		return true;
    	}
        return false;
    }
    
}

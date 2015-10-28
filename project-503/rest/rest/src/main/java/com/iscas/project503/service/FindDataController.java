package com.iscas.project503.service;

import static com.iscas.project503.util.Project503RestString.ALARMINFO;
import static com.iscas.project503.util.Project503RestString.ENVINFO;
import static com.iscas.project503.util.Project503RestString.FIND;
import static com.iscas.project503.util.Project503RestString.SEPARATER;
import static com.iscas.project503.util.Project503RestString.WEATHER;
import static com.iscas.project503.util.Project503RestString.WEATHER_KEY;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.iscas.project503.util.Project503HazelcastClient;
@RestController
@RequestMapping(FIND)
public class FindDataController {
	
	private HazelcastInstance node=Project503HazelcastClient.getInstance();
	
    @RequestMapping(SEPARATER+ENVINFO)
    @ResponseBody
    public String[] queryRealtimeEnvInfo(@RequestParam(value = "termID[]", required = true) String[] termID){
        IMap<String,String> map=node.getMap(ENVINFO);
        if(termID!=null && termID.length!=0){
        	String[] result=new String[termID.length];
            for(int i=0;i<termID.length;i++)
            	result[i]=map.get(termID[i]);//TODO maybe we should change the key type!
            return result;
        }
        return null;
    }
    
    @RequestMapping(SEPARATER+WEATHER)
    @ResponseBody
    public String queryRealtimeWeatherInfo(@RequestParam(value = "weatherinfo", required = false) String weatherinfo){
        IMap<String,String> map=node.getMap(WEATHER);
    	String result=map.get(WEATHER_KEY);
    	return result;
    }
    
    @RequestMapping(SEPARATER+ALARMINFO)
    @ResponseBody
    public Map<String,String>[] queryRealtimeAlarmInfo(@RequestParam(value = "termID[]", required = true) String[] termID){
        Map<String,Map<String,String>> map=node.getMap(ALARMINFO);
        if(termID!=null && termID.length!=0){
        	Map[] result = new HashMap[termID.length];
            for(int i=0;i<termID.length;i++)
            	result[i]=map.get(termID[i]);
            return result;
        }
        return null;
    }
    
}

package com.iscas.project503.controller;

import static com.iscas.project503.util.Project503RestString.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iscas.project503.service.HbaseService;
@RestController
@RequestMapping(HBASE_FIND)
public class HbaseFindController {

	@RequestMapping(ENVINFO)
	public List<String> queryHistoricalEnvInfo(
			@RequestParam(value = "beginTime", required = true)String beginTime,
			@RequestParam(value = "endTime", required = true)String endTime,
			@RequestParam(value = "termID[]", required = true)String [] termID){
		List<String> result=new ArrayList<String>();
		for(String id : termID){
			String begin=id+ID_TIME_SPLIT+beginTime;
			String end=id+ID_TIME_SPLIT+endTime;
			String single=HbaseService.select(HISTORICENVINFO, begin, end);	
			if(single!=null) result.add(single);
		}	
		return result;
	}
	
	@RequestMapping(RECORDINFO)
	public List<String> queryHistoricalRecordInfo(
			@RequestParam(value = "beginTime", required = true)String beginTime,
			@RequestParam(value = "endTime", required = true)String endTime,
			@RequestParam(value = "termID[]", required = true)String [] termID){
		List<String> result=new ArrayList<String>();
		for(String id : termID){
			String begin=id+ID_TIME_SPLIT+beginTime;
			String end=id+ID_TIME_SPLIT+endTime;
			String single=HbaseService.select(MEASUREINFO, begin, end);	
			if(single!=null) result.add(single);
		}	
		return result;
	}
	
	@RequestMapping(WEATHERINFO)
	public List<String> queryHistoricalWeather(
			@RequestParam(value = "beginTime", required = true)String beginTime,
			@RequestParam(value = "endTime", required = true)String endTime,
			@RequestParam(value = "termID[]", required = true)String [] termID){
		List<String> result=new ArrayList<String>();
		for(String id : termID){
			String begin=id+ID_TIME_SPLIT+beginTime;
			String end=id+ID_TIME_SPLIT+endTime;
			String single=HbaseService.select(WEATHERINFO, begin, end);	
			if(single!=null) result.add(single);
		}	
		return result;
	}
	
	@RequestMapping(ALARMINFO)
	public List<String> queryHistoricalAlarmInfo(
			@RequestParam(value = "beginTime", required = true)String beginTime,
			@RequestParam(value = "endTime", required = true)String endTime,
			@RequestParam(value = "termID[]", required = true)String [] termID,
			@RequestParam(value = "alarmType", required = true)String alarmType){
		List<String> result=new ArrayList<String>();
		for(String id : termID){
			String begin=id+ID_TIME_SPLIT+beginTime;
			String end=id+ID_TIME_SPLIT+endTime;
			String single=HbaseService.select(HISTORICALARMINFO, begin, end,ATTRIBUTE,"alarmType",alarmType);	
			if(single!=null) result.add(single);
		}	
		return result;
	}
}

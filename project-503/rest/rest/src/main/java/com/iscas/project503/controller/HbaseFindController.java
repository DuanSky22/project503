package com.iscas.project503.controller;

import static com.iscas.project503.util.Project503RestString.*;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iscas.project503.util.HbaseClientConfiguration;
@RestController
@RequestMapping(HBASE_FIND)
public class HbaseFindController {

	@RequestMapping(ENVINFO)
	public List<String> queryHistoricalEnvInfo(
			@RequestParam(value = "beginTime", required = true)String beginTime,
			@RequestParam(value = "endTime", required = true)String endTime,
			@RequestParam(value = "termID[]", required = true)String [] termID){
		
		Configuration config=HbaseClientConfiguration.getInstance().getHbaseConfig();
		
		
		return null;
	}
}

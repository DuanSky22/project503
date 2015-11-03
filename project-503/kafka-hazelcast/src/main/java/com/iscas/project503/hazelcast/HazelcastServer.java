package com.iscas.project503.hazelcast;

import static com.iscas.project503.util.Project503String.ALARMINFO;
import static com.iscas.project503.util.Project503String.ENVINFO;
import static com.iscas.project503.util.Project503String.TOPIC_SCALEINFO;
import static com.iscas.project503.util.Project503String.WEATHER;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.GroupProperties.GroupProperty;
import com.iscas.project503.hazelcast.mapstore.AlarmInfoMapStore;
import com.iscas.project503.hazelcast.mapstore.EnvInfoMapStore;
import com.iscas.project503.hazelcast.mapstore.MeasureMapStore;
import com.iscas.project503.hazelcast.mapstore.WeatherInfoMapStore;

public class HazelcastServer {
	
	public static void main(String args[]){
		
		/*
		 * find bugs 02
		 * duansky
		 * 2015-11-03
		 * fixed!
		 */
//		Config config = new Config();  //here since we have make a hazelcast.xml file to config the hazelcast server,
		                               // so we don't need to new a new config object!
//		//=================envinfo=============================//
//		MapConfig envinfoMapConfig = config.getMapConfig(ENVINFO);	
//		MapStoreConfig envinfoMapStoreConfig=new MapStoreConfig();
//		
//		envinfoMapStoreConfig.setEnabled(true);
//		envinfoMapStoreConfig.setWriteDelaySeconds(0);
//		envinfoMapStoreConfig.setWriteBatchSize(100);
//		envinfoMapStoreConfig.setWriteCoalescing(false);
//		envinfoMapStoreConfig.setClassName("com.iscas.project503.hazelcast.mapstore.EnvInfoMapStore");
//		envinfoMapStoreConfig.setImplementation(new EnvInfoMapStore());
//        
//        envinfoMapConfig.setMapStoreConfig(envinfoMapStoreConfig);
//		
//        //=================weather=============================//
//        MapConfig weatherMapConfig = config.getMapConfig(WEATHER);	
//		MapStoreConfig weatherMapStoreConfig=new MapStoreConfig();
//		
//		weatherMapStoreConfig.setEnabled(true);
//		weatherMapStoreConfig.setWriteDelaySeconds(0);
//		weatherMapStoreConfig.setWriteBatchSize(100);
//		weatherMapStoreConfig.setClassName("com.iscas.project503.hazelcast.mapstore.WeatherInfoMapStore");
//		weatherMapStoreConfig.setImplementation(new WeatherInfoMapStore());
//        
//		weatherMapConfig.setMapStoreConfig(weatherMapStoreConfig);
//        
//        //=================AlarmInfo=============================//
//        MapConfig alarmMapConfig = config.getMapConfig(ALARMINFO);	
//		MapStoreConfig alarmMapStoreConfig=new MapStoreConfig();
//		
//		alarmMapStoreConfig.setEnabled(true);
//		alarmMapStoreConfig.setWriteDelaySeconds(0);
//		alarmMapStoreConfig.setWriteBatchSize(100);
//		alarmMapStoreConfig.setClassName("com.iscas.project503.hazelcast.mapstore.AlarmInfoMapStore");
//		alarmMapStoreConfig.setImplementation(new AlarmInfoMapStore());
//        
//		alarmMapConfig.setMapStoreConfig(alarmMapStoreConfig);
//		
//        //=================MeasureInfo=============================//
//        MapConfig measureMapConfig = config.getMapConfig(TOPIC_SCALEINFO);	
//		MapStoreConfig measureMapStoreConfig=new MapStoreConfig();
//		
//		measureMapStoreConfig.setEnabled(true);
//		measureMapStoreConfig.setWriteDelaySeconds(0);
//		measureMapStoreConfig.setWriteBatchSize(100);
//		measureMapStoreConfig.setClassName("com.iscas.project503.hazelcast.mapstore.MeasureMapStore");
//		measureMapStoreConfig.setImplementation(new MeasureMapStore());
//        
//		measureMapConfig.setMapStoreConfig(measureMapStoreConfig);
		
        HazelcastInstance server=Hazelcast.newHazelcastInstance();
        
	}
	
}

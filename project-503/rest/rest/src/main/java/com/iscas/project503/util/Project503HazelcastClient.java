package com.iscas.project503.util;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class Project503HazelcastClient {
	
	private static HazelcastInstance instance=null;
	private Project503HazelcastClient(){
	}
	
	public static HazelcastInstance getInstance(){
		if(instance==null){
			ClientConfig clientConfig=new ClientConfig();
			clientConfig.getNetworkConfig().addAddress("localhost","133.133.133.75"); //TODO maybe we need a configuration!
			instance=HazelcastClient.newHazelcastClient(clientConfig);
		}
		return instance;
	}

}

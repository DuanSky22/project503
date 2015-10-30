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
			String[] serverAddresses=HazelcastServerParser.getInstance().getServerAddresses();
			clientConfig.getNetworkConfig().addAddress(serverAddresses);
			instance=HazelcastClient.newHazelcastClient(clientConfig);
		}
		return instance;
	}

}

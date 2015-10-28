package com.iscas.project503.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastServer {
	
	private static HazelcastInstance node=Hazelcast.newHazelcastInstance();
	
	public static void main(String args[]){
		HazelcastServer server=new HazelcastServer();
	}
	
}

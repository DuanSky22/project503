package com.iscas.project503.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


public class HbaseConnection {
	
	private static Connection conn=null;
	
	private static HbaseConnection instance=null;
	
	private HbaseConnection(){
		Configuration conf=HBaseConfiguration.create();
		Map<String,String> map=HbaseConnParser.getInstance().getHbaseConn();
		for(Entry<String,String> entry : map.entrySet()){
			conf.set(entry.getKey(), entry.getValue());
		}
		try {
			conn=ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HbaseConnection getInstance(){
		if(instance==null)
			return new HbaseConnection();
		else
			return instance;
	}
	
	public Connection getHbaseConnection(){
		return conn;
	}

}

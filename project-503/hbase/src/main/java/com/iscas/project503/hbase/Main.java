package com.iscas.project503.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class Main {
	
	public static void main(String args[]){
		Configuration config=HBaseConfiguration.create();
		Connection con;
		try {
			con = ConnectionFactory.createConnection(config);
			Admin admin=con.getAdmin();
			Map<String,List<String>> schemas=HbaseSchemaParser.getInstance().getHbaseSchema();
			HTableDescriptor[] listTables=admin.listTables();
			for(Entry<String,List<String>> tableEntry : schemas.entrySet()){
				String tableName=tableEntry.getKey();
				TableName tn=TableName.valueOf(tableName);
				admin.disableTable(tn);
				HTableDescriptor desc=new HTableDescriptor(tn);
				for(String colFamily : tableEntry.getValue()){
					HColumnDescriptor col=new HColumnDescriptor(colFamily);
					desc.addFamily(col);
				}
				admin.createTable(desc);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

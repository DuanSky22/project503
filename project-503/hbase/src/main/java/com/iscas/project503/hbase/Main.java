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
import org.apache.hadoop.hbase.client.Table;

public class Main {
	public static Configuration config=null;
	public static Connection con=null;
	
	static {
		config=HBaseConfiguration.create();
		Map<String,String> map=HbaseConnParser.getInstance().getHbaseConn();
		for(Entry<String,String> entry : map.entrySet()){
			config.set(entry.getKey(), entry.getValue());
		}
		try {
			con = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Main.createTables();
		//Main.deleteAllTables();
	}
	
	public static void createTables(){
		
		try {
			Admin admin=con.getAdmin();
			Map<String,List<String>> schemas=HbaseSchemaParser.getInstance().getHbaseSchema();
			
			for(Entry<String,List<String>> tableEntry : schemas.entrySet()){
				String tableName=tableEntry.getKey();
				TableName tn=TableName.valueOf(tableName);
				if(admin.tableExists(tn)){
					if(!admin.isTableDisabled(tn))
						admin.disableTable(tn);
					admin.deleteTable(tn);
				}
				HTableDescriptor desc=new HTableDescriptor(tn);
				for(String colFamily : tableEntry.getValue()){
					HColumnDescriptor col=new HColumnDescriptor(colFamily);
					desc.addFamily(col);
				}
				admin.createTable(desc);
			}
			TableName[] tableNames=admin.listTableNames();
			System.out.println("=================table been created=========================");
			for(TableName tableName : tableNames){
				System.out.println(tableName.getNameAsString());
			}
			System.out.println("=================table been created=========================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAllTables(){
		Admin admin;
		try {
			admin = con.getAdmin();
			for(TableName tableName : admin.listTableNames()){
				if(!admin.isTableDisabled(tableName))
					admin.disableTable(tableName);
				admin.deleteTable(tableName);
				System.out.println(tableName.getNameAsString()+" has been deleted!");
			}
			System.out.println("All tables in hbase have been deleted!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

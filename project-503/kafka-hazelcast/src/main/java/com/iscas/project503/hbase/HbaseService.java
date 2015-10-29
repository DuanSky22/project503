package com.iscas.project503.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.iscas.project503.util.HbaseConnection;


public class HbaseService {
	
	private static Connection conn=null;
	
	static{
		conn=HbaseConnection.getInstance().getHbaseConnection();
	}
	
	//insert data
	public static void insert(String tableName,String key,Map<String,Map<String,String>> data){
		TableName tn=TableName.valueOf(tableName);
		try {
			Table table=conn.getTable(tn);
			Put put=new Put(Bytes.toBytes(key));
			for(Entry<String,Map<String,String>> colFamily : data.entrySet()){
				String colFamilyName=colFamily.getKey();
				for(Entry<String,String> attr : colFamily.getValue().entrySet()){
					String attrName=attr.getKey();
					String attrValue=attr.getValue();
					put.addColumn(Bytes.toBytes(colFamilyName), 
							Bytes.toBytes(attrName), 
							Bytes.toBytes(attrValue));
				}
				table.put(put);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//select data
	// get data where its key value between two values.
	public static String select(String tableName,String begin,String end){
		String result="";
		TableName tn=TableName.valueOf(tableName);
		try {
			Table table=conn.getTable(tn);
			Scan scan=new Scan(Bytes.toBytes(begin),Bytes.toBytes(end));
			//TODO we don't know the result of the scan.
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}

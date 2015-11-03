package com.iscas.project503.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.util.HbaseConnection;


public class HbaseService {
	
	private static Connection conn=null;
	
	static{
		conn=HbaseConnection.getInstance().getHbaseConnection();
	}
	
	//insert data
	public static void insert(String tableName,String key,Map<String,Map<String,String>> data){
		TableName tn=TableName.valueOf(tableName);
		List<Put> puts=new ArrayList<Put>();
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
					puts.add(put);
				}
				table.put(puts);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//select data 
	// get data where its key value between two values.
	//this adapt for envinfo / measureinfo / weatherinfo.
	public static String select(String tableName,String begin,String end){
		String result="";
		TableName tn=TableName.valueOf(tableName);
		try {
			Table table=conn.getTable(tn);
			Scan scan=new Scan(Bytes.toBytes(begin),Bytes.toBytes(end));
			ResultScanner scanner=table.getScanner(scan);
			List<Map<String,String>> total=new ArrayList<Map<String,String>>();
			for(Result res : scanner){
				Map<String,String> map=new HashMap<String,String>();
				for(Cell cell : res.rawCells())
					map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
				total.add(map);
			}
			ObjectMapper mapper=new ObjectMapper();
			result=mapper.writeValueAsString(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//select data
	//http://blog.csdn.net/cnweike/article/details/42920547 for more detail.
	public static String select(String tableName,String begin,String end,String colFamily,String column,String value){
		String result="";
		TableName tn=TableName.valueOf(tableName);
		try {
			Table table=conn.getTable(tn);
			Scan scan=new Scan(Bytes.toBytes(begin),Bytes.toBytes(end));
			
			SingleColumnValueFilter scvf = new SingleColumnValueFilter(  
			        Bytes.toBytes(colFamily),   
			        Bytes.toBytes(column),   
			        CompareFilter.CompareOp.EQUAL,   
			        new SubstringComparator(value));  
			scvf.setFilterIfMissing(false);  
			scvf.setLatestVersionOnly(true); // OK  
			
			scan.setFilter(scvf);
			
			ResultScanner scanner=table.getScanner(scan);
			List<Map<String,String>> total=new ArrayList<Map<String,String>>();
			for(Result res : scanner){
				Map<String,String> map=new HashMap<String,String>();
				for(Cell cell : res.rawCells())
					map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
				total.add(map);
			}
			
			ObjectMapper mapper=new ObjectMapper();
			result=mapper.writeValueAsString(total);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String args[]){
		HbaseService service=new HbaseService();
		String tableName="WeatherInfo";
		String begin="94#2015-10-30 09:38:52 952";
		String end="99#2015-10-30 09:54:00 795";
		service.select(tableName, begin, end);
	}

}

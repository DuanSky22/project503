package com.iscas.project503.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.iscas.project503.util.Project503String;

public class HBaseOperations {

	//private static Properties prop;
	private static Configuration config;
	private static Connection connection;
	private static List<List<String>> resultList = new ArrayList<List<String>>();

	static {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "133.133.134.130");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			connection = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 指定表名,然后指定rowkey的一个范围查询.其中表名是确定的,而且 
	/**
	 * 在方法中,指定表名,然后利用termID和开始时间,结束时间构成查询范围,每一个ID是一个list
	 * 这个ID数组返回的是List<list<infor>>
	 * 
	 * @tableName 需要查询的tableName
	 * @termID[] 需要查询的termID termID的排序方式
	 * @startTime 时间区间开始点 包括这个起始值
	 * @endTime 时间区间结束点 不包括这个结束值,为了实现包括,在这个字节数组的后面添加0byte. 但是不确定这个加 是否可以解决问题.
	 * @timeStamp[]
	 * @return List<List<String>>  返回的结果在list的包装下的格式是: 多带了一个 "   []   "
	 *                     [CO2Concentration:0.23,airHumidity:11.23,airTemperature:12.35,alarmSource:car,light:4454,soilHumidity:52.1
	 *                        ,soilTemperature:10.05,streamNumber:123,termID:001,timeStamp:201545455545]
	 * */
	public static List<List<String>> selectInfo(String tableName,
			String rowKey1, String rowKey2, String[] termID) {
        
		Scan scan = new Scan();
		HTable table;
		// String requestRowkey = null;
		for (int i = 0; i < termID.length; i++) {
			System.out.println(termID[i]);
			 List<String> infoList = new ArrayList<String>();
			String selectInfo = "";
			scan.setStartRow(rowKey1.getBytes());
			scan.setStopRow(rowKey2.getBytes());
			ResultScanner rs = null;
			try {
				table = new HTable(config, Bytes.toBytes(tableName));
				rs = table.getScanner(scan);
				for (Result r : rs) {
					for (KeyValue kv : r.list()) {
						selectInfo += Bytes.toString(kv.getQualifier()) + ":" +Bytes.toString(kv.getValue()) + ",";
						/*System.out.println("qualifier:"
								+ Bytes.toString(kv.getQualifier()));
						System.out.println("value:"
								+ Bytes.toString(kv.getValue()));*/
					}
				}
				selectInfo = selectInfo.substring(0, selectInfo.length()-1);
				System.out.println(selectInfo);
				infoList.add(selectInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rs.close();
			}
			resultList.add(infoList);
		}
		return resultList;
	}

	//针对报警信息进行查询;
	public static List<List<String>> selectInfo(String tableName,
			String rowKey1, String rowKey2, String[] termID,String alarmType) {
        
		Scan scan = new Scan();
		HTable table;
		// String requestRowkey = null;
		for (int i = 0; i < termID.length; i++) {
			System.out.println(termID[i]);
			 List<String> infoList = new ArrayList<String>();
			String selectInfo = "";
/*			String startRowKey = termID[i] + Project503String.INNER_SPLIT
					+ beginTime  + Project503String.INNER_SPLIT + alarmType;
			String endRowKey = termID[i] + Project503String.INNER_SPLIT
					+ endTime  + Project503String.INNER_SPLIT + alarmType+ " 0";
*/			scan.setStartRow(rowKey1.getBytes());
			scan.setStopRow(rowKey2.getBytes());
			ResultScanner rs = null;
			try {
				table = new HTable(config, Bytes.toBytes(tableName));
				rs = table.getScanner(scan);
				for (Result r : rs) {
					for (KeyValue kv : r.list()) {
						selectInfo += Bytes.toString(kv.getQualifier()) + ":" +Bytes.toString(kv.getValue()) + ",";
						/*System.out.println("qualifier:"
								+ Bytes.toString(kv.getQualifier()));
						System.out.println("value:"
								+ Bytes.toString(kv.getValue()));*/
					}
				}
				selectInfo = selectInfo.substring(0, selectInfo.length()-1);
				System.out.println(selectInfo);
				infoList.add(selectInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rs.close();
			}
			resultList.add(infoList);
		}
		return resultList;
	}

	/**
	 * */
	public static Boolean addRowCol(String tableName, String rowKey,
			String columnFamily, String qualifier, String value) {
		try {
			Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
			HTable table = new HTable(config, Bytes.toBytes(tableName));
			put.addColumn(Bytes.toBytes(columnFamily),
					Bytes.toBytes(qualifier), Bytes.toBytes(value));
			table.put(put);
			table.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 添加所有的列 用于把实时库中的数据添加到hbase中.
	public static Boolean addRowCols(String tableName, String rowKey,
			String columnFamily, String[] qualifier, String[] value) {
		try {

			Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
			HTable table = new HTable(config, Bytes.toBytes(tableName));
			HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
					.getColumnFamilies();
			for (int i = 0; i < columnFamilies.length; i++) {
				String familyName = columnFamilies[i].getNameAsString();
				if (familyName.equals(columnFamily)) { // article列族put数据
					for (int j = 0; j < qualifier.length; j++) {
						put.addColumn(Bytes.toBytes(familyName),
								Bytes.toBytes(qualifier[j]),
								Bytes.toBytes(value[j]));
					}
				}
			}
			table.put(put);
			table.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 根据表明 和 rowkeys 进行删除.
	public static Boolean deleteRowCol(String tableName, String rowKey1,String r0wKey2,String termID) {
		Scan scan = new Scan();
		scan.setStartRow(rowKey1.getBytes());
		scan.setStopRow(r0wKey2.getBytes());
		ResultScanner rs = null;
		try {
			HTable table = new HTable(config, Bytes.toBytes(tableName));
			rs = table.getScanner(scan);
			for (Result r : rs) {
				for (KeyValue kv : r.list()) {
					Delete delete = new Delete(kv.getRow());  
			        table.delete(delete);  
				}
			}
			table.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 在update中使用的是addCowRol方法进行加入的.
	public static Boolean updateRowCol(String tableName, String rowKey,
			String columnFamily, String qualifier, String value) {
		 return addRowCol(tableName, rowKey, columnFamily, qualifier, value);
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeAdmin(Admin admin) {
		if (admin != null) {
			try {
				admin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

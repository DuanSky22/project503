package com.iscas.project503.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HBaseTableUtil {

	private static Configuration config;
	private static Connection connection;
	private static List<List<String>> resultList = new ArrayList<List<String>>();

	public static void main(String[] args) {
//		deleteTable("HistoricAlarmInfo");
	    String[] family = {"attribute"};
        createTable("WeatherInfo1",family);
	}
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

	// 创建表 familys 是列簇的名字;
	public static Boolean createTable(String tableName, String[] familys) {
		Admin admin;
		try {
			admin = connection.getAdmin();
			if (admin.tableExists(TableName.valueOf(tableName))) {
				System.out.println("Table: " + tableName + " already exists !");
			} else {
				HTableDescriptor dsc = new HTableDescriptor(
						TableName.valueOf(tableName));
				// UnmodifyableHTableDescriptor
				int len = familys.length;
				for (int i = 0; i < len; i++) {
					HColumnDescriptor family = new HColumnDescriptor(familys[i]);
					dsc.addFamily(family);
				}
				admin.createTable(dsc);
				HBaseOperations.closeAdmin(admin);
				HBaseOperations.closeConnection(connection);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean deleteTable(String tableName) {
		Admin admin;
		try {
			admin = connection.getAdmin();
			if (admin.tableExists(TableName.valueOf(tableName))) {
				admin.disableTable(TableName.valueOf(tableName));
				admin.deleteTable(TableName.valueOf(tableName));
			} else {
				System.out.println(tableName + "had been deleted!");
			}
			HBaseOperations.closeAdmin(admin);
			HBaseOperations.closeConnection(connection);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void showTables() {
		try {
			Admin admin = connection.getAdmin();
			HTableDescriptor[] des = admin.listTables();
			for (HTableDescriptor desc : des) {
				System.out.println(desc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

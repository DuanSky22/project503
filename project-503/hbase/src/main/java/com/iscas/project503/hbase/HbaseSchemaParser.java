package com.iscas.project503.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HbaseSchemaParser {
	
	private static HbaseSchemaParser instance=null;
	private Map<String,List<String>> map;

	public static void main(String args[]){
		Map<String,List<String>> map=HbaseSchemaParser.getInstance().getHbaseSchema();
	}
	private HbaseSchemaParser(){
		parser();
	}
	
	@SuppressWarnings("unchecked")
	private void parser(){
		map=new HashMap<String,List<String>>();
		SAXReader saxReader=new SAXReader();
		
		try {
			Document doument=saxReader.read(HbaseSchemaParser.class.getClassLoader().
					getResourceAsStream("HbaseSchema.xml"));
			Element root=doument.getRootElement();
			for(Iterator<Element> iter=root.elementIterator();iter.hasNext();){
				Element table=iter.next();
				String tableName=table.getName();
				List<String> tableAttrs=new ArrayList<String>();
				for(Iterator<Element> attr=table.elementIterator();attr.hasNext();){
					Element attribute=attr.next();
					tableAttrs.add(attribute.getData().toString());
				}
				map.put(tableName, tableAttrs);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static HbaseSchemaParser getInstance(){
		if(instance==null)
			return new HbaseSchemaParser();
		else
			return instance;
	}

	public Map<String,List<String>> getHbaseSchema(){
		return map;
	}
}

package com.iscas.project503.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HbaseConnParser {
	
	Map<String,String> map=new HashMap<String,String>();
	
	private static HbaseConnParser instance=null;
	
	private HbaseConnParser(){
		parser();
	}
	
	public static HbaseConnParser getInstance(){
		if(instance==null)
			instance = new HbaseConnParser();
		return instance;
	}
	
	private void parser(){
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(HbaseConnParser.class.getClassLoader().getResourceAsStream("HbaseCon.xml"));
			Element root=doc.getRootElement();
			for(Iterator<Element> iter=root.elementIterator();iter.hasNext();){
				Element e=iter.next();
				map.put(e.getName(), e.getData().toString());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String,String> getHbaseConn(){
		return map;
	}

}

package com.iscas.project503.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class HazelcastServerParser {
	
	List<String> serverAddressList=new ArrayList<String>();
	
	private static HazelcastServerParser instance=null;
	
	public static HazelcastServerParser getInstance(){
		if(instance == null)
			return new HazelcastServerParser();
		else
			return instance;
	}
	
	private HazelcastServerParser(){
		parser();
	}
	
	@SuppressWarnings("unchecked")
	private void parser(){
		SAXReader saxReader=new SAXReader();
		try {
			Document doc=saxReader.read(HazelcastServerParser.class.
					getClassLoader().getResourceAsStream("hazelcast-server.xml"));
			Element root=doc.getRootElement();
			for( Iterator<Element>e = root.elementIterator(); e.hasNext();){
				serverAddressList.add(e.next().getData().toString());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getServerAddresses(){
		String[] result=new String[serverAddressList.size()];
		for(int i=0;i<result.length;i++){
			result[i]=serverAddressList.get(i);
		}
		return result;
	}
}

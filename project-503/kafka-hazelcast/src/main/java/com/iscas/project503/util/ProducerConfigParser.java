package com.iscas.project503.util;

import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ProducerConfigParser {
	
	public static void main(String args[]){
		ProducerConfigParser config=ProducerConfigParser.getInstance();
		Properties p=config.getProducerConfig();
	}

	private static ProducerConfigParser instance=null;
	private static Properties prop=null;
	
	public  static ProducerConfigParser getInstance(){
		if(instance==null)
			return new ProducerConfigParser();
		else
			return instance;
	}
	@SuppressWarnings("unchecked")
	private ProducerConfigParser(){
		prop=new Properties();
		SAXReader saxReader=new SAXReader();
		try {
			Document doc=saxReader.read(
					ProducerConfigParser.class.getClassLoader().getResourceAsStream("producer.xml"));
			Element root=doc.getRootElement();
			for(Iterator<Element> iter=root.elementIterator();iter.hasNext();){
				Element e=iter.next();
				//System.out.println(e.getName());
				if(e.getName().equals("topic.list")){
					int topicCounter = 0;
					for(Iterator<Element> topic=e.elementIterator();topic.hasNext();){
						Element topicContent = topic.next();
						//System.out.println(topicContent.getName());
						topicCounter ++;
						Object topicName = null;
						Object topicStreamNums = null;
						for(Iterator<Element> topicContentIter = topicContent.elementIterator();topicContentIter.hasNext();){
							Element topicAttribute=topicContentIter.next();
							if(topicAttribute.getName().equals("name")){
								topicName = topicAttribute.getData();
							}
							if(topicAttribute.getName().equals("stream.number")) {
								topicStreamNums = topicAttribute.getData();
							}
						}
						prop.put(Project503String.TOPIC+topicCounter+"", topicName);
						prop.put(topicName, topicStreamNums);
					}
					prop.put(Project503String.TOPIC_COUNT, topicCounter);
				} else {
					prop.put(e.getName(), e.getData());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public Properties getProducerConfig(){
		return prop;
	}
}
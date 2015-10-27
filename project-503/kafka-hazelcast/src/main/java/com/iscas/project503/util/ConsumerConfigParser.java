package com.iscas.project503.util;

import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ConsumerConfigParser {
	
	public static void main(String args[]){
		//ConsumerConfigParser config=ConsumerConfigParser.getInstance();
	}
	
	private static  ConsumerConfigParser instance=null;
	private  Properties prop=null;
	
	@SuppressWarnings("unchecked")
	private ConsumerConfigParser(){
		prop=new Properties();
		SAXReader saxReader=new SAXReader();
		try {
			Document doc=saxReader.read(ConsumerConfigParser.class.getClassLoader().getResourceAsStream("consumer.xml"));
			Element root=doc.getRootElement();
			
			Element zookeeper=root.element("zookeeper");
			for(Iterator<Element> iter=zookeeper.elementIterator();iter.hasNext();){
				Element e= iter.next();
				prop.put(e.getName(),e.getData());
			}
			
			Element consumer=root.element("consumer");
			for(Iterator<Element> iter=consumer.elementIterator();iter.hasNext();){
				Element e= iter.next();
				if(e.getName().equals("topic.list")){
					int topicCounter=0;
					for(Iterator<Element> topicOuter=e.elementIterator();topicOuter.hasNext();){
						Element topicElement=topicOuter.next();
						topicCounter++;
						Object topicName=null;
						Object streamNums=null;
						for(Iterator<Element> topicInner=topicElement.elementIterator();topicInner.hasNext();){
							Element topicAttribute=topicInner.next();
							if(topicAttribute.getName().equals("name")){
								topicName=topicAttribute.getData();
							}
							if(topicAttribute.getName().equals("stream.number")){
								streamNums=topicAttribute.getData();
							}
						}
						prop.put(Project503String.TOPIC+topicCounter+"", topicName);
						prop.put(topicName, streamNums);
					}
					prop.put(Project503String.TOPIC_COUNT, topicCounter);
				}
				else
					prop.put(e.getName(), e.getData());
			}
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	};
	
	public static  ConsumerConfigParser getInstance(){
		if(instance==null)
			return new ConsumerConfigParser();
		else
			return instance;
	}
	
	public Properties getConsumerConfig(){
		return prop;
	}
}
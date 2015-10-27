package com.iscas.project503.kafka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.iscas.project503.util.JsonDataMessageParser;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerThread implements Runnable {

	private String topic;
	private KafkaStream<String, String> stream;
	
	private static Map<String,String> enviromentMap = null;
	private static List<Map<String,String>> alermMessageList = null;
	
	public ConsumerThread() {
	}
	
	public ConsumerThread(String topic, 
			KafkaStream<String, String> stream) {
		super();
		this.topic = topic;
		this.stream = stream;
	}

	public void run() {
		for(ConsumerIterator<String, String> it = stream.iterator();it.hasNext();){
    		MessageAndMetadata<String, String> data=it.next();
    		if(topic.equals("environmentInfo".trim())) {
    			enviromentMap = JsonDataMessageParser.enviromentMessageMap(data.message());
    			String termID = enviromentMap.get("termID");
    			HazelcastInstance client = HazelcastClient.newHazelcastClient();
    			Map<String,String> kafkaHazelcastMap = client.getMap( topic );
    			kafkaHazelcastMap.put(termID, data.message());
    			 
    		} else if(topic.equals("environmentAlarm".trim())) {
    			alermMessageList = JsonDataMessageParser.enviromentAlermMessageList(data.message());
    			String termID = enviromentMap.get("termID");
    			HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
    			Map<String,String> kafkaHazelcastMap = hazelcastInstance.getMap( topic );
    			kafkaHazelcastMap.put(termID, data.message());
    		}
    		System.out.println("rescive "+topic+" partition "+data.partition()+" message :"+data.key()+" "+data.message());
    		
    		if(topic.equals("humidity".trim())) {
    			try{  
		           // File file = new File("/home/cz/503/result/001.txt");  
		          //  if(file.createNewFile()){  
		              //  System.out.println("Create file successed");  
		         //   }  
		           String content = topic+"   "+  data.partition() + "  " +   data.offset() + "  " +  data.topic() + "   " ;
		          // method2("/home/cz/503/result/001.txt", content);
		        }catch(Exception e){  
		            System.out.println(e);  
		        }  
    		}
    		//topicMap.put(data.key(),data.message());
    	}
	}

	  private void method2(String fileName, String content) {   
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);     
            writer.write(content);     
            writer.write("\r\n");      
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }     
}

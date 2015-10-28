package com.iscas.project503.kafka.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.kafka.schema.AsynSendResponse;
import com.iscas.project503.util.MessageFactory;

public class AsynSendResponseTopic extends Topic{
	
	private static long termID=1;

	@Override
	public String getNextMessage() {
		AsynSendResponse as=new AsynSendResponse();
		as.setResult(MessageFactory.getNextRandomAlpha(12));
		as.setStatus(MessageFactory.getNextRandomNumber(1));
		as.setTermID(termID++ +"");
		
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(as);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}

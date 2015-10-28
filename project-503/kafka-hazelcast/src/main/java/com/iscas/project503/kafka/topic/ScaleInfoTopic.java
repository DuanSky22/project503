package com.iscas.project503.kafka.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.util.MessageFactory;
import com.iscas.project503.kafka.schema.ScaleInfo;

public class ScaleInfoTopic extends Topic{
	
	private static int termID=1;

	@Override
	public String getNextMessage() {
		ScaleInfo scale=new ScaleInfo();
		scale.setCropID(MessageFactory.getNextRandomNumber());
		scale.setCropName(MessageFactory.getNextRandomAlpha());
		scale.setGrade(MessageFactory.getNextRandomNumber(1));
		scale.setPlotID(MessageFactory.getNextRandomNumber());
		scale.setTime(MessageFactory.getCurrentDate());
		scale.setWeight(MessageFactory.getNextRandomNumber(2));
		scale.setWeightType(MessageFactory.getNextRandomNumber(1));
		scale.setTermID((termID++)+"");
		
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(scale);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}

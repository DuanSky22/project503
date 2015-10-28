package com.iscas.project503.kafka.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscas.project503.kafka.schema.WeatherInfo;
import com.iscas.project503.util.MessageFactory;

public class WeatherInfoTopic extends Topic{

	public static long termID=1;
	@Override
	public String getNextMessage() {
		
		WeatherInfo weather=new WeatherInfo();
		weather.setAirHumidity(MessageFactory.getNextRandomNumber(2));
		weather.setAirpressure(MessageFactory.getNextRandomNumber(2));
		weather.setAirTemperature(MessageFactory.getNextRandomNumber(2));
		weather.setLight(MessageFactory.getNextRandomNumber(2));
		weather.setRain(MessageFactory.getNextRandomNumber(3));
		weather.setTermID(termID++ +"");
		weather.setTime(MessageFactory.getCurrentDate());
		weather.setWindSpeed(MessageFactory.getNextRandomNumber(1));
		
		ObjectMapper mapper=new ObjectMapper();
		String result="";
		try {
			result = mapper.writeValueAsString(weather);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}

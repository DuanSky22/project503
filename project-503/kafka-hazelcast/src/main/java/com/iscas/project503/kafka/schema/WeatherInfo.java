package com.iscas.project503.kafka.schema;

public class WeatherInfo {

	private String termID;
	private String windSpeed;
	private String rain;
	private String airpressure;
	private String airTemperature;
	private String airHumidity;
	private String light;
	private String time;
	
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public String getAirpressure() {
		return airpressure;
	}
	public void setAirpressure(String airpressure) {
		this.airpressure = airpressure;
	}
	public String getAirTemperature() {
		return airTemperature;
	}
	public void setAirTemperature(String airTemperature) {
		this.airTemperature = airTemperature;
	}
	public String getAirHumidity() {
		return airHumidity;
	}
	public void setAirHumidity(String airHumidity) {
		this.airHumidity = airHumidity;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

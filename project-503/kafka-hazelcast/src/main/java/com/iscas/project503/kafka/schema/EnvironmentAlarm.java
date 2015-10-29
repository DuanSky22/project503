package com.iscas.project503.kafka.schema;

public class EnvironmentAlarm {
	
	private String streamNumber;
	private String termID;
	private String time;
	private String airTemperature;
	private String airHumidity;
	private String soilTemperature;
	private String soilHumidity;
	private String light;
	private String CO2Concentration;
	private AlarmInfo[] alarmInfo=new AlarmInfo[2];
	
	public String getStreamNumber() {
		return streamNumber;
	}
	public void setStreamNumber(String streamNumber) {
		this.streamNumber = streamNumber;
	}
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getSoilTemperature() {
		return soilTemperature;
	}
	public void setSoilTemperature(String soilTemperature) {
		this.soilTemperature = soilTemperature;
	}
	public String getSoilHumidity() {
		return soilHumidity;
	}
	public void setSoilHumidity(String soilHumidity) {
		this.soilHumidity = soilHumidity;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getCO2Concentration() {
		return CO2Concentration;
	}
	public void setCO2Concentration(String cO2Concentration) {
		CO2Concentration = cO2Concentration;
	}
	public AlarmInfo[] getAlarmInfo() {
		return alarmInfo;
	}
	public void setAlarmInfo(AlarmInfo[] alarmInfo) {
		this.alarmInfo = alarmInfo;
	}
	
	

}

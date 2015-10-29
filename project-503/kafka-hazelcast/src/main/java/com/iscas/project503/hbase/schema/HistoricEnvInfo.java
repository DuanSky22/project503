package com.iscas.project503.hbase.schema;

public class HistoricEnvInfo {
	
	private String streamNumber;
	private String termID;
	private String timeStamp;
	private String airTemperature;
	private String airHumidity;
	private String soilTemperature;
	private String soilHumidity;
	private String light;
	private String CO2Concentration;
	
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
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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

}

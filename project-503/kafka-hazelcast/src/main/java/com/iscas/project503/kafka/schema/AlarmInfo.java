package com.iscas.project503.kafka.schema;

public class AlarmInfo {
	
	private String alarmId;
	private String time;
	private String alarmGrade;
	private String alarmType;
	private String alarmDesp;
	private String termID;
	private String handler;
	private String handleTime;
	private String handleStatus;
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(String alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmDesp() {
		return alarmDesp;
	}
	public void setAlarmDesp(String alarmDesp) {
		this.alarmDesp = alarmDesp;
	}
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public String getHandleStatus() {
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	
	public enum AlarmType{
		
		TempratureHighAlarm("tempratureHighAlarm"),
		TempratureLowAlarm("tempratureLowAlarm"),
		WeatherWindSpeedAlarm("weather_windSpeedAlarm"),
		WeatherRainAlarm("weather_rainAlarm"),
		Wf_XXAlarm("wf_XXAlarm"),
		DeviceAlarm("deviceAlarm");
		
		
		private String description;
		
		public String getDescription(){
			return description;
		}
		
		private AlarmType(String description){
			this.description=description;
		}
	}

}

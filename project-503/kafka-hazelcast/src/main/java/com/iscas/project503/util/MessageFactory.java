package com.iscas.project503.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MessageFactory {

	private  String dataMessage = null;
	private  String topic = null;
	private  long alermID = 0;
	private  String streamNumber = null;
	private  String alrmType = null; // 报警源类型

	private  int termNums = 0;
	private  int airTemperature = 0;
	private  int airHumidity = 0;
	private  int soilTemperature = 0;
	private  int soilHumidity = 0;
	private  int light = 0;
	private  int CO2Concentration = 0;

	public MessageFactory() {
		super();
	}

	public MessageFactory(String topic, int termNums, String streamNumber,
			int airTemperature, int airHumidity, int soilTemperature,
			int soilHumidity, int light, int cO2Concentration) {
		super();
		this.topic = topic;
		this.termNums = termNums;

		this.streamNumber = streamNumber;
		this.airTemperature = airTemperature;
		this.airHumidity = airHumidity;
		this.soilTemperature = soilTemperature;
		this.soilHumidity = soilHumidity;
		this.light = light;
		this.CO2Concentration = cO2Concentration;
	}

	public  String getMessage() {
		if (topic.equals("environmentInfo")) {
			Date date = new Date();
			return dataMessage = "{"
					+ "streamNumber:"
					+ streamNumber
					+ ",termID:"
					+ getTermID()
					+ ",timeStamp:"
					+ new SimpleDateFormat("YYYYMMDDhhmmss").format(date)
							.toString() + ",airTemperature:"
					+ getFloat(airTemperature) + ",airHumidity:"
					+ getFloat(airHumidity) + ",soilTemperature:"
					+ getFloat(soilTemperature) + ",soilHumidity:"
					+ getFloat(soilHumidity) + ",light:" + getFloat(light)
					+ ",CO2Concentration:" + CO2Concentration + ",alrmType:"
					+ getAlarmSource() + "}";

		} else if (topic.equals("environmentAlarm")) {
			int tempID = getTermID();
			String tempAlarmType = getAlarmSource();
			String timpStamp = new SimpleDateFormat("YYYYMMDDhhmmss").format(
					new Date()).toString();
			return dataMessage = "{" + "streamNumber:" + streamNumber
					+ ",termID:" + tempID + ",timeStamp:" + timpStamp
					+ ",airTemperature:" + getFloat(airTemperature)
					+ ",airHumidity:" + getFloat(airHumidity)
					+ ",soilTemperature:" + getFloat(soilTemperature)
					+ ",soilHumidity:" + getFloat(soilHumidity) + ",light:"
					+ getFloat(light) + ",CO2Concentration:" + CO2Concentration
					+ ",alrmType:" + tempAlarmType + ",alarmInfo:[{"
					+ "alarmId:" + alermID + ",alarmTime:" + timpStamp
					+ ",alarmGrade:" + (int) (Math.random() * 10)
					+ ",alarmType:" + tempAlarmType + ",alarmDesp:"
					+ "this is cause by" + tempAlarmType + ",TermID" + tempID
					+ ",handler" + "" + ",handleTime" + "" + ",handleStatus:"
					+ HandleStatus.handled.getDescription() + "}]" + "}";
		}
		return null;
	}

	private  int getTermID() {
		return (int) (Math.random() * termNums);
	}

	private  float getFloat(int temp) {
		return (float) (Math.random() * temp);
	}

	private  String getAlarmSource() {
		return AlarmSource.getAlarmDescription();
	}

	public enum AlarmSource {

		HIGHTEMPERATURE("highTemperature"), HIGHSOILHUMIDITY("highSoilHumidity"), HIGHCO2CONCENTRATION(
				"highCO2"), HIGHLIGHT("highLight");
		private String description;

		private AlarmSource(String description) {
			this.description = description;
		}

		private String getDescription() {
			return description;
		}

		public static String getAlarmDescription() {
			int random = (int) (Math.random() * 4);
			String message = null;
			switch (random) {
			case 0:
				message = HIGHTEMPERATURE.getDescription();
				break;
			case 1:
				message = HIGHTEMPERATURE.getDescription();
				break;
			case 2:
				message = HIGHTEMPERATURE.getDescription();
				break;
			case 3:
				message = HIGHTEMPERATURE.getDescription();
				break;
			}
			return message;
		}
	}

	public enum HandleStatus {
		handled("handled"), unhanded("unhandled");
		private String description;

		private HandleStatus(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	/*public static void main(String[] args) {
		System.out.println(new MessageFactory("environmentInfo", 4,"00000000001", 30, 20, 25,
				15, 300, 10).getMessage());
		System.out.println(new MessageFactory("environmentAlarm", 4,"00000000001", 30, 20, 25,
				15, 300, 10).getMessage());
	}*/
}

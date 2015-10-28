package com.iscas.project503.util;

public final class Project503String {
	public static final String ID="Id";
	public static final String CREATE_TIME="createTime";
	public static final String CONTENT="content";
	
	//topic
	public static final String TOPIC="topic";
	public static final String TOPIC_ENVIRONMENTINFO="EnvironmentInfo";

	public static final String TOPIC_SCALEINFO="ScaleInfo";
	public static final String TOPIC_WEATHERINFO="WeatherInfo";
	public static final String TOPIC_ASYNSENDRESPONSE="AsynSendResponse";
	
	//topic & hazelcast
	public static final String TH_EVVIRONMENTALARM="EnvironmentAlarm"; 
	
	//hazelcast
	public static final String ENVINFO="envinfo";
	public static final String WEATHER="weather";
	public static final String WEATHER_KEY="weatherinfo";
	public static final String ALARMINFO="alarmInfo";
	
	//message
	public static final String MESSAGE_TERM_ID="termID";
	public static final String MESSAGE_ALARM_TYPE="alarmType";
	public static final String MESSAGE_PLOT_ID="plotID";
	public static final String MESSAGE_JSON_COMMA=",";
	public static final String MESSAGE_JSON_LEFT_BRACE="{";
	public static final String MESSAGE_JSON_RIGHT_BRACE="}";
	public static final String MESSAGE_JSON_LEFT_SQUARE_BRACKET="[";
	public static final String MESSAGE_JSON_RIGHT_SQUARE_BRACKET="]";
	
	public static final String STREAM_NUMS="stream.nums";
	public static final String TOPIC_COUNT="topicCount";
	public static final String ZOOKEEPER_CONNECT="zookeeper.connect";
	public static final String ZOOKEEPER_SESSION_TIMEOUT="zookeeper.session.timeout";
	public static final String AUTO_COMMIT_INTERVAL="auto.commit.interval";
	public static final String AUTO_OFFSET_RESET="auto.offset.reset";
	public static final String GROUP_ID="group.id";
	public static final String SERIALIZER_CLASS="serializer.class";
	

	
	public static final String INNER_SPLIT=":--:";
	public static final String MESSAGE_SPLIT="#--#";
}

package com.iscas.project503.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.iscas.project503.kafka.schema.AlarmInfo;
import com.iscas.project503.kafka.schema.AlarmInfo.AlarmType;

public class MessageFactory {
	
	private static long counter=0;
	
	private static int type=0;
	
	public static final char[] NUMBERS={
		'0','1','2','3','4','5','6','7','8','9'
	};
	
	public static final char[] ALPHAS={
		'a','b','c','d','e','f',
		'g','h','i','j','k','l',
		'm','n','o','p','q','r',
		's','t','u','v','w','x',
		'y','z'
	};
	
	private static Random random=new Random();
	
	public static String getNextOridinalNumber(){
		return String.valueOf(counter++);
	}
	
	public static String getNextRandomNumber(){
		return getNextRandomNumber(4);
	}
	
	public static String getNextRandomNumber(int n){
		String result="";
		int numbersLength=NUMBERS.length;
		for(int i=0;i<n;i++)
			result+=NUMBERS[random.nextInt(numbersLength)];
		return result;
	}
	
	public static String getNextRandomAlpha(){
		return getNextRandomAlpha(4);
	}
	
	public static String getNextRandomAlpha(int n){
		String result="";
		int alphaLength=ALPHAS.length;
		for(int i=0;i<n;i++)
			result+=ALPHAS[random.nextInt(alphaLength)];
		return result;
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");//设置日期格式
		return df.format(new Date());
	}
	
	public static String getNextAlarmType(){
		type=(++type) % AlarmType.values().length;
		return AlarmType.values()[type].getDescription();
	}
	
	public static void main(String args[]){
		System.out.println(getCurrentDate());
		System.out.println(getNextAlarmType());
	}

}
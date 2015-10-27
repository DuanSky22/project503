package com.iscas.project503.backup;

import java.util.Random;

public class MessageFactory {
	
	private static long counter=0;
	
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

}

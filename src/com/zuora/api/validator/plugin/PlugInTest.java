package com.zuora.api.validator.plugin;

import java.util.ArrayList;

public class PlugInTest {
	
	public void test(String str){
		System.out.println("Print the arg in UPPERCASE: " + str.toUpperCase());
	}
	
	public static boolean validateLength(String str){
		
		boolean result = true;
		
		if(str.length() > 50){
			result = false;
		}
		
		return result;
	}

	public static boolean paymentTerms(String str){
		boolean result = false;
		
		ArrayList <String> acceptableValues = new ArrayList<String>();
		acceptableValues.add("Due Upon Recipt");
		acceptableValues.add("Net30"); 
		acceptableValues.add("Net10");
		
		if(acceptableValues.contains(str)){
			result = true;
		}
		
		return result;
	}

}

package com.zuora.api.validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.csvreader.CsvReader;

 class RulesList {
	private ArrayList<Rule> rules;
	
	public RulesList(){
		rules = new ArrayList<Rule>();
	}
	
	public void addRule(Rule rule){
		rules.add(rule);
	}
	
	public Rule getRuleByHeader(String header){
		Rule result = null;
		
		for(Rule rule : rules){
			if(rule.getHeader().equals(header)){
				result = rule;
			}
		}
		
		
		return result;
	}
	
}


public final class mian {

	private static String className;
	private static String methodName;
	
	private static RulesList rules;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//plugInName = "PlugInTest";
		//methodName = "test";
		//paramName = "Account";
		System.out.println("Reading Rules from file: " + args[0]);
		rules = new RulesList();
		readRules("testRules.csv");
		
		System.out.println("Running Rules");
		//runRules();
		readData("testData.csv");
		
		System.out.println("You've been validated!");
		System.out.println("Happily Ever After");
		
	}
	
	private static void readRules(String fileName){
		
		try {
				CsvReader csvReader = new CsvReader(fileName);
				csvReader.readHeaders();
				while(csvReader.readRecord()){
						String haderName = csvReader.get(0);
						String value = csvReader.get(2);
					
						String[] delcare = value.split("\\.");
						className = delcare[0];
						methodName = delcare[1];
						Rule rule = new Rule(haderName, className, methodName);
						//rules.add(new Rule(haderName, className, methodName));
						rules.addRule(rule);
				}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void readData(String fileName){
		
		try {
				CsvReader csvReader = new CsvReader(fileName);
				csvReader.readHeaders();
				String[] headers = csvReader.getHeaders();
				while(csvReader.readRecord()){
					
					String value;
					for(String header : headers){
						value = csvReader.get(header);
						runRuleByHeader(header, value);
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private static void runRuleByHeader(String header, String value){
		
		//get Rule for header
		Rule rule = rules.getRuleByHeader(header);
		
		try {
			Class<?> c = Class.forName("com.zuora.api.validator.plugin." + rule.getClassName());
			Object t = c.newInstance();
			//System.out.println("Methods found: ");
			Method m = c.getMethod(rule.getMethodName(), String.class);
			//for(Method m : c.getDeclaredMethods()){
				//System.out.println(m.toGenericString());
				//if(m.getName().equals(rule.getMethodName())){
					if(!(Boolean) m.invoke(t, value)){
						System.out.println("Validation Exception");
					} else {
						System.out.println("Good.");
					}
				//}
			//}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// */	
	}
	

}

/**
 * 
 */
package com.zuora.api.validator;

/**
 * @author msullivan
 *
 */
public final class Rule {
	private final String header;
	private final String className; 
	private final String methodName;
	
	public Rule(String head, Boolean enumed, String[] values){
		header = head;
		className = null;
		methodName = null;
	}
	
	public Rule(String head, String cName, String mName){
		header = head;
		className = cName;
		methodName = mName;
	}
	
	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

}

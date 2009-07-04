/**
 * 
 */
package com.rollingwithcode.collect;

/**
 * Default Exception for the collections
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.0.1-snapshot
 */
public class CollectException extends RuntimeException {

	/**
	 *  Searial UID
	 */
	private static final long serialVersionUID = 4150660096566449899L;
	/**
	 * Basic Exception to englobe another exceptions
	 * @param message for the exception
	 * @param cause Exception
	 * @since 1.0
	 */
	public CollectException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Default Exception
	 * @param message to raises
	 * @since 1.0
	 */
	public CollectException(String message) {
		super(message);
	}
	
}

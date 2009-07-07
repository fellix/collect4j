/**
 * 
 */
package com.rollingwithcode.collect;

import java.util.Collection;

/**
 * Make conditions for the collection
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snaphost
 */
public class WhenHandler<E> {
	private Collection<E> collection;
	/**
	 * Defaults construtor
	 * @param collection
	 * @since 1.0
	 */
	public WhenHandler(Collection<E> collection) {
		super();
		this.collection = collection;
		if(collection == null){
			throw new NullPointerException("Collection cann't be null");
		}
	}
	/**
	 * Select some fields for filter
	 * @param fields to filter
	 * @return CompareHandler
	 * @since 1.0
	 * @see #when()
	 */
	public CompareHandler<E> when(Object...fields){
		return new CompareHandler<E>(fields, collection);
	}
	/**
	 * Use the default toString() method to compare
	 * @return CompareHandler
	 * @since 1.0
	 * @see #when(Object...)
	 */
	public CompareHandler<E> when(){
		return new CompareHandler<E>(null, collection);
	}
	
}

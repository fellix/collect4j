/**
 * 
 */
package com.rollingwithcode.collect;

import java.util.Collection;

/**
 * Main class for the DSL.
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snapshot
 */
public class Collect {
	/**
	 * Prepare a collection to search inside
	 * @param <T> Generic for the collection
	 * @param collection to eval
	 * @return WhenHandler
	 * @since 1.0
	 */
	public <T> WhenHandler<T> in(Collection<T> collection){
		if(collection == null){
			throw new NullPointerException("Collection can not be null");
		}
		return new WhenHandler<T>(collection);
	}
}

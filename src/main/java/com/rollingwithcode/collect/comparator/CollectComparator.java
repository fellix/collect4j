/**
 * 
 */
package com.rollingwithcode.collect.comparator;

import java.util.Collection;

/**
 * Interface used for compare the objects
 * @param <E> Generic type for the compare
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snapshot
 */
public interface CollectComparator<E> {
	/**
	 * Performe the Compare using this class
	 * @param original Collection to compare
	 * @param fields to compare
	 * @param values to match with the fields
	 * @return new Collection<E>
	 * @since 1.0 
	 */
	Collection<E> compare(Collection<E> original, Object[] fields, Object[] values);
}

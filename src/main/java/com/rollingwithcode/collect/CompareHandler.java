/**
 * 
 */
package com.rollingwithcode.collect;

import java.util.Collection;

import com.rollingwithcode.collect.comparator.CollectComparator;
import com.rollingwithcode.collect.comparator.EqualsComparator;
import com.rollingwithcode.collect.comparator.LikeComparator;

/**
 * Used to make compare using values
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snaphost
 */
public class CompareHandler<E> {
	
	private Object[] fields;
	private Collection<E> collection;
	/**
	 * Defaults construtor
	 * @param fields to filter
	 * @param collection to eval
	 * @since 1.0
	 */
	public CompareHandler(Object[] fields, Collection<E> collection) {
		super();
		this.fields = fields;
		this.collection = collection;
	}
	/**
	 * Compare object with when same (equals)
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see #compare(CollectComparator, Object...)
	 * @see EqualsComparator
	 */
	public Collection<E> eql(Object...values){
		return compare(new EqualsComparator<E>(), values);
	}
	/**
	 * Generic method for custom compare
	 * @param comparator the Comparator implementation
	 * @param values passed as parameters
	 * @return Collection for the result
	 * @since 1.0
	 */
	public Collection<E> compare(CollectComparator<E> comparator, Object...values){
		return comparator.compare(collection, fields, values);
	}
	
	/**
	 * Compare using like.
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see #primitiveLike(Object[])
	 * @see #objectLike(Object[])
	 * @throws CollectException
	 */
	public Collection<E> like(Object...values){
		return compare(new LikeComparator<E>(), values);
	}
}

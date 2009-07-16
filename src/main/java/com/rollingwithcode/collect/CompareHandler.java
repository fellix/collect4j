/**
 * 
 */
package com.rollingwithcode.collect;

import java.util.Collection;

import com.rollingwithcode.collect.comparator.CollectComparator;
import com.rollingwithcode.collect.comparator.EqualsComparator;
import com.rollingwithcode.collect.comparator.LikeComparator;
import com.rollingwithcode.collect.comparator.OnlyComparator;

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
	 * @see CollectComparator#compare(Collection, Object[], Object[])
	 */
	public Collection<E> compare(CollectComparator<E> comparator, Object...values){
		return comparator.compare(collection, fields, values);
	}
	
	/**
	 * Compare using like.
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see LikeComparator
	 * @throws CollectException
	 * @see #compare(CollectComparator, Object...)
	 */
	public Collection<E> like(Object...values){
		return compare(new LikeComparator<E>(), values);
	}
	/**
	 * Compare if any field is equals to all values.
	 * @param values for each fields
	 * new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see OnlyComparator
	 * @see #compare(CollectComparator, Object...)
	 * @throws CollectException
	 */
	public Collection<E> only(Object...values){
		return compare(new OnlyComparator<E>(), values);
	}
}

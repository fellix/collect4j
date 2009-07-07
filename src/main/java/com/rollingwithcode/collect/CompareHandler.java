/**
 * 
 */
package com.rollingwithcode.collect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import com.rollingwithcode.collect.comparator.CollectComparator;
import com.rollingwithcode.collect.comparator.EqualsComparator;

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
		if(fields != null && fields.length != values.length){
			throw new CollectException("Invalid value or field length to compare");
		}
		if(fields == null){
			return primitiveLike(values);
		}
		try{
			return objectLike(values);
		} catch (IllegalArgumentException e) {
			throw new CollectException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new CollectException(e.getMessage(), e);
		}
	}
	/**
	 * Verify if the toString (String.valueOf) of the object contains each passed value
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see Object#toString()
	 * @see String#valueOf(Object)
	 * @see #objectLike(Object[])
	 * @see #like(Object...)
	 */
	@SuppressWarnings("unchecked")
	private Collection<E> primitiveLike(Object[] values){
		Collection<E> newCollection = new ArrayList<E>();
		for(Object o : collection){
			for(Object value : values){
				if(String.valueOf(o).contains(String.valueOf(value))){
					if(!newCollection.contains(o)){
						newCollection.add((E)o);
					}
				}
			}
		}
		return newCollection;
	}
	/**
	 * Verify if the toString (String.valueOf) of the select fields in the object contains each passed value
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see Object#toString()
	 * @see String#valueOf(Object)
	 * @see #primitiveLike(Object[])
	 * @see #like(Object...)
	 */
	@SuppressWarnings("unchecked")
	private Collection<E> objectLike(Object[] values) throws IllegalArgumentException, IllegalAccessException{
		Collection<E> newCollection = new ArrayList<E>();
		Field[] keys = null;
		for(Object o : collection){
			if(keys == null){
				keys = o.getClass().getDeclaredFields();
			}
			Integer i = 0;
			for(Object field : fields){
				for(Field f : keys){
					if(f.getName().equals(field)){
						f.setAccessible(true);
						if(String.valueOf(f.get(o)).contains(String.valueOf(values[i]))){
							if(!newCollection.contains(o)){
								newCollection.add((E)o);
							}
						}
					}
				}
				i++;
			}
			
		}
		return newCollection;
	}
}

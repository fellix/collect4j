/**
 * 
 */
package com.rollingwithcode.collect.comparator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import com.rollingwithcode.collect.CollectException;

/**
 * Make an comparator with the contains method
 * @author Rafael Felix da Silva
 * @since 0.1-snapshot
 * @version 1.0
 */
public class LikeComparator<E> implements CollectComparator<E> {
	
	@Override
	public Collection<E> compare(Collection<E> original, Object[] fields, Object[] values) {
		if(fields != null && fields.length != values.length){
			throw new CollectException("Invalid value or field length to compare");
		}
		if(fields == null){
			return primitiveLike(original, fields, values);
		}
		try{
			return objectLike(original, fields, values);
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
	private Collection<E> primitiveLike(Collection<E> original, Object[] fields,
			Object[] values){
		Collection<E> newCollection = new ArrayList<E>();
		for(Object o : original){
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
	private Collection<E> objectLike(Collection<E> original, Object[] fields,
			Object[] values) throws IllegalArgumentException, IllegalAccessException{
		Collection<E> newCollection = new ArrayList<E>();
		Field[] keys = null;
		for(Object o : original){
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

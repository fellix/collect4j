/**
 * 
 */
package com.rollingwithcode.collect.comparator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import com.rollingwithcode.collect.CollectException;

/**
 * Compare values and use only if equals to all values
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 1.0-snapshot
 */
public class OnlyComparator<E> implements CollectComparator<E> {

	@Override
	public Collection<E> compare(Collection<E> original, Object[] fields,
			Object[] values) {
		if(fields != null && fields.length != values.length){
			throw new CollectException("Invalid value or field length to compare");
		}
		if(fields == null){
			return primitiveEquals(original, fields, values);
		}
		try {
			return objectEquals(original, fields, values);
		} catch (IllegalArgumentException e) {
			throw new CollectException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new CollectException(e.getMessage(), e);
		}
	}
	
	/**
	 * Compare when the object have same references (equals)
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see #objectEquals(Object[])
	 * @see #eql(Object...)
	 */
	@SuppressWarnings("unchecked")
	private Collection<E> primitiveEquals(Collection<E> original, Object[] fields,
			Object[] values){
		Collection<E> newCollection = new ArrayList<E>();
		for(Object o : original){
			boolean flag = true;
			for(Object value : values){
				flag = flag && o.equals(value);
			}
			if(flag){
				newCollection.add((E) o);
			}
		}
		return newCollection;
	}
	/**
	 * Compare values in the fields using reflection
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see #primitiveEquals(Object[])
	 * @see #eql(Object...)
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	private Collection<E> objectEquals(Collection<E> original, Object[] fields,
			Object[] values) throws IllegalArgumentException, IllegalAccessException{
		Collection<E> newCollection = new ArrayList<E>();
		Field[] keys = null;
		for(Object o : original){
			if(keys == null){
				keys = o.getClass().getDeclaredFields();
			}
			boolean flag = true;
			Integer i = 0;
			for(Object field : fields){
				for(Field f : keys){
					if(f.getName().equals(field)){
						f.setAccessible(true);
						Object vl =f.get(o);
						flag = flag && vl.equals(values[i]);
					}
				}
				i++;
			}
			if(flag){
				newCollection.add((E) o);
			}
			
		}
		return newCollection;
	}
	
}

/**
 * 
 */
package com.rollingwithcode.collect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to make compare using values
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snaphost
 */
public class CompareHandler {
	
	private Object[] fields;
	private Collection<?> collection;
	/**
	 * Defaults construtor
	 * @param fields to filter
	 * @param collection to eval
	 * @since 1.0
	 */
	public CompareHandler(Object[] fields, Collection<?> collection) {
		super();
		this.fields = fields;
		this.collection = collection;
	}
	/**
	 * Compare object with when same (equals)
	 * @param values for each fields
	 * @return new Collection (ArrayList) with the filter
	 * @since 1.0
	 * @see #primitiveEquals(Object[])
	 * @see #objectEquals(Object[])
	 * @throws CollectException
	 */
	public Collection<?> eql(Object...values){
		if(fields != null && fields.length != values.length){
			throw new CollectException("Invalid value or field length to compare");
		}
		if(fields == null){
			return primitiveEquals(values);
		}
		try {
			return objectEquals(values);
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
	private Collection<?> primitiveEquals(Object[] values){
		Collection<Object> newCollection = new ArrayList<Object>();
		for(Object o : collection){
			for(Object value : values){
				if(o.equals(value)){
					if(!newCollection.contains(o)){
						newCollection.add(o);
					}
				}
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
	private Collection<?> objectEquals(Object[] values) throws IllegalArgumentException, IllegalAccessException{
		Collection<Object> newCollection = new ArrayList<Object>();
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
						if(f.get(o).equals(values[i])){
							if(!newCollection.contains(o)){
								newCollection.add(o);
							}
						}
					}
				}
				i++;
			}
			
		}
		return newCollection;
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
	public Collection<?> like(Object...values){
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
	private Collection<?> primitiveLike(Object[] values){
		Collection<Object> newCollection = new ArrayList<Object>();
		for(Object o : collection){
			for(Object value : values){
				if(String.valueOf(o).contains(String.valueOf(value))){
					if(!newCollection.contains(o)){
						newCollection.add(o);
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
	private Collection<?> objectLike(Object[] values) throws IllegalArgumentException, IllegalAccessException{
		Collection<Object> newCollection = new ArrayList<Object>();
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
								newCollection.add(o);
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

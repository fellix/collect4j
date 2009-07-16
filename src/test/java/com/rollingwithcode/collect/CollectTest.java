/**
 * 
 */
package com.rollingwithcode.collect;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.rollingwithcode.collect.comparator.CollectComparator;

/**
 * Unit test for the Collect class
 * @author Rafael Felix da Silva
 * @version 1.0
 * @since 0.1-snapshot
 */
public class CollectTest {
	private Collect collect;
	private List<String> list;
	private Set<Person> persons;
	
	@Before
	public void before(){
		collect = new Collect();
		list = new ArrayList<String>();
		list.add("Banana");
		list.add("Apple");
		list.add("Coconut");
		list.add("Cherry");
		list.add("Guava");
		persons = new HashSet<Person>();
		persons.add(new Person("John", 32));
		persons.add(new Person("Edward", 18));
		persons.add(new Person("Rafael", 21));
		persons.add(new Person("Dan", 21));
	}
	
	@Test
	public void shouldReturnWhenHandler(){
		WhenHandler<String> when  = collect.in(list);
		assertNotNull(when);
	}
	@Test(expected=NullPointerException.class)
	public void shouldNotReturnWhenHandler(){
		collect.in(null);
	}
	@Test
	public void shouldCollectWithLikeWihoutFields(){
		Collection<String> ret = collect.in(list).when().like("C");
		assertEquals(2, ret.size());
	}
	@Test
	public void shouldCollectWithEqlWihoutFields(){
		Collection<String> ret = collect.in(list).when().like("Apple");
		assertEquals(1, ret.size());
	}
	@Test
	public void shouldCollectWithLikeWithFields(){
		Collection<Person> ret = collect.in(persons).when("name").like("a");
		assertEquals(3, ret.size());
	}
	@Test
	public void shouldCollectWithEqlWithFields(){
		Collection<Person> ret = collect.in(persons).when("age").eql(21);
		assertEquals(2, ret.size());
	}
	@Test
	public void shouldCollectWithLikeWithTwoFields(){
		Collection<Person> ret = collect.in(persons).when("name", "age").like("n", 2);
		assertEquals(3, ret.size());
	}
	@Test
	public void shouldCollectWithLikeEqlTwoFields(){
		Collection<Person> ret = collect.in(persons).when("name", "age").like("Rafael", 21);
		assertEquals(2, ret.size());
	}
	@Test
	public void shouldCollectWithCustomCompare(){
		Collection<Person> ret = collect.in(persons).when().compare(new CollectComparator<Person>(){

			@Override
			public Collection<Person> compare(Collection<Person> original,
					Object[] fields, Object[] values) {
				Collection<Person> newCollection = new ArrayList<Person>();
				for(Person p : original){
					for(Object v : values){
						if(p.getName().startsWith(String.valueOf(v))){
							newCollection.add(p);
						}
					}
				}
				return newCollection;
			}
			
		}, "R");
		assertEquals(1, ret.size());
	}
	@Test
	public void shouldCollectWithOnly(){
		Collection<Person> col = collect.in(persons).when("name", "age").only("Rafael", 21);
		assertEquals(1, col.size());
	}
	
	private class Person{
		private String name;
		private Integer age;
		public Person(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the age
		 */
		public Integer getAge() {
			return age;
		}
		/**
		 * @param age the age to set
		 */
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
}

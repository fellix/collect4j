# Collect4j
Collect4j is an DSL (Domain Specific Language) for manipulates Java Collections API.
Simple Commands do filter and access values in a collection.

## Using
Add the jar file collect4j-X.X.jar to your classpath, or dowload from maven repositories (repo1)

## Building from source
Needs:
<ul>
  <li>Sun SDK for Java 1.6</li>
  <li>Apache Maven 2.0.8</li>
</ul>
Clone this repository
Execute a comand mvn clean install


## Supports
 - eql for equals comparision
 - like for string content
 - only method, is same on eql but if eql receive two values is used or, only use and
 - supports generic (objects is supported too)
 - comparates some fields (Only for your custom class)

## TODO
 - Implements more Collection search (Like a regex)
 - Map Implementations

## Usage Samples
- Simple Collections
<pre>
  List<String> list = new ArrayList<String>();
	list.add("Uva");
	list.add("Maça");
	list.add("Melancia");
	list.add("Melão");
	list.add("Maracujá");
	Collection<String> busca = (Collection<String>) new Collect().in(lista).when().like("Mel", "Ma"); //Search in the collection when the content contatis the passed string.
</pre>

Generics Collections
<pre>
public class Person{
		String name;
		int age;

		public Person() {
			super();
		}

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		//Getters and setters
		/**
		 * Some cases the to string is used too
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Person["+name+", "+age+" ]";
		}

	}

	  List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Rafael", 21));
		persons.add(new Person("Suélen", 19));
		persons.add(new Person("Pedro", 23));
		persons.add(new Person("Rodrigo", 23));
	  Collection<Person> busca = (Collection<Person>) new Collect().in(persons).when("age").eql(23);//Compare with the field age is equals to 23, and return a new collection
	  Collection<Person> busca = (Collection<Person>) new Collect().in(persons).when().eql("23");//Compare the Person#toString() contais the string "23"
</pre>


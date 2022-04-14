package P2;

public class Person {

	private final String personName;
	
	// Abstraction function:
    //   AF(personName) = the name of a Person
    // Representation invariant:
    //   true
    // Safety from rep exposure:
    //	the field are private and final
	//	person is String,so are guaranteed immutable
    
 
	/**
	    * make a new person
	    * @param person  the name of the new Person
	    */
		public Person(String personName) {
			this.personName = personName;
		} 
       
	
	@Override
	public String toString()
	{
		return this.personName;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		return true;
	}
	
	
}

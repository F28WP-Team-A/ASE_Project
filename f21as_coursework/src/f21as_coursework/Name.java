package f21as_coursework;

/*
 * Class written by Matthieu Pesci, but uploaded
 * by Elliot Whitehouse due to IT issues.
 */

public class Name {
	
	private String firstName;
	private String lastName;
	
	public Name(String firstName, String secondName) {
		
		this.firstName = firstName;
		this.lastName = secondName;
		
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

}

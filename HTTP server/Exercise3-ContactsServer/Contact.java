
public class Contact {	
	private String name;
	private String email;
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	/* Override toString method to get the contact information 
	 *
	 */
	@Override
	public String toString() {
		return "Name: " + name+ "\r\nEmail: " + email +"\r\n";
	}
}

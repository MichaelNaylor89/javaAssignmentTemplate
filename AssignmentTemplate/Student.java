
public class Student extends Person{
	
	private int studentNumber; 
	private String courseTitle; 
	private String startDate; 
	private float bursary; 
	private String email;
	public Student(String name, String gender, String dob, String address, String postcode, int studentNumber,
			String courseTitle, String startDate, float bursary, String email) {
		super(name, gender, dob, address, postcode);
		
		this.studentNumber = studentNumber;
		this.courseTitle = courseTitle;
		this.startDate = startDate;
		this.bursary = bursary;
		this.email = email;
	}
	
	
	//Put your getters, setters and toString methods here with Javadoc ...
	
	
	
	
	

}

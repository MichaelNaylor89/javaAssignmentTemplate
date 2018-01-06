import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseTester {

	public static void main(String[] args) {
		
		ArrayList<Student> allStudents = new ArrayList<>();
		StudentDAO dao = new StudentDAO();
		
		try {
			allStudents = dao.getAllStudents();
			
		} catch (SQLException e) {
			System.out.println("SQL exception: "+e.getMessage());
			
		}
		System.out.println(allStudents);
	}
	
	//Add other tests here ...
}

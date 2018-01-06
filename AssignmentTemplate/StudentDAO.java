import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {

	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:studentdb.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}


	public ArrayList<Student> getAllStudents() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		String query = "SELECT * FROM students;";
		Student temp = null;
		ArrayList<Student> allStudents = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			while (resultset.next()) {

				String name = resultset.getString("Name");
				String gender = resultset.getString("Gender");
				String dob = resultset.getString("DOB");
				String address = resultset.getString("Address");
				String postcode = resultset.getString("Postcode");

				int stuID = resultset.getInt("StudentNumber");
				String courseTitle = resultset.getString("CourseTitle");
				String startDate = resultset.getString("StartDate");
				Float bursary = resultset.getFloat("Bursary");
				String email = resultset.getString("Email");

				temp = new Student(name, gender, dob, address, postcode, stuID, courseTitle, startDate,bursary ,email);

				allStudents.add(temp);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return allStudents;
	}

	public Student getStudent(String id) throws SQLException {
		Student temp = null;

		//put your code here...
		
		return temp;
	}



	public Boolean insertStu(Student stu) throws SQLException {
		
		// put your code here
		
		return false;
	}

	public Boolean deleteStu(String stuID) throws SQLException {
		
		//put your code here
		
		return false;
	}

	public Boolean updateStu(Student stu) throws SQLException {
		
		//put your code here
		
		return false;
	}

	public Boolean checkLoginCredentials(String username, String password) throws SQLException {

		//put your code here
		return false;	
	}

	public boolean checkApiKey(String key) throws SQLException {
		
		//put your code here
		return false;
	}
}

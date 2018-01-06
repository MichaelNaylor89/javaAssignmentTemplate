import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebServiceTester {


	public static void main(String[] args) {
		System.out.println("Students = " + getStudents());
	}

	private static StringBuffer getStudents() {

		StringBuffer response = new StringBuffer();
		try {

			URL url = new URL("http://localhost:8005/get-all");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String output;

			while ((output = reader.readLine()) != null) {
				response.append(output);
			}
			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	private static StringBuffer getStudent(int studentId) {
		StringBuffer response = new StringBuffer();
		response= null;
		//put your code here...
		return response;
	}


	private static void postStudent() throws IOException {
		//put your code here...
	}


	private static void updateStudent() throws IOException {
		//put your code here
	}


	private static void deleteStudent() throws IOException {
		//put your code here
	}

}

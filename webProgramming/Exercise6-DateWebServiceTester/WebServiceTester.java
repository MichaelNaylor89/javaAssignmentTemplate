import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WebServiceTester {
	
	public static void main(String[] args) {		
		System.out.println("Date = " + getDateTest());		
	}
	
	private static StringBuffer getDateTest() {		
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL("http://localhost:3000/get-date");
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
}

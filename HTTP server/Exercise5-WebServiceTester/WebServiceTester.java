import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WebServiceTester {

	public static void main(String[] args) {

		try {
			System.out.println("Contacts = " + getContacts());
			postContact();
			System.out.println("Contacts = " + getContacts());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static StringBuffer getContacts() throws IOException {

		StringBuffer response = new StringBuffer();
		//URL url = new URL("http://localhost:8000/get_json");
		URL url = new URL("http://localhost:8000/");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String output;

		while ((output = reader.readLine()) != null) {
			response.append(output);
		}
		reader.close();
		return response;
	}

	private static void postContact() throws IOException {

		String urlParameters = "name=Adam Test&email=adam@mail.com";
		URL url;
		url = new URL("http://localhost:8000/add_contact");
		// create and configure the connection object
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(15000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		// write/send/POST data to the connection using output stream and buffered writer
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(urlParameters);

		// clear the writer
		writer.flush();
		writer.close();
		// close output stream
		os.close();
		// get the server response code to determine what to do next (i.e. success/error)
		String response = "";
		String line;
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode = " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				response += line;
			}
		} 
		System.out.println("response = " + response);
		System.out.println("Insert Done!!");

	}

}

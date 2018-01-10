import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

	public static void main(String[] args) throws IOException  {
		URL url;
		BufferedReader in =null;
		try {
			url = new URL("http://www2.mmu.ac.uk/");
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL found " + e );

		}
		catch (IOException io) {
			System.out.println("Connection problem "+io);
		}
		finally {
			in.close();
		}
	}
}

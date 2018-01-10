
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ControllerClient {

	public static void main(String[] args) throws IOException {		
		Socket s = new Socket("localhost", 3000);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String currentDate = input.readLine();
        System.out.println("Date from server: "+currentDate);
	}
}
		
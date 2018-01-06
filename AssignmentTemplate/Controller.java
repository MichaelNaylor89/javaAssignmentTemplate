import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

// Add Javadoc ..

public class Controller {

//Add Javadoc ..
	
	public static void main(String[] args) {
		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8005), 0);
		
			server.createContext("/", new HomeHandler());
			server.createContext("/get-all", new GetHandler());
			
			//Add your other handlers here ....	

			// start the server
			server.setExecutor(null);
			server.start();
			System.out.println("Server running on port 8005");

		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}

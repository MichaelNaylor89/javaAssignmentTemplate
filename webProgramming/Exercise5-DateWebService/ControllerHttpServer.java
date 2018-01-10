import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
 /**
  * Date RESTful web service at http://localhost:3000/get-date
  * @author Alan Crispin
  *
  */
public class ControllerHttpServer {

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
			server.createContext("/", new GetHomeHandler());
			server.createContext("/get-date", new GetDateHandler());
			server.setExecutor(null); //default implementation of threading
			// start the server
			server.start();
			System.out.println("Server running on port 3000");
		}
		catch (IOException io) {
			System.out.println("Connection problem: "+io);
		}
	}
}

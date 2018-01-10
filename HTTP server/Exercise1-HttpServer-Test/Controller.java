import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Controller {

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
			server.createContext("/", new GetHomeHandler());
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

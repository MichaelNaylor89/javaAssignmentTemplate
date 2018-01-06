import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HomeHandler implements HttpHandler {
	
	//Example handler - add Javadoc
	
	public void handle(HttpExchange he) throws IOException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		he.sendResponseHeaders(200, 0);
		out.write("Welcome to the student webservice");
		out.close();

	}

}

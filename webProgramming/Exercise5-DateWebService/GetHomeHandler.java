
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetHomeHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		he.sendResponseHeaders(200, 0);
		out.write("RESTful date web Service - to access the date use URL localhost:3000/get-date");
		out.close();
	}
}

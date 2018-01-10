import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetInfoHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		he.sendResponseHeaders(200, 0);
		out.write("Use /get?firstname=Alan&lastname=Crispin to see how to handle url parameters");
		out.close();
	}
}

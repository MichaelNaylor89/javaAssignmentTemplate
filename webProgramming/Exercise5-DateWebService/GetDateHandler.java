import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetDateHandler  implements HttpHandler {	
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		he.sendResponseHeaders(200, 0);
		out.write(new Date().toString());
		out.close();
	}
}

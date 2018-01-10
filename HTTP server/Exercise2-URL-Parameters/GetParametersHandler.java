import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetParametersHandler implements HttpHandler {
	
	public void handle(HttpExchange httpExchange) throws IOException {
	      StringBuilder response = new StringBuilder();
	      Map <String,String>parms = GetParametersHandler.queryToMap(httpExchange.getRequestURI().getQuery());
	      response.append("<html><body>");
	      response.append("Forename : " + parms.get("firstname") + "<br/>");
	      response.append("Surname : " + parms.get("lastname") + "<br/>");
	      response.append("</body></html>");
	      GetParametersHandler.writeResponse(httpExchange, response.toString());
	    }
	 public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
		    httpExchange.sendResponseHeaders(200, response.length());
		    OutputStream os = httpExchange.getResponseBody();
		    os.write(response.getBytes());
		    os.close();
		  }
		  /**
		   * returns the url parameters in a map
		   * @param query
		   * @return map
		   */
		  public static Map<String, String> queryToMap(String query){
		    Map<String, String> result = new HashMap<String, String>();
		    for (String param : query.split("&")) {
		        String pair[] = param.split("=");
		        if (pair.length>1) {
		            result.put(pair[0], pair[1]);
		        }else{
		            result.put(pair[0], "");
		        }
		    }
		    return result;
		  }	
}

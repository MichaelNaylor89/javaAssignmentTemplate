import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Controller {
	
	public static void main(String[] args) {
		
		final ContactsDAO dao = new ContactsDAO();		
	
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
			
			//list contacts...
			server.createContext("/", new HttpHandler() {
	
				@Override
				public void handle(HttpExchange he) throws IOException {
					System.out.println("display all");
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					ArrayList<Contact> contacts = dao.selectAllContacts();
					
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
									
					// Use GSON to convert the contacts arraylist to a json string and ouptut to browser
					for (Iterator<Contact> iterator = contacts.iterator(); iterator.hasNext();) {
						Contact contact = (Contact) iterator.next();
						out.write(contact.getName() + " " + contact.getEmail() + "\n" );
						System.out.println(contact.getName());
					}
					
					out.close();
				}
				
			});
			
			server.createContext("/add_contact",new HttpHandler() {
				//process data from /add form
				@Override
				public void handle(HttpExchange he) throws IOException {
					System.out.println("adding new contact");
					HashMap<String,String> post = new HashMap<String,String>();
					//read the request body
					BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
					String line = "";
					String request = "";
					while((line = in.readLine()) != null) {
						request = request + line;
					}
					System.out.println(">>>>>>>>>>>>>>> "+request);
					//individual key=value pairs are delimited by ampersands. Tokenize.
					String[] pairs = request.split("&");					
					for(int i=0;i<pairs.length;i++) {
						//each key=value pair is separated by an equals, and both halves require URL decoding.
						String pair = pairs[i];
						post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
					}					
					//Should have a HashMap of posted data in our "post" variable. Now to add a contact
					Contact c = new Contact(post.get("name"), post.get("email"));
					
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
					try { 
						dao.insertContact(c); 
						he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
						out.write("Success!");
						out.close();
					}
					catch(SQLException se) {
						 he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
						 out.write("Error Adding Contact");
						 out.close();
					}
				}
				
			});
			
			
			server.createContext("/get_json", new HttpHandler() {
				
				@Override
				public void handle(HttpExchange he) throws IOException {
					
					System.out.println("display all JSON");
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					ArrayList<Contact> contacts = dao.selectAllContacts();
					Gson gson = new Gson();
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
					String contactsJson = gson.toJson(contacts);  // convert the contacts array list to a json string using GSON
					out.write(contactsJson);
					out.close();
					System.out.println(contactsJson);
					
				}
			});
			
			//actually start the server
			System.out.println("Server running on port 8000");
			server.start();
		}
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage() + "  " + ioe.getStackTrace());
		}
		
	}//main

}

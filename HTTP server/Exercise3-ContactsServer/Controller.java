import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.sun.net.httpserver.*;

public class Controller {

	public static void main(String[] args) throws SQLException {
		
		final ContactsDAO dao = new ContactsDAO();
		ArrayList<Contact> contactsList = new ArrayList<Contact>();
		
		//Print all contacts using DAO SelectAllContacts
		if(contactsList != null)
			  contactsList = dao.selectAllContacts();	
		
		for (Contact c : contactsList){		     
		    System.out.println(c);
		}
		
		/*
		//Test contact insertion using DAO insertContact				
		Contact contact = new Contact();		
		contact.setName("Fred Bloggs");
		contact.setEmail("fred@mail.com");		
		dao.insertContact(contact);
		
		//Print all contacts using DAO SelectAllContacts
		if(contactsList != null)
			contactsList = dao.selectAllContacts();	

		for (Contact c : contactsList){		     
			System.out.println(c);
		}
		*/
		
		
		//Develop web front end for the contacts application
		
		//GET operation		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
			
			//list contacts...
			server.createContext("/", new HttpHandler() {

				@Override
				public void handle(HttpExchange he) throws IOException {
					final String head = "<html><head></head><body><table><tr><th>Name</th><th>Email</th></tr>";
					final String foot = "</table></body></html>";
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					
					ArrayList<Contact> contacts = dao.selectAllContacts();
					
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
					out.write(head);					
					
					for(Contact c: contacts) {
						out.write(
							"<tr><td>" + c.getName() 
							+ "</td><td>" + c.getEmail() 							
						);
					}
													
					out.write(foot);
				
					out.close();
				}
				
			});
			
			//POST operation			
			server.createContext("/post",new HttpHandler() {
				@Override
				public void handle(HttpExchange he) throws IOException {
					//output HTML form
					he.sendResponseHeaders(200, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					out.write("<html><head></head><body><form method=\"POST\" action=\"/add_write\">");
					out.write("Name:<input name=\"name\"><br>");
					out.write("Email:<input name=\"email\"><br>");
					out.write("<input type=\"submit\" value=\"Submit\">");
					out.write("</form></body></html>");
					out.close();
				}				
			});
			
			server.createContext("/add_write",new HttpHandler() {
				//process data from /add form
				@Override
				public void handle(HttpExchange he) throws IOException {
					HashMap<String,String> post = new HashMap<String,String>();
					//read the request body
					BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
					String line = "";
					String request = "";
					while((line = in.readLine()) != null) {
						request = request + line;
					}
					//individual key=value pairs are delimited by ampersands. Tokenize.
					String[] pairs = request.split("&");					
					for(int i=0;i<pairs.length;i++) {
						//each key=value pair is separated by an equals, and both halves require URL decoding.
						String pair = pairs[i];
						post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
					}					
					//Should have a HashMap of posted data in our "post" variable. Now to add a contact
					Contact c = new Contact();
					c.setName(post.get("name"));
					c.setEmail(post.get("email"));

					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
					try { 
						dao.insertContact(c); 
						he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
						out.write("Success!");
					}
					catch(SQLException se) {
						 he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
						 out.write("Error Adding Contact");
					}
				}
				
			});
			
			//actually start the server
			server.start();
		}
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage() + "  " + ioe.getStackTrace());
		}
		
	}//main

}//Controller class

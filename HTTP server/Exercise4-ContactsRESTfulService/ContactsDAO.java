import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContactsDAO {
	Connection c = null;
	Statement s= null;
	ResultSet r = null;
	
	/**Get Database Connection
	 *  
	 * @return Statement Object
	 */
   public Statement getConnection() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:contactsdb.sqlite");
			s = c.createStatement();				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return s;
    }
   /**Close any open database connection
    * 
    */
   public void closeConnection() {
	   try {
		   if(s!=null) {
			   s.close();
		   }
		   if(c!=null) {
			   c.close();
		   }
	} catch (SQLException e) {
			e.printStackTrace();
	}	   
   }
   
   /**Retrieve all Contacts
	 * 
	 * @return
	 */
	public ArrayList<Contact> selectAllContacts() {
		
		ArrayList<Contact> contactsArray = new ArrayList<Contact>();
		try {
			String sql = "select * from  contacts";
			ResultSet rs = getConnection().executeQuery(sql);
			
			if(rs != null) {
				while(rs.next()) {
					
					Contact contact = new Contact();
					
					try {
						 contact.setName(rs.getString("Name"));
						 contact.setEmail(rs.getString("Email"));					
					   
					   }
					   catch(SQLException s) {
						   s.printStackTrace();
					   }					
					
					contactsArray.add(contact);
				}
				
				rs.close();
			}
		}
		catch (SQLException s) {
			
		}

		closeConnection();
		return contactsArray;
	 }
	
	
	
	/**
	 * Insert Contact into database
	 * 
	 * @param c            		Contact Object
	 * @return				 	True if inserted
	 * @throws SQLException  	Any error message thrown
	 */
	public boolean insertContact(Contact c) throws SQLException {
		
		boolean b = false;
		try {
			String sql = "insert into contacts (Name, Email) values ('"+c.getName() + "','" +   c.getEmail() + "');";	
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
		}
		catch (SQLException s) {
			throw new SQLException("Contact Not Added");
		}	  
		return b;	 }
}

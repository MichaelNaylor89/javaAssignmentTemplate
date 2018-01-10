import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {

	public static void main(String[] args) {
		
		try {

			System.out.println("Show all customers");
			ShowAllCustomers();	
			System.out.println("Show all orders");
			ShowAllOrders();
			System.out.println("Show all orders for Alan Crispin");
			ShowOrdersForCustomer(1);
			System.out.println("Show ProductID order list");
			ShowProductOrdersList();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		

	}
	
	
	private static void ShowAllCustomers() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;

		String query = "SELECT * FROM customers;";


		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query

			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				System.out.println(resultset.getString("CustomerID") + " "
						+ resultset.getString("CustomerName")+ " "
						+ resultset.getString("CustomerEmail"));
			}	

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (resultset != null) {
				resultset.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private static void ShowAllOrders() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;

		String query = "SELECT * FROM orders;";


		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query

			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				System.out.println(resultset.getString("OrderID") + " "
						+ resultset.getString("ProductID")+ " "
						+ resultset.getString("CustomerID")+" "
                                                + resultset.getString("OrderDate"));
			}	

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (resultset != null) {
				resultset.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	private static void ShowOrdersForCustomer(int i) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;

		String query = "SELECT customers.CustomerName, orders.ProductID, orders.OrderDate FROM orders INNER JOIN customers ON orders.customerID=customers.customerID WHERE customers.customerID =" +i+";";


		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query

			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				System.out.println(resultset.getString("CustomerName") + " "+ resultset.getString("ProductID")+ " "+ resultset.getString("OrderDate"));
				
				
			}	

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (resultset != null) {
				resultset.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private static void ShowProductOrdersList() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;

		String query = "SELECT orders.ProductID, customers.CustomerName, orders.OrderDate FROM orders INNER JOIN customers ON orders.customerID=customers.customerID"; 



		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query

			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				System.out.println(resultset.getString("ProductID") + " "+ resultset.getString("CustomerName")+ " "+ resultset.getString("OrderDate"));
				
				
			}	

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (resultset != null) {
				resultset.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}


	}
	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			String dbURL = "jdbc:sqlite:shopdb.sqlite";	         		
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;
	}


}

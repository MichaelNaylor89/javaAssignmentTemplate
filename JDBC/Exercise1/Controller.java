import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {

	public static void main(String[] args) {

		try {

			ShowAllRecords();
			insertRecordIntoCollectionTable();
			ShowAllRecords();			


		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}


	}

	private static void insertRecordIntoCollectionTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String   query = "INSERT INTO collection (ID, Title, Genre, Year)  VALUES (5,'Divergent','Sci Fi',2014);";


		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute insert SQL statement
			statement.executeUpdate(query);

			System.out.println("Record is inserted into connection table");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static void ShowAllRecords() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;

		String query = "SELECT * FROM collection;";


		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query

			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				System.out.println(resultset.getString("ID"));
				System.out.println(resultset.getString("Title"));
				System.out.println(resultset.getString("Genre")); 
				System.out.println(resultset.getString("Year"));
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

			String dbURL = "jdbc:sqlite:dvd.sqlite";	         		
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;
	}

}

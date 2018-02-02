package jpspackage;

//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DBObject {
	private static final String MYSQL_DATABASE_SERVER = "localhost:3306";
	private static final String MYSQL_DATABASE_NAME = "PAGE_SCRAPER_DB";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "meri";

	public DBObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		try {
			String connect = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/" + MYSQL_DATABASE_NAME;
			return DriverManager.getConnection(connect, MYSQL_USERNAME, MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("MySQL user password server or db name is incorrect!");
			return null;
		}
	}
	
	public void addLink() {
		
	}
	
	public void addImageSource() {
		
	}
}

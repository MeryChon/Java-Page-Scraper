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
import java.util.HashSet;
import java.util.Set;

public class DBObject {
	private static final String MYSQL_DATABASE_SERVER = "localhost:3306";
	private static final String MYSQL_DATABASE_NAME = "PAGE_SCRAPER_DB";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "meri";
	
	private static final String TABLE_URLS = "visited_urls";
	private static final String TABLE_SCRAPED_LINKS = "scraped_links";
	private static final String TABLE_SCRAPED_IMAGE_SOURCES = "scraped_image_sources";

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
	
	
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private boolean urlInDatabase(String url, Connection con) {
		return false;
	}
	
	
	/**
	 * Returns result set generated by executing given query;
	 * 
	 * @param query
	 * @return {@link ResultSet}
	 */
	private ResultSet getResultSet(String query, Connection conn) {
		ResultSet result = null;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + MYSQL_DATABASE_NAME + " ;");
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * Executes update queries, that is queries which cause changes in tables of
	 * the database;
	 * 
	 * @param query
	 * @throws SQLException
	 */
	private int executeUpdate(String query, Connection conn) {
		int id = 0;
		try {
			Statement stmt1;
			stmt1 = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt1.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt1.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/*
	 * Get id of a table entry with given url;
	 */
	private int getURLid(String url, Connection conn) throws SQLException {
		String query = "SELECT * FROM "+TABLE_URLS+" WHERE url like '"+url+"';";
		System.out.println(query);
		ResultSet rs = this.getResultSet(query, conn);
		if(!rs.next()) {
			return -1;
		} else {
			return rs.getInt("id");
		}
	}
	
	
	public void addLinks(String url, Set<String> scrapedLinks) throws SQLException {
		Connection conn = getConnection();
		int urlID = getURLid(url, conn);
		if(urlID < 0) {
			System.out.println("need to add new url");
			urlID = addURL(url, conn);
		}
		String queryLinksForGivenURL = "SELECT link_url FROM "+TABLE_SCRAPED_LINKS+" WHERE url_id = "
																								+ urlID + ";";
		System.out.println(queryLinksForGivenURL);
		ResultSet rs = getResultSet(queryLinksForGivenURL, conn);
		Set<String> linksInDatabase = new HashSet<String>();
		while(rs.next()) {
			linksInDatabase.add(rs.getString("link_url"));
		}
		for(String link: scrapedLinks) {
			if(!linksInDatabase.contains(link)) {
				addLink(link, urlID, conn);
			}
		}		
		closeConnection(conn);
	}
	
	
	private int addLink(String link, int urlId, Connection conn) {
		String query = "INSERT INTO "+TABLE_SCRAPED_LINKS+" (url_id, link_url) VALUES ("+urlId+", "
				+ "'" + link + "');";
//		System.out.println(query);
		int newLinkEntryId = executeUpdate(query, conn);
		return newLinkEntryId;
		
	}

	private int addURL(String url, Connection conn) {
		String query = "INSERT INTO "+TABLE_URLS+" (url) VALUES ('"+ url +"');";
		System.out.println(query);
		return executeUpdate(query, conn);		
	}

//	public boolean addLink(String url, String link) throws SQLException {
//		Connection conn = this.getConnection();
//		int urlID = getURLid(url, conn);
//		if (urlID >= 0) {
//			System.out.println("URL already in database");
//		} else {
//			String urlsQuery = "INSERT INTO " + TABLE_URLS+" (url) VALUES " + " ('"+ url + "');";
//			urlID = executeUpdate(urlsQuery, conn);
//		}
//		String linksQuery = "INSERT INTO "+TABLE_SCRAPED_LINKS+" (url_id, link_url) VALUES ('"+ urlID + ", "
//				+ "'"+link+"');";
//		int linkId = executeUpdate(linksQuery, conn);
//		
//		closeConnection(conn);
//		return true;
//		
//	}
	
	public void addImageSources(String url, Set<String> imageSources) {
		System.out.println("NEED TO ADD IMAGE");
	}
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtility {
	
	public String url = "jdbc:mysql://localhost:3306/";
	public String dburl = "jdbc:mysql://localhost:3306/inventorytracker";
	public String user = "root";
	public String pass = "";
	private String dbDriver = "com.mysql.jdbc.Driver";
	
	public DatabaseUtility() {
		
	}
	
	public void createDatabase() {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Opened database successfully");
			
			stmt = con.createStatement();
			
			stmt.execute("CREATE DATABASE IF NOT EXISTS inventorytracker");
			stmt.execute("USE inventorytracker");
			
			stmt.close();
			con.close();
			System.out.println("Database created successfully");
		}catch(Exception e) {
			System.err.println("\nPostgreSQL JDBC Driver Not Found.");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
	}
	
	public void createUserTable() {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dburl, user, pass);
			System.out.println("Opened database successfully");
			
			stmt = con.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS `users` ("
					+ "`id` INT UNSIGNED AUTO_INCREMENT NOT NULL, "
					+ "`active` BOOLEAN NOT NULL, "
					+ "`firstname` VARCHAR(32) NOT NULL, "
					+ "`lastname` VARCHAR(32) NOT NULL, "
					+ "`username` VARCHAR(32), "
					+ "`email` VARCHAR(32) NOT NULL, "
					+ "`password` VARCHAR(32) NOT NULL, "
					+ "`rights` BOOLEAN NOT NULL, "
					+ "PRIMARY KEY (`id`), "
					+ "UNIQUE (`email`)"
					+ ")";
			
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
			System.out.println("Users: Table created successfully");
		}catch(Exception e) {
			System.err.println("\nPostgreSQL JDBC Driver Not Found.");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public void createItemTable() {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dburl, user, pass);
			System.out.println("Opened database successfully");
			
			stmt = con.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS `items` ("
					+ "`id` INT UNSIGNED AUTO_INCREMENT NOT NULL, "
					+ "`active` BOOLEAN NOT NULL, "
					+ "`itemname` VARCHAR(32) NOT NULL, "
					+ "`description` VARCHAR(32) NOT NULL, "
					+ "`owner` VARCHAR(32) NOT NULL, "
					+ "PRIMARY KEY (`id`))";
			
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
			System.out.println("Items: Table created successfully");
		}catch(Exception e) {
			System.err.println("\nPostgreSQL JDBC Driver Not Found.");
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

}

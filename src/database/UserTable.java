package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserTable {
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private String dbDriver = "com.mysql.jdbc.Driver";
	
	public UserTable() {
		
	}
	
	public void insertAdmin(String firstName, String lastName, String userName, String email, String password, boolean rights) {
		System.out.println("Inserting admin!");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			con.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = ""
					+ "INSERT INTO `users`(`active`, `firstname`, `lastname`, `username`, `email`, `password`, `rights`) "
					+ "SELECT * FROM (SELECT true, ?, ?, ?, ?, ?, ?) AS tmp " 
					+ "WHERE NOT EXISTS ( SELECT rights FROM users WHERE rights = '1' AND username = 'admin' ) LIMIT 1;";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, userName);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			pstmt.setBoolean(6, rights);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
			System.out.println("Admin: Records created successfully");
		}catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}
	
	public void insertUser(String firstName, String lastName, String userName, String email, String password, boolean rights) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			con.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = ""
					+ "INSERT INTO `users`(`active`, `firstname`, `lastname`, `username`, `email`, `password`, `rights`) "
					+ "VALUES (true,?,?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "`active` = VALUES(active), "
					+ "`firstname` = VALUES(firstname), "
					+ "`lastname` = VALUES(lastname), "
					+ "`username` = VALUES(username), "
					+ "`email` = VALUES(email), "
					+ "`password` = VALUES(password), "
					+ "`rights` = VALUES(rights);";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, userName);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			pstmt.setBoolean(6, rights);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
			System.out.println("Users: Records created successfully");
		}catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}
	
	public boolean confirmLogin(String userName, String password) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean bool = false;
		
		try {
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			pst = con.prepareStatement("SELECT username, password FROM users");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				if(userName.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					bool = true;
					break;
				}
				System.out.println(userName + " = " + rs.getString(1));
				System.out.println(password + " = " + rs.getString(2));
			}
			con.close();
			pst.close();
			rs.close();
		}catch(Exception e) {
			System.err.println("\nConfirmLogin: Retrieving data failed.");
		}
		return bool;
	}
	
	public boolean confirmUniqueKeys(String userName, String email) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean bool = false;
		
		try {
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			pst = con.prepareStatement("SELECT username, email FROM users");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				if(userName.equals(rs.getString(1)) || email.equals(rs.getString(2))) {
					bool = true;
					break;
				}
				System.out.println(userName + " != " + rs.getString(1));
				System.out.println(email + " != " + rs.getString(2));
			}
			con.close();
			pst.close();
			rs.close();
		}catch(Exception e) {
			System.err.println("\nUniqueKeys: Retrieving data failed.");
		}
		return bool;
	}
	
	public boolean checkUserName(String userName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean bool = false;
		System.out.println("CheckUserName: " + userName);
		
		try {
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			pst = con.prepareStatement("SELECT username FROM users");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				if(userName.equals(rs.getString(1))) {
					bool = true;
					break;
				}
			}
			con.close();
			pst.close();
			rs.close();
		}catch(Exception e) {
			System.err.println("\nRetrieving data failed for username." + " \"" + userName + "\"");
		}
		return bool;
	}
	
	public boolean checkEmail(String email) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean bool = false;
		System.out.println("CheckEmail: " + email);
		
		try {
			con = DriverManager.getConnection(dbu.url, dbu.user, dbu.pass);
			pst = con.prepareStatement("SELECT email FROM users");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				if(email.equals(rs.getString(1))) {
					bool = true;
					break;
				}
				System.out.println(email + " | " + rs.getString(1));
			}
			con.close();
			pst.close();
			rs.close();
		}catch(Exception e) {
			System.err.println("\nRetrieving data failed for email.");
		}
		return bool;
	}
	
	public int getUserId(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int userId = -1;
		System.out.println("CheckEmail: " + email);
		
		try {
			con = DriverManager.getConnection(dbu.url, dbu.user, dbu.pass);
			
			String sql = "SELECT `id` FROM users WHERE `email` = ?";
			
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userId = rs.getInt(1);
				System.out.println(email + " | " + rs.getString(1));
			}
			con.close();
			pstmt.close();
			rs.close();
		}catch(Exception e) {
			System.err.println("\nRetrieving data failed for email.");
		}
		return userId;
	}
	
	public ArrayList<Object> getUsers() {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			stmt = con.createStatement();
			
			String sql = "SELECT `firstname`, `lastname`, `username`, `email`, `password`, `rights` FROM `users` WHERE `rights` = false AND `active` = true";
			
			ResultSet res = stmt.executeQuery(sql);
//			ResultSet res = stmt.executeQuery("SELECT firstname, lastname, username, email FROM users WHERE active = 1");
			
			ArrayList<Object> arr = new ArrayList<Object>();
			while(res.next()) {
				Map<String, String> obj = new LinkedHashMap<String, String>();
				obj.put("firstname", res.getString("firstname"));
				obj.put("lastname", res.getString("lastname"));
				obj.put("username", res.getString("username"));
				obj.put("email", res.getString("email"));
				obj.put("password", res.getString("password"));
				obj.put("rights", res.getString("rights"));
				arr.add(obj);
			}
			
			return arr;
			
		} catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		
		return null;
	}

}

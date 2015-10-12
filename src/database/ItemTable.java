package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ItemTable {
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private String dbDriver = "com.mysql.jdbc.Driver";
	
	public ItemTable() {
		
	}
	
	public void insertItem(String itemName, String description, String owner) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			con.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			String sql = "INSERT INTO `items`(`active`, `itemname`, `description`, `owner`) "
					+ "VALUES (true, ?, ?, ?);"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, description);
			pstmt.setString(3, owner);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
			System.out.println("Items: Records created successfully");
		}catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " +  e.getMessage() );
		}
	}
	
	public ArrayList<Object> getItems() {
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbu.dburl, dbu.user, dbu.pass);
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM `items`");
			
			ArrayList<Object> arr = new ArrayList<Object>();
			while(res.next()) {
				Map<String, String> obj = new LinkedHashMap<String, String>();
				obj.put("itemname", res.getString("itemname"));
				obj.put("description", res.getString("description"));
				obj.put("owner", res.getString("owner"));
				arr.add(obj);
			}
			
			return arr;
			
		} catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return null;
	}
	
}

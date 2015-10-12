package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import database.DatabaseUtility;
import database.ItemTable;

@WebServlet("/items")
public class ItemCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private ItemTable myItemTable = new ItemTable();
	
	public void init(ServletConfig config) throws ServletException {
		dbu.createItemTable();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
//		for(int i = 0; i < 10; i++) {			
//			myItemTable.insertItem("item name" + (i+1), "item description " + (i+1), "item owner " + (i+1));
//		}
		
		String jsonText = JSONValue.toJSONString(myItemTable.getItems());
		out.print(jsonText);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Add Item Post!");
		String itemname = null;
		String description = null;
		String owner = null;
		StringBuffer sb = new StringBuffer();
		JSONParser parser = new JSONParser();
		JSONObject joUser = new JSONObject();
		
		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			joUser = (JSONObject) parser.parse(sb.toString());
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		itemname = (String) joUser.get("itemname");
		description = (String) joUser.get("description");
		owner = (String) joUser.get("owner");
		
		System.out.println(itemname + " | " + description + " | " + owner);
		myItemTable.insertItem(itemname, description, owner);
	}

}

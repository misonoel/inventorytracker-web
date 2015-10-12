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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import database.DatabaseUtility;
import database.UserTable;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private UserTable myUserTable = new UserTable();
	
	public void init(ServletConfig config) throws ServletException {
		dbu.createDatabase();
		dbu.createUserTable();
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null;
		String password = null;
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
		
		username = (String) joUser.get("username");
		password = (String) joUser.get("password");

		if(myUserTable.confirmLogin(username, password)) {
			joUser.put("__MSGS__", "Success");
		} else {
			joUser.put("__ERROR__", "Error Login");
		}
		
//		joUser.put("__MSGS__", "Success");
//		JsonObject currentRecord = new JsonObject();
//		currentRecord.add("username", new JsonPrimitive(username));
//		currentRecord.add("password", new JsonPrimitive(password));
//		currentRecord.add("__MSGS__", new JsonPrimitive());
		
		PrintWriter out = response.getWriter();
		
		out.print(joUser);
		System.out.println(joUser);
	}
	
	public void destroy() {
		
	}
	
}

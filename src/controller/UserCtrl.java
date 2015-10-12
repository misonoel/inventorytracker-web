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
import database.UserTable;

@WebServlet("/users")
public class UserCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private UserTable myUserTable = new UserTable();

	public void init(ServletConfig config) throws ServletException {
		dbu.createDatabase();
		dbu.createUserTable();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String jsonText = JSONValue.toJSONString(myUserTable.getUsers());
		out.print(jsonText);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Add User Post!");
		String firstname = null;
		String lastname = null;
		String username = null;
		String email = null;
		String password = null;
		String strRights = null;
		boolean rights = false;
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

		firstname = (String) joUser.get("firstname");
		lastname = (String) joUser.get("lastname");
		username = (String) joUser.get("username");
		email = (String) joUser.get("email");
		password = (String) joUser.get("password");
		strRights = (String) joUser.get("rights");
		
		if(strRights.equals("0")) {
			rights = false;
		} else {
			rights = true;
		}
		
		System.out.println(firstname + " | " + lastname + " | " + username + " | " + email + " | " + password + " | '" +  strRights + "' | " +  rights + " | ");
		
		myUserTable.insertUser(firstname, lastname, username, email, password, rights);
	}

}

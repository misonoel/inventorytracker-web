package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtility;
import database.UserTable;

@WebServlet("/main")
public class MainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseUtility dbu = new DatabaseUtility();
	private UserTable myUserTable = new UserTable();
	int counter;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("MainCtrl init().");
		counter = 0;
		dbu.createDatabase();
		dbu.createUserTable();
		dbu.createItemTable();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nMainCtrl doPost | counter: " + ++counter + "\n");
		myUserTable.insertAdmin("Admin-firstname", "Admin-lastname", "admin", "admin@admin.com", "admin1234", true);
//		myUserTable.insertUser("Admin", "Admin", "admin", "admin@admin.com", "admin", 1);
	}

}

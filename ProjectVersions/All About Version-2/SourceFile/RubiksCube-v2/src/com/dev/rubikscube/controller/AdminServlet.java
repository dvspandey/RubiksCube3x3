package com.dev.rubikscube.controller;

import java.io.IOException;
//import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.rubikscube.blogic.AdminService;


 

/**
 * Servlet implementation class AdminServlet
 */
//@WebServlet(urlPatterns = {"/Admin"}, name = "AdminServlet", description = "AdminServlet retuns userData.txt file data in String" )
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		setAccessControlHeaders(response);// For CORS
		// Call AdminService's class methods
		
		// read form fields
		String adminName = request.getParameter("adminName"); 
		String password = request.getParameter("password");
		
//		System.out.println("adminName: " + adminName);
//		System.out.println("password: " + password);
		AdminService adminService = new AdminService();
		
		if(adminService.getAdminCredential(request, response, adminName, password)) {
			//DashBoard
			System.out.println("AdminLogin ["+adminName+"] Unlock Success...");
			adminService.adminDashboard(request, response, adminName, password);
		}else {System.out.println("We Send AdminLock page.");}

	}// end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);// For CORS
		doGet(request, response);
	}
	
	
	//--------------------------- Handle CORS --------------------------------
	//Refer: https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/servlet-cors.html
	//New for Preflight Call
	 @Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }

	  private void setAccessControlHeaders(HttpServletResponse resp) {// For Enable Cross-Origin Resource Sharing [CORS]
//		  resp.setHeader("Access-Control-Allow-Origin", "*");
//		  resp.setHeader("Access-Control-Allow-Methods", "*");
		  
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:2121");
	      resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	      resp.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
	      resp.setHeader("Access-Control-Max-Age", "86400");
	  }
	//--------------------------- Handle CORS --------------------------------

}//end class AdminServlet

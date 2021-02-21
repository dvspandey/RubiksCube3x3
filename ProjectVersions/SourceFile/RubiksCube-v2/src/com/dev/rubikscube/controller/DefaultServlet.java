package com.dev.rubikscube.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultServlet extends HttpServlet {
	/**
	 * @author Devesh Pandey
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Get PrintWriter object from responce object
		PrintWriter pw=res.getWriter();
		//Set response Content Type
		res.setContentType("text/html");  
		  
//		// Redirect to OOPS.html (default page)
//		res.sendRedirect("OOPS.html");
		
		pw.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>OOPS</title>  \r\n" + 
				"    <link rel = \"icon\" href = \"./UI-Dark/img/icon2.png\" type = \"image/x-icon\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"../UI-Dark/styleDark.css\">\r\n" + 
				"</head>\r\n" + 
				"<body style = \"margin:0px; background-color: #06090f;\">\r\n" + 
				"	<div style=\"    margin-bottom: 5rem;\r\n" + 
				"    margin-top: 5rem;\"> \r\n" + 
				"    	<h1 style = \"text-align:center; color:#c1c6ca; \" > OOps ..!  :(  </h1>\r\n" + 
				"    </div>\r\n" + 
				"    <div>\r\n" + 
				"    	<h5 style = \"text-align:center; color:#c1c6ca; \"> <a href=\"./UI-Dark/indexDark.html\" style=\"color:#4e5052; border-style: solid; border-color: #c1c6ca; background-color: #c1c6ca; padding: 5px; border-radius: 2rem; text-decoration:none;\">Back To Home </a></h5>\r\n" + 
				"    </div>\r\n" + 
				"    <div class=\"footer\">\r\n" + 
				"                <h6 style=\"margin-bottom: 0px;\">Cube Solvo | All Rights Reserved 2021&copy; | dvspandey Production</h6>\r\n" + 
				"                <h6 style=\"margin: 0; margin-bottom: 0px;\"><a href=\"https://github.com/dvspandey\" style=\"text-align: center; text-decoration: none; color: rgb(193, 174, 199); margin-top: 0;\">https://github.com/dvspandey</a></h6>\r\n" + 
				"    </div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
		
		
		
		System.out.println("Default Servlet Exe");
		pw.close();  
	}//doGet(-,-);
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-);

}//class

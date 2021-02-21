package com.dev.rubikscube.blogic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.rubikscube.dao.ReadFile;

 

public class AdminService {
	public boolean flag = false;
	public boolean getAdminCredential(HttpServletRequest request, HttpServletResponse response, String adminName, String password) throws ServletException, IOException {
		if(adminName==null||password==null) {
			// get response writer
			PrintWriter printWriter = response.getWriter();
			
			// build HTML code
			String htmlRespone = "<html>"; 
			htmlRespone += "<head>\r\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
					"<title>AdminLock</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div align=\"center\">\r\n" + 
					"	<form name=\"adminAuthentication\" method=\"post\" action=\"Admin\">\r\n" + 
					"	    AdminName: <input type=\"text\" name=\"adminName\"/> <br/>\r\n" + 
					"		PassWord:&nbsp&nbsp&nbsp&nbsp <input type=\"password\" name=\"password\"/> <br/>\r\n" + 
					"		<input type=\"submit\" value=\"UnLock\" />\r\n" + 
					"	</form>\r\n" + 
					"	</div>\r\n" + 
					"</body>";				
			htmlRespone += "</html>";
			
			// return response
			printWriter.println(htmlRespone);
			printWriter.close();
		}else {
			//Chek adminName/pass in file and then if success then redirect to method AdminDashbord
			// else set adminName/pass = null and call method getAdminCredential 
			// Check adminName/pass into file and redirect
			
			
			if(adminName.equalsIgnoreCase("dvspandey10@gmail.com") && password.equals("Sonu@1234")) {
				//AdminDashbord
				flag = true;
			}else {
				adminName = null;
				password = null;
				flag = false;
				getAdminCredential(request, response, adminName, password);
			}
		}
		return flag;
	}// end doGet
	
	
	public void adminDashboard(HttpServletRequest request, HttpServletResponse response, String adminName, String password) throws ServletException, IOException {
		String userData = "";
		try {
//------------------------------------DELETE--and Store another file------------------------------------------------------
			///System.out.println(FileOperations.delete(".//userData.txt", 1));
//------------------------------------UPDATE--------------------------------------------------------				
//			FileOperations fo = new FileOperations("", "23/JAN/2021", "16:01 IST", "dvspandeyPC", "172.1.1.1");
//			FileOperations fo = new FileOperations("Devesh Pandey", "16-OCT-2021", "01:05 IST", "dvspandeyPC", "xxx.xxx.xvx.xxx");
//			System.out.println(fo.update(".//userData.txt", 13));
//			userData= ReadFile.fileExistData(".//userData.txt"); //Display whole data of file
			userData= ReadFile.fileExistData("userData.txt"); //Display whole data of file
			
//------------------------------------Display--------------------------------------------------------
//			userData= ReadFile.fileExistData(".//userData.txt"); //Display whole data of file
//			userData = UserInfoFile.mainHeadder;
//			userData= ReadFile.fileExistData(".//userData.txt", 466+1,582); // Display Specific bytes of data of file
//			userData= userData + ReadFile.fileExistData(".//userData.txt", 7); // Display data row wise
			
			//System.out.println("data feached success..");
		} catch (Exception e) {
			// TODO: handle exception
			userData = "dvs-01:exception Occur dvspandey: "+e.fillInStackTrace();
		}
		//Gson gson = new Gson();
		//String userDataJSON = gson.toJson(UserInfoFile.fileExistData(".//userData.txt"));
		
//		PrintWriter printWriter = response.getWriter();
//		//response.setContentType("application/json");
//		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
//		printWriter.write(userData);
//		printWriter.close();
		
		PrintWriter printWriter = response.getWriter();
		// build HTML code
		String htmlRespone = "<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
				"<title>AdminLock</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h1 style = \"color:red;\" align=\"center\">AdminLock</h1>\r\n" + 
				"	<h4 align=\"center\">Welcome Admin <h2 style = \"color:Purple;\" align=\"center\"><u>"+adminName+"</u></h2> </h4>\r\n" + 
				"	<div align=\"center\">\r\n" + 
				"		<pre>\r\n" + 
							userData + 
				"		</pre>\r\n" + 
				"	</div>\r\n" + 
				"</body>";			
		htmlRespone += "</html>";
		
		// return response
		printWriter.println(htmlRespone);
		printWriter.close();
		
	}// end adminDashboard
	
	
}


/*
 * 		String userData = "";
		try {
//------------------------------------DELETE--and Store another file------------------------------------------------------
			///System.out.println(FileOperations.delete(".//userData.txt", 1));
//------------------------------------UPDATE--------------------------------------------------------				
//			FileOperations fo = new FileOperations("", "23/JAN/2021", "16:01 IST", "dvspandeyPC", "172.1.1.1");
//			FileOperations fo = new FileOperations("Devesh Pandey", "16-OCT-2021", "01:05 IST", "dvspandeyPC", "xxx.xxx.xvx.xxx");
//			System.out.println(fo.update(".//userData.txt", 13));
			userData= ReadFile.fileExistData(".//userData.txt"); //Display whole data of file
			
//------------------------------------Display--------------------------------------------------------
//			userData= ReadFile.fileExistData(".//userData.txt"); //Display whole data of file
//			userData = UserInfoFile.mainHeadder;
//			userData= ReadFile.fileExistData(".//userData.txt", 466+1,582); // Display Specific bytes of data of file
//			userData= userData + ReadFile.fileExistData(".//userData.txt", 7); // Display data row wise
			
			//System.out.println("data feached success..");
		} catch (Exception e) {
			// TODO: handle exception
			userData = "dvs-01:exception Occur dvspandey: "+e.fillInStackTrace();
		}
		//Gson gson = new Gson();
		//String userDataJSON = gson.toJson(UserInfoFile.fileExistData(".//userData.txt"));
		
		PrintWriter printWriter = response.getWriter();
//		response.setHeader("Access-Control-Allow-Origin", "*");//new
//        response.setHeader("Access-Control-Allow-Methods", "POST");//new
		//response.setContentType("application/json");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		printWriter.write(userData);
		printWriter.close();
	
		*
		*
		*
		*
		*
		*
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminLock</title>
</head>
<body>
	<h1 style = "color:red;" align="center">AdminLock</h1>
	<h4 align="center">Welcome Admin <h3 style = "color:blue;" >Name</h3> </h4>
	<div align="center">
		<pre>
			
		</pre>
	</div>
</body>
		*
		*
		*
		*
		*
		*
		*
		*
		*
		*
		*/

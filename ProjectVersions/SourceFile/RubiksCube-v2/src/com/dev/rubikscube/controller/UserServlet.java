package com.dev.rubikscube.controller;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.rubikscube.bean.DataJB;
import com.dev.rubikscube.blogic.MainBlogic;
import com.dev.rubikscube.blogic.UserService;
import com.google.gson.Gson; 
 
/**
 * Servlet implementation class UserServlet
 */
//@CrossOrigin(origins = "http://localhost:8080") //for Spring applications 
//@WebServlet(urlPatterns = {"/steps"}, name = "UserServlet", description = "UserServlet retuns Json Steps" )
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	private DataJB dataJB = null;
	private static String defaultData = "{\"cube\":[[[\"Y\",\"Y\",\"Y\"],[\"Y\",\"Y\",\"Y\"],[\"Y\",\"Y\",\"Y\"]],[[\"B\",\"B\",\"B\"],[\"B\",\"B\",\"B\"],[\"B\",\"B\",\"B\"]],[[\"R\",\"R\",\"R\"],[\"R\",\"R\",\"R\"],[\"R\",\"R\",\"R\"]],[[\"G\",\"G\",\"G\"],[\"G\",\"G\",\"G\"],[\"G\",\"G\",\"G\"]],[[\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\"],[\"O\",\"O\",\"O\"]],[[\"W\",\"W\",\"W\"],[\"W\",\"W\",\"W\"],[\"W\",\"W\",\"W\"]]],\"rotations\":\"Default data dvspandey..\",\"obj\":{\"name\":\"DemoData DemoData\",\"date\":null,\"timeStamp\":null,\"pcName\":null,\"ipAddress\":null}}";
	//    private static String defaultData = "{ 'cube':"
	//    		+ "[[['Y','Y','Y'],['Y','Y','Y'],['Y','Y','Y']],"
	//    		+ "[['B','B','B'],['B','B','B'],['B','B','B']],"
	//    		+ "[['R','R','R'],['R','R','R'],['R','R','R']],"
	//    		+ "[['G','G','G'],['G','G','G'],['G','G','G']],"
	//    		+ "[['O','O','O'],['O','O','O'],['O','O','O']],"
	//    		+ "[['W','W','W'],['W','W','W'],['W','W','W']]],"
	//    		+ " 'rotations':'Default data dvspandey..'}";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//----------------------------------------------------------------------------------		
		setAccessControlHeaders(response);// For CORS
		// #2 Send Response [Convert Java Object to JSON object]
		if(dataJB==null) {
			System.out.println("[UI data]dataJB==Null");
			Gson gson = new Gson();
			dataJB =  gson.fromJson(defaultData, DataJB.class);
		} 
		List<MainBlogic> steps = new ArrayList<>();
		try {
			steps = service.getSteps(dataJB);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("dvs-02:Exception in doGet-UserServlet[DataJB]: "+e);
			//here we can USe default data and OverCome the problem UI "Lorem ipsum dolor sit."
			// using defaultData and convert into DataJB object and pass  service.getSteps(); But Now i feel it is not good for now. 
		}catch(Exception e) {
			System.out.println("dvs-02:Exception in doGet-UserServlet: "+e);
		}

		Gson gson = new Gson();
		String stepJSON = gson.toJson(steps);

		PrintWriter printWriter = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		printWriter.write(stepJSON);
		dataJB = null;
		printWriter.close(); 
	}//End doGet method

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		BufferedReader json =  request.getReader();  //OLD	#1


		//		Gson gson = new Gson();        //OLD #2
		//		dataJB =  gson.fromJson(request.getReader(), DataJB.class); //OLD #2
		//----------------------------------------------------------------------------------		
		setAccessControlHeaders(response);// For CORS
		// #1 Take Request data into String variable and convert JSON object to Java Object
		String json =  getBody(request);
		//System.out.println(json.toString()); 
		Gson gson = new Gson();
		//System.out.println("UI String:"+json);
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		Calendar c = Calendar.getInstance(timeZone); 
		try {
			dataJB =  gson.fromJson(json, DataJB.class);
			
			// Set Server Date and Time to user InFo		 
			
			dataJB.getObj().setDate(""+c.get(Calendar.DATE)+"/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR));
			dataJB.getObj().setTimeStamp(c.get(Calendar.HOUR_OF_DAY)+ ":" +c.get(Calendar.MINUTE) +":"+c.get(Calendar.SECOND) + " [" + timeZone.getID()+"]");
			System.out.println("Data arrival :: "+dataJB.getObj().toString());
		} catch (Exception e) {
			System.out.println("dvs-01:Exception in doPost-UserServlet[JSON format error]: "+e);
		}

		// for checking userInfo comes or innerClass of DataJB is empty 
		if(dataJB!=null) {
			if(dataJB.getObj()==null) {
				System.out.println("Devesh User Information is : [NULL] so, i'm setting default values");
				DataJB.Inner defaultINFO = dataJB.new Inner();
				dataJB.setObj(defaultINFO);
			}
		}
		//System.out.println("UI Seeker name:"+dataJB.getRotations());

		doGet(request, response);
	}// end doPost method


	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				//stringBuilder.append("");
				System.out.println("MyString: "+defaultData);
				stringBuilder.append(defaultData);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}//end getBody method

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
 
		resp.setHeader("Access-Control-Allow-Origin", "*");//http://localhost:2121,http://127.0.0.1:5500
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		resp.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
		resp.setHeader("Access-Control-Max-Age", "86400");
	}
	//--------------------------- Handle CORS --------------------------------

}//end class

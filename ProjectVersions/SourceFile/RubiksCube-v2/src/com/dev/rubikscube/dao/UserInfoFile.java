/* This class method take File name and Data in sting format and store that data into File.
 * with given specific format/pattern Row-wise and columns-wise.
 * 
			// Header size = 115x3 + 5(end line character "\n") bytes
			// dataString size = 115 + 1(using endLine character "\n") bytes

			name = 30 byte.
			date = 15 byte.
			timeStamp = 15 byte.
			pcName = 20 byte.
			ipAddress = 20 byte.
			size-was = 15 byte including(1byte(of tab space char "\t"))
			"\n"= 1 bytes.   so Total Row size = 30+15+15+20+20+15+1 = 116

			each Row size = 30 + 15 + 15 + 20 + 20 + 15 + 1
						  = 116 bytes.
 */
package com.dev.rubikscube.dao;

import java.io.*;

import org.apache.commons.lang3.StringUtils;//For center padding

import com.dev.rubikscube.bean.DataJB;




public class UserInfoFile {
	static boolean flag = true;
	public static String mainHeadder =  leftPadding("[Pandey's Productions...]", ' ', 115)+"\n\n"
			+ centerPadding("NAME", ' ', 30) + centerPadding("DATE", ' ', 15) + centerPadding("TimeStamp", ' ', 15)+ centerPadding("Machine", ' ', 20) + centerPadding("IP Address", ' ', 20) + centerPadding("Size-Was", ' ', 15)+"\n"
			+ centerPadding("", '=', 115)+"\n\n";
			
	public static void main(DataJB.Inner obj){
		//DataJB.Inner obj = dataJB.new Inner();  //Inner/nested class object

		String name = obj.getName();// = "Akshay";
		String date = obj.getDate();// = "19-JAN-2021";
		String timeStamp = obj.getTimeStamp();// = "7:53";
		String pcName  = obj.getPcName();// = "AkshayPC";
		String ipAddress  = obj.getIpAddress();// = "172.1.1.1";

		if(name==null){
			name = "xxxxxx";
	 	}
		if(date==null){
			date = "xx-xxx-xxxx";
		}
		if(timeStamp==null){
			timeStamp = "xx:xx";
		}
		if(pcName==null){
			pcName = "xxxxxxxx";
		}
		if(ipAddress==null){
			ipAddress = "xxx.xxx.xxx.xxx";
		}


		try{
//			String userData = ".//userData.txt";//fileName
			String userData = "userData.txt";//fileName
			name = centerPadding(name, ' ', 30);
			date = centerPadding(date, ' ', 15);
			timeStamp = centerPadding(timeStamp, ' ', 15);
			pcName = centerPadding(pcName, ' ', 20);
			ipAddress = centerPadding(ipAddress, ' ', 20);
			String size = centerPadding((((int)(fileSize(userData))/1000<=0) ? (fileSize(userData)+"\tbytes"):(fileSize(userData)/1000+"\tKb")), ' ', 15);
			//Writting
			String dataString = name+date+timeStamp+pcName+ipAddress+size;
			notePad(userData,dataString);//notePad(filename,dataString); ---> find file and store dataString and then close file.
		}
		catch(FileNotFoundException foe){
			System.out.println("FileNotFoundException:"+foe);
		}catch(IOException ioe){
			System.out.println("IOException:"+ioe);
		}catch(Exception e){
			System.out.println("Exception:"+e);
		}
	}

	private static void notePad(String fileName, String dataString)throws FileNotFoundException, IOException{
		FileOutputStream fos;
		if(fileSize(fileName)!=0){
			System.out.println("File Exist with name: \""+fileName+"\"\t[SIZE: "+(((int)(fileSize(fileName))/1000<=0) ? (fileSize(fileName)+" bytes]"):(fileSize(fileName)/1000+" Kb]"))); //Here i use Ternary Operator if we reached >= 1kb  then we print Size in kb else bytes
			//fileExistData(fileName);
			fos = new FileOutputStream(fileName,true);
		}else{
			fos = new FileOutputStream(fileName);
//			String header = leftPadding("[Pandey's Productions...]", ' ', 115)+"\n\n";
//			header = header + centerPadding("NAME", ' ', 30) + centerPadding("DATE", ' ', 15) + centerPadding("TimeStamp", ' ', 15)+ centerPadding("Machine", ' ', 20) + centerPadding("IP Address", ' ', 20) + centerPadding("Size-Was", ' ', 15)+"\n";
//			header = header + centerPadding("", '=', 115);
			dataString = mainHeadder +dataString; 

			// Header size = 115x3 + 5(end line character "\n") bytes
			// dataString size = 115.

			System.out.println("Truncated File use Successfully.....! ");
		}


		String str = dataString;
		char[] ch = new char[str.length()+1];
		str.getChars(0, str.length(), ch, 0);
		ch[str.length()] = '\n'; // adding end line character(Enter) at end of each row [1 Byte]
		int chLen = ch.length;
		for(int i=0;i<chLen;i++){
			fos.write(ch[i]);
		}	

		fos.close();
		System.out.format("Current size: %.2f bytes\n",fileSize(fileName)); //File Size
	}


	private static double fileSize(String fileName){ 
		//String fileName = "abc.txt";     
		//File f = new File(fileName);
		long fileSize=0;// = f.length();
		//System.out.format("The size of the file: %d bytes\n", fileSize);
		// try-catch block to handle exceptions 
		try { 

			// Create a file object 
			File f = new File(fileName); 
			fileSize = f.length();
			// Get the absolute path of file f 
			String absolute = f.getAbsolutePath(); 

			// Display the file path of the file object 
			// and also the file path of absolute file 
			//          System.out.println("Original  path: " + f.getPath()); 
			if(flag==true) {
				System.out.println("Absolute  path: " + absolute);
				flag = false;
			} 
		} 
		catch (Exception e) { 
			System.err.println(e.getMessage()); 
		} 
		return fileSize;
	}//end fileSize


	//*************************************** Padding *****************************************************
	// Function to perform center padding 
	public static String centerPadding(String input, char ch, int L){ 
		// Center pad the string 
		String result = StringUtils.center(input, L, ch); 

		// Return the resultant string 
		return result; 
	} 

	// Function to perform right padding 
	public static String rightPadding(String input, char ch, int L){ 

		// Right pad the string 
		String result = StringUtils.rightPad(input, L, ch); 

		// Return the resultant string 
		return result; 
	}

	// Function to perform left padding 
	public static String leftPadding(String input, char ch, int L) 
	{ 

		// Left pad the string 
		String result = StringUtils.leftPad(input, L, ch); 

		// Return the resultant string 
		return result; 
	} 

	//*************************************** Padding *****************************************************


	//*************************** public static void main(String[] args) Method ***************************
	/*
	public static void main(String[] args){
		String name = null;// = "Akshay";
		String date = null;// = "19-JAN-2021";
		String timeStamp = null;// = "7:53";
		String pcName  = null;// = "AkshayPC";
		String ipAddress  = null;// = "172.1.1.1";

		if(name==null){
			name = "xxxxxx";
		}
		if(date==null){
			date = "xx-xxx-xxxx";
		}
		if(timeStamp==null){
			timeStamp = "xx:xx";
		}
		if(pcName==null){
			pcName = "xxxxxxxx";
		}
		if(ipAddress==null){
			ipAddress = "xxx.xxx.xxx.xxx";
		}


		try{
			//Writting
			String userData = "userData";//fileName
			String dataString = name+"\t"+date+"\t"+timeStamp+"\t"+pcName+"\t"+ipAddress+"\t"
				+(((int)(fileSize(userData+".txt"))/1000<=0) ? (fileSize(userData+".txt")+"\tbytes"):(fileSize(userData+".txt")/1000+"\tKb"));//actualData
			notePad(userData,dataString);//notePad(filename,dataString); ---> find file and store dataString and then close file.

			//Reading
			fileExistData(userData+".txt");
			System.out.format("\nCurrent size: "+fileSize(userData+".txt")/1000+" kb\n"); //File Size
		}
		catch(FileNotFoundException foe){
			System.out.println("FileNotFoundException:"+foe);
		}catch(IOException ioe){
			System.out.println("IOException:"+ioe);
		}catch(Exception e){
			System.out.println("Exception:"+e);
		}

	}
	 */


}// end UserInfoFile class
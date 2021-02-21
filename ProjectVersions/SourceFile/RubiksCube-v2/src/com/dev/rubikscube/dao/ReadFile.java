package com.dev.rubikscube.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {
  
	//********************************** Reading Data from file *******************************************
	//Fetching/[Reading] All file data
	public static String fileExistData(String fileName)throws FileNotFoundException, IOException{ 
		FileInputStream fis = new FileInputStream(fileName);
		String str = ""; 
		int data;
		while((data=fis.read()) != -1){
			//System.out.print((char)data);  
			str = str + (char)data;
		}
		fis.close();
		return str;
	}// end fileExistData

	//Fetching [Reading] file data specific position/ bytes 
	public static String fileExistData(String fileName ,int startByte , int endByte)throws FileNotFoundException, IOException{ 

		FileInputStream fis = new FileInputStream(fileName);
		int fileLen = fis.available();
		if(endByte>fileLen){
			endByte = fileLen;
		}else if(startByte<=0){
			startByte = 1;
		}else if(startByte>fileLen){
			startByte = fileLen;
		}
		byte[] byteArray = new byte[fileLen];
		int  x = fis.read(byteArray);
		System.out.println("size of file : "+x+"\tbytes");
		String str = "";
		for(int i=startByte; i<=endByte; i++){
			str = str + (char)byteArray[i];
		}
		fis.close();
		return str;
	}// end fileExistData

	//----------------------------------Fetch Row if exist ------------------------------------------------

	//Fetching [Reading] file data specific position 
	public static String fileExistData(String fileName , int row)throws FileNotFoundException, IOException{ 
		int startByte = (350)+116*(row-1);
		int endByte = startByte+116;
		String str = fileExistData(fileName, startByte, endByte);
		return str;
	}// end fileExistData

	//**--------------------------------------------------------------------------------------------------
	//********************************** Reading Data from file *******************************************
	
	
}

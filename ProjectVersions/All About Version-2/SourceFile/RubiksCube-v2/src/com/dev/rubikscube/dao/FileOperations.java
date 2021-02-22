/*
 * This class contains different methods of Operations into a file 
 * Updation
 * Deletion
 * 
 * Of particular data element of Rows/Columns  
 * */
 
package com.dev.rubikscube.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperations {
	private String name = "";
	private String date = "";
	private String timeStamp = "";
	private String machine = "";
	private String iPAddress = "";
	private String sizeWas = "";   



	public FileOperations(String name, String date, String timeStamp, String machine, String iPAddress) {
		super();
		if(name.equals("")==false)
			this.name = UserInfoFile.centerPadding(name, ' ', 30);
		if(date.equals("")==false)
			this.date = UserInfoFile.centerPadding(date, ' ', 15);
		if(timeStamp.equals("")==false)
			this.timeStamp = UserInfoFile.centerPadding(timeStamp, ' ', 15);
		if(machine.equals("")==false)
			this.machine = UserInfoFile.centerPadding(machine, ' ', 20);
		if(iPAddress.equals("")==false)
			this.iPAddress = UserInfoFile.centerPadding(iPAddress, ' ', 20);

		this.sizeWas = UserInfoFile.centerPadding("RowIsUpdated", ' ', 15);
	}

	//**--------------------------------------------------------------------------------------------------
	//----------------------------------Update Row if exist ----------------------------------------------	
	//update file data of specific row  
	public String update(String fileName ,int row)throws FileNotFoundException, IOException{ 

		int startByte = (350)+116*(row-1); //we should take (350+1) starting index But here we use ByteArray which index not start with 1 but it is start from 0 so whole calculation we should take with (n-1) then we get correct output
		int endByte = startByte+116;

		FileInputStream fis = new FileInputStream(fileName);
		int fileLen = fis.available();
		if(endByte>fileLen){
			//endByte = fileLen;
			fis.close();
			return "NotFound Row: "+row;
		}else if(startByte<=0){
			//startByte = 1;
			fis.close();
			return "NotFound Row: "+row;
		}else if(startByte>fileLen){
			//startByte = fileLen;
			fis.close();
			return "NotFound Row: "+row;
		}

		//		name = 30 byte.
		//		date = 15 byte.
		//		timeStamp = 15 byte.
		//		pcName = 20 byte.
		//		ipAddress = 20 byte.
		//		size-was = 15 byte including(1byte(of tab space char "\t"))
		//		"\n"= 1 bytes.   so Total Row size = 30+15+15+20+20+15+1 = 116

		byte[] byteArray = new byte[fileLen];
		int  x = fis.read(byteArray);
		fis.close();
		System.out.println("size of file : "+x+"\tbytes");

		//--------------------------------------------------------
		int i=0;
		if(name.equals("")==false) {
			int start 	= startByte;
			int end 	= startByte+30;
			byte[] temp = name.getBytes();
			int j = 0;
			for(i=start; i<end; i++){
				byteArray[i] = temp[j++];
			}
		}
		if(date.equals("")==false) {
			int start 	= startByte+30;
			int end 	= startByte+30+15;
			byte[] temp = date.getBytes();
			int j = 0;
			for(i=start; i<end; i++){
				byteArray[i] = temp[j++];
			}
		}
		if(timeStamp.equals("")==false) {
			int start 	= startByte+30+15;
			int end 	= startByte+30+15+15;
			int j = 0;
			byte[] temp = timeStamp.getBytes();
			for(i=start; i<end; i++){
				byteArray[i] = temp[j++];
			}
		}
		if(machine.equals("")==false) {
			int start 	= startByte+30+15+15;
			int end 	= startByte+30+15+15+20;
			byte[] temp = machine.getBytes();
			int j = 0;
			for(i=start; i<end; i++){
				byteArray[i] = temp[j++];
			}
		}
		if(iPAddress.equals("")==false) {
			int start 	= startByte+30+15+15+20;
			int end 	= startByte+30+15+15+20+20;
			byte[] temp = iPAddress.getBytes();
			int j = 0;
			for(i=start; i<end; i++){
				byteArray[i] = temp[j++];
			}
		}
		if(sizeWas.equals("")==false) {
			int start 	= startByte+30+15+15+20+20;
			int end 	= startByte+30+15+15+20+20+15;
			byte[] temp = sizeWas.getBytes();
			int j = 0;
			for(i=start; i<end-1; i++){// (end-1)index element in byteArray is "\n" endLine char so we keep it as it is
				byteArray[i] = temp[j++];
			}
		}
		//--------------------------------------------------------

		String updatedRow = "UpdatedRow: ";
		for(i=startByte; i<endByte; i++){
			updatedRow = updatedRow + (char)byteArray[i];
		}

		//-------------------Send byte[] data into file-------------
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			// Writes bytes from the specified byte array to this file output stream 
			fos.write(byteArray);
		}
		catch (FileNotFoundException e) {
			System.out.println("dvs-01:FileOperations.update File not found" + e);
		}
		catch (IOException ioe) {
			System.out.println("dvs-02:FileOperations.update Exception while writing file " + ioe);
		}
		finally {
			// close the streams using close method
			try {
				if (fos != null) {
					fos.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("dvs-03:FileOperations.update Error while closing stream: " + ioe);
			}

		}
		//-------------------Send byte[] data into file-------------


		return updatedRow;
	}// end update
	//***************************************************************************************************
	// This method is delete a given Row and also Save that  deleted row into another File 
	public static String delete(String fileName ,int row)throws FileNotFoundException, IOException{
		int startByte = (350)+116*(row-1);
		int endByte = startByte+116;

		FileInputStream fis = new FileInputStream(fileName);
		int fileLen = fis.available();
		if(endByte>fileLen){
			fis.close();
			return "NotFound Row: "+row;
		}else if(startByte<=0){
			fis.close();
			return "NotFound Row: "+row;
		}else if(startByte>fileLen){
			fis.close();
			return "NotFound Row: "+row;
		}

		byte[] byteArray = new byte[fileLen];
		byte[] newByteArray = new byte[fileLen-116];
		byte[] deletdRow = new byte[116];
		int  x = fis.read(byteArray);
		fis.close();
		System.out.println("size of file : "+x+"\tbytes");

		int i=0, j=0, z=0;
		String str = "Row: "+row+"> ";
		for(i=0; i<byteArray.length; i++) {
			if(i<startByte||i>endByte-1) {
				newByteArray[j++] = byteArray[i];
			}else {
				deletdRow[z++] = byteArray[i];
				str = str + (char)byteArray[i];
			}
		}
		str = str + " [deleated!]";
		
		
		
		//-------------------Send byte[] data into file-------------
		FileOutputStream fos = null;
		FileOutputStream fosDeletedRow = null;
		try {
			fos = new FileOutputStream(fileName);
//			fosDeletedRow = new FileOutputStream(".//userDeletedData.txt",true);
			fosDeletedRow = new FileOutputStream("userDeletedData.txt",true);
			// Writes bytes from the specified byte array to this file output stream 
			fos.write(newByteArray);
			fosDeletedRow.write(deletdRow);
		}
		catch (FileNotFoundException e) {
			System.out.println("dvs-01:FileOperations.delete File not found" + e);
		}
		catch (IOException ioe) {
			System.out.println("dvs-02:FileOperations.delete Exception while writing file " + ioe);
		}
		finally {
			// close the streams using close method
			try {
				if (fos != null) {
					fos.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("dvs-03:FileOperations.delete Error while closing stream: " + ioe);
			}

		}
		//-------------------Send byte[] data into file-------------


		return str;
	}// end delete


}//class FileOperations

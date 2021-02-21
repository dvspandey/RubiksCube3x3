//***************************** Flip Logics **************************
		//		 R		L		U	  F 	   D	  B 
		//		 R'		L'		U'	  F'	   D'	  B'



//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

public class BackFlip { 

	// Back Flip

	public static char[][][] flip(char cube[][][]){

		// U {  // flip Back face Clock-Wise(NOT column) [z][x][y]

		//	 Y <-- R        [0]		  =		 [2]	-------> step1
		//				{000,001,002} = {202,212,222}  

		//	 O <-- Y        [4]		  =		 [0]	-------> step4
		//				{400,410,420} = {002,001,000}  

		//	 W <-- O        [5]		  =		 [4]	-------> step3
		//				{500,501,502} = {420,410,400}  

		//	 R <-- W        [2]		  =		 [5]	-------> step2
		//				{202,212,222} = {500,501,502}  		

		// }
		//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];

		char tempArr[] = new char[3];  // for copying column	
		// [0]	= [2]

		tempArr[0] = cube[0][0][0]; 																					
		tempArr[1] = cube[0][0][1]; 																					
		tempArr[2] = cube[0][0][2]; 																					

		cube[0][0][0] = cube[2][0][2]; 
		cube[0][0][1] = cube[2][1][2]; 																					
		cube[0][0][2] = cube[2][2][2]; 																					


		//##########################################################							   
		// [2]  = [5]																		   
		cube[2][0][2] = cube[5][0][0]; 
		cube[2][1][2] = cube[5][0][1]; 																					
		cube[2][2][2] = cube[5][0][2]; 

		//##########################################################							   
		// [5]  = [4]																		   
		cube[5][0][0] = cube[4][2][0];
		cube[5][0][1] = cube[4][1][0];
		cube[5][0][2] = cube[4][0][0];

		//##########################################################
		// [4]  = [0]
		cube[4][0][0] = tempArr[2];
		cube[4][1][0] = tempArr[1];
		cube[4][2][0] = tempArr[0];


		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[3] = FaceRotor90.faceRotate90(cube[3]);  // Back

		return cube;

	}// flip method close



	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	// Reverse Back Flip



	public static char[][][] flipCounter(char cube[][][]){

		// U {  // flip Back face AntiClock-Wise(NOT column) [z][x][y]				  

		//	 R <-- Y        [2]		  =		 [0]									  
		//				{202,212,222} =  {000,001,002}								  

		//	 Y <-- O        [0]		  =		 [4]									  
		//				{002,001,000}  =  {400,410,420}								  

		//	 O <-- W        [4]		  =		 [5] 									  
		//				{420,410,400} =  {500,501,502}								  

		//	 W <-- R        [5]		  =		 [2]
		//				{500,501,502} =  {202,212,222}		

		// }
		//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];

		char tempArr[] = new char[3];  // for copying column	
		// [2]	= [0]

		tempArr[0] = cube[2][0][2]; 																					
		tempArr[1] = cube[2][1][2]; 																					
		tempArr[2] = cube[2][2][2]; 																					

		cube[2][0][2] = cube[0][0][0]; 
		cube[2][1][2] = cube[0][0][1]; 																					
		cube[2][2][2] = cube[0][0][2]; 																					

		//##########################################################
		// [0]  = [4]
		cube[0][0][2] = cube[4][0][0];
		cube[0][0][1] = cube[4][1][0];
		cube[0][0][0] = cube[4][2][0];

		//##########################################################							   
		// [4]  = [5]																		   
		cube[4][2][0] = cube[5][0][0];
		cube[4][1][0] = cube[5][0][1];
		cube[4][0][0] = cube[5][0][2];

		//##########################################################							   
		// [5]  = [2]																		   
		cube[5][0][0] = tempArr[0]; 
		cube[5][0][1] = tempArr[1]; 																					
		cube[5][0][2] = tempArr[2]; 

		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[3] = FaceRotor90.faceRotateCounter90(cube[3]);   // Back

		return cube;

	}// flipCounter method close



}// end class UpFlip

//***************************** Flip Logics **************************
//		 R		L		U	  F 	   D	  B 
//		 R'		L'		U'	  F'	   D'	  B'


 

//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

public class LeftFlip {

	// Left Flip


	public static char[][][] flip(char cube[][][]){
		//OLD																		//NEW
		// L {  // flip all left column Clock-Wise [z][x][0]						// L {  // flip all left column Clock-Wise [z][x][0]         

		//	 W <-- B        [5]		  =		 [1]   --------> step1					//	 W <-- B        [5]		  =		 [1]   --------> step1   
		//				{500,510,520} = {100,110,120}								//				{502,512,522} = {120,110,100}                

		//	 G <-- W        [3]		  =		 [5]   --------> step4					//	 G <-- W        [3]		  =		 [5]   --------> step4   
		//				{300,310,320} = {500,510,120} 								//				{302,312,322} = {502,512,122}                

		//	 Y <-- G        [0]		  =		 [3]   --------> step3 					//	 Y <-- G        [0]		  =		 [3]   --------> step3   
		//				{000,010,020} = {300,310,320}								//				{000,010,020} = {322,312,302}                

		//	 B <-- Y        [1]		  =		 [0]   --------> step2					//	 B <-- Y        [1]		  =		 [0]   --------> step2   
		//				{100,110,120} = {000,010,020}								//				{100,110,120} = {000,010,020}                

		// }																		// }                                                         
		//******************************* R *************************************
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];

		char tempArr[] = new char[3];  // for copying column 
		tempArr[0] = cube[5][0][2]; 
		tempArr[1] = cube[5][1][2]; 
		tempArr[2] = cube[5][2][2]; 

		// [5]  =  [1]
		cube[5][0][2] = cube[1][2][0]; 
		cube[5][1][2] = cube[1][1][0]; 
		cube[5][2][2] = cube[1][0][0]; 

		//##########################################################
		// [1]  =  [0]
		cube[1][0][0] = cube[0][0][0]; 
		cube[1][1][0] = cube[0][1][0]; 
		cube[1][2][0] = cube[0][2][0]; 

		//##########################################################
		// [0]  =  [3]
		cube[0][0][0] = cube[3][2][2]; 
		cube[0][1][0] = cube[3][1][2]; 
		cube[0][2][0] = cube[3][0][2]; 

		//##########################################################
		// [3]  =  [5]
		cube[3][0][2] = tempArr[0]; 
		cube[3][1][2] = tempArr[1]; 
		cube[3][2][2] = tempArr[2]; 


		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[4] = FaceRotor90.faceRotate90(cube[4]);
		return cube;

	}// flip method close

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	// Reverse Left flip 

	public static char[][][] flipCounter(char cube[][][]){
		//OLD																				//NEW
		// L' {  // flip all Left column antiClock-Wise [z][x][0]							// L' {  // flip all Left column antiClock-Wise [z][x][0]	

		//	 B <-- W        [1]		  =		 [5]											//	 B <-- W        [1]		  =		 [5]                 
		//				{100,110,120} =  {500,510,520}										//				{100,110,120} =  {522,512,502}     

		//	 W <-- G        [5]		  =		 [3]											//	 W <-- G	    [5]		  =		 [3]                 
		//				{500,510,120} =  {300,310,320}										//				{502,512,522} =  {302,312,322}           

		//	 G <-- Y        [3]		  =		 [0] 											//	 G <-- Y        [3]		  =		 [0]                 
		//				{300,310,320} =  {000,010,020}										//				{302,312,322} =  {020,010,000}           

		//	 Y <-- B        [0]		  =		 [1]											//	 Y <-- B        [0]		  =		 [1]                 
		//				{000,010,020} =  {100,110,120}										//				{000,010,020} =  {100,110,120}		     

		// }
		//******************************* R *************************************
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];
		// [1]  =  [5]
		char tempArr[] = new char[3];  // for copying column 
		tempArr[0] = cube[1][0][0]; 
		tempArr[1] = cube[1][1][0]; 
		tempArr[2] = cube[1][2][0]; 

		cube[1][0][0] = cube[5][2][2]; 
		cube[1][1][0] = cube[5][1][2]; 
		cube[1][2][0] = cube[5][0][2]; 

		//##########################################################
		// [5]  =  [3]
		cube[5][0][2] = cube[3][0][2]; 
		cube[5][1][2] = cube[3][1][2]; 
		cube[5][2][2] = cube[3][2][2]; 

		//##########################################################
		// [3]  =  [0]
		cube[3][0][2] = cube[0][2][0]; 
		cube[3][1][2] = cube[0][1][0]; 
		cube[3][2][2] = cube[0][0][0]; 

		//##########################################################
		// [0]  =  [1]
		cube[0][0][0] = tempArr[0]; 
		cube[0][1][0] = tempArr[1]; 
		cube[0][2][0] = tempArr[2]; 

		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[4] = FaceRotor90.faceRotateCounter90(cube[4]);

		return cube;

	}// flipCounter method close



}// end class LeftFlip

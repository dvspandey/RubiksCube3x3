//***************************** Flip Logics **************************
		//		 R		L		U	  F 	   D	  B 
		//		 R'		L'		U'	  F'	   D'	  B'




//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

public class RightFlip {

	// Right Flip 

	public static char[][][] flip(char cube[][][]){
		//OLD																		//NEW		
		// R {  // flip all right column Clock-Wise [z][x][2]						// R {  // flip all right column Clock-Wise [z][x][2,0]  

		//	 B <-- W        [1]		  =		 [5]									//	 B <-- W        [1]		  =		 [5]                 
		//				{102,112,122} =  {502,512,522}								//				{102,112,122} =  {520,510,500}           

		//	 W <-- G        [5]		  =		 [3]									//	 W <-- G        [5]		  =		 [3]                 
		//				{502,512,122} =  {302,312,322}								//				{500,510,120} =  {300,310,320}           

		//	 G <-- Y        [3]		  =		 [0] 									//	 G <-- Y        [3]		  =		 [0]                 
		//				{302,312,322} =  {002,012,022}								//				{300,310,320} =  {022,012,002}         

		//	 Y <-- B        [0]		  =		 [1]									//	 Y <-- B        [0]		  =		 [1]                 
		//				{002,012,022} =  {102,112,122}								//				{002,012,022} =  {102,112,122}		     

		// }																		// }                                                     
		//******************************* R *************************************
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];
		// [1]  =  [5]
		char tempArr[] = new char[3];  // for copying column 
		tempArr[0] = cube[1][0][2]; 
		tempArr[1] = cube[1][1][2]; 
		tempArr[2] = cube[1][2][2]; 

		cube[1][0][2] = cube[5][2][0]; 
		cube[1][1][2] = cube[5][1][0]; 
		cube[1][2][2] = cube[5][0][0]; 

		//##########################################################
		// [5]  =  [3]
		cube[5][0][0] = cube[3][0][0]; 
		cube[5][1][0] = cube[3][1][0]; 
		cube[5][2][0] = cube[3][2][0]; 

		//##########################################################
		// [3]  =  [0]
		cube[3][0][0] = cube[0][2][2]; 
		cube[3][1][0] = cube[0][1][2]; 
		cube[3][2][0] = cube[0][0][2]; 

		//##########################################################
		// [0]  =  [1]
		cube[0][0][2] = tempArr[0]; 
		cube[0][1][2] = tempArr[1]; 
		cube[0][2][2] = tempArr[2]; 

		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[2] = FaceRotor90.faceRotate90(cube[2]); // right

		return cube;

	}// flip method close

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	// Reverse Right Flip


	public static char[][][] flipCounter(char cube[][][]){
		//OLD																		//NEW		
		// R' {  // flip all right column antiClock-Wise [z][x][2]							// R' {  // flip all right column antiClock-Wise [z][x][2,0]   

		//	 W <-- B        [5]		  =		 [1]   --------> step1							//	 W <-- B        [5]		  =		 [1]   --------> step1   
		//				{502,512,522} = {102,112,122}										//				{500,510,520} = {122,112,102}               

		//	 G <-- W        [3]		  =		 [5]   --------> step4							//	 G <-- W        [3]		  =		 [5]   --------> step4   
		//				{302,312,322} = {502,512,122} 										//				{300,310,320} = {500,510,520}                

		//	 Y <-- G        [0]		  =		 [3]   --------> step3 							//	 Y <-- G        [0]		  =		 [3]   --------> step3   
		//				{002,012,022} = {302,312,322}										//				{002,012,022} = {320,310,300}           

		//	 B <-- Y        [1]		  =		 [0]   --------> step2							//	 B <-- Y        [1]		  =		 [0]   --------> step2   
		//				{102,112,122} = {002,012,022}										//				{102,112,122} = {002,012,022}                

		// }																				// }                                                         
		//******************************* R *************************************
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];

		char tempArr[] = new char[3];  // for copying column 
		tempArr[0] = cube[5][0][0]; 
		tempArr[1] = cube[5][1][0]; 
		tempArr[2] = cube[5][2][0]; 

		// [5]  =  [1]
		cube[5][0][0] = cube[1][2][2]; 
		cube[5][1][0] = cube[1][1][2]; 
		cube[5][2][0] = cube[1][0][2]; 

		//##########################################################
		// [1]  =  [0]
		cube[1][0][2] = cube[0][0][2]; 
		cube[1][1][2] = cube[0][1][2]; 
		cube[1][2][2] = cube[0][2][2]; 

		//##########################################################
		// [0]  =  [3]
		cube[0][0][2] = cube[3][2][0]; 
		cube[0][1][2] = cube[3][1][0]; 
		cube[0][2][2] = cube[3][0][0]; 

		//##########################################################
		// [3]  =  [5]
		cube[3][0][0] = tempArr[0]; 
		cube[3][1][0] = tempArr[1]; 
		cube[3][2][0] = tempArr[2]; 


		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[2] = FaceRotor90.faceRotateCounter90(cube[2]); // right

		return cube;

	}// flipCounter method close

}// end class RightFlip

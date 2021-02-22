//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

public class UpFlip {

	// Up Flip

	public static char[][][] flip(char cube[][][]){
	
	// U {  // flip all up Row Clock-Wise(NOT column) [z][0][y]
	
	//	 B <-- R        [1]		  =		 [2]
	//				{100,101,102} =  {200,201,202}
	
	//	 R <-- G        [2]		  =		 [3]
	//				{200,201,202} =  {300,301,302}
	
	//	 G <-- O        [3]		  =		 [4] 
	//				{300,301,302} =  {400,401,402}
	
	//	 O <-- B        [4]		  =		 [1]
	//				{400,401,402} =  {100,101,102}		
	
	// }
	//******************************* R *************************************
	// Note: char tempArr[] = new char[3];  // for copying column 
	
	char tempArr[] = cube[1][0]; // for copying row		
	// [1]  =  [2]
	cube[1][0] = cube[2][0];
	
	//##########################################################
	// [2]  =  [3]
	cube[2][0] = cube[3][0];
	
	//##########################################################
	// [3]  =  [4]
	cube[3][0] = cube[4][0];
	
	//##########################################################
	// [4]  =  [1]
	cube[4][0] = tempArr;
	
	//	System.out.println(tempArr.length);  //for printing
	//	for(int i=0; i<tempArr.length; i++)
	//		System.out.println(tempArr[i]);
	//***********************************************************************
	cube[0] = FaceRotor90.faceRotate90(cube[0]);  // Front
	
	return cube;
	
	}// flip method close
	
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		// Reverse Up Flip
	
	public static char[][][] flipCounter(char cube[][][]){
	
	// U {  // flip all up Row antiClock-Wise(NOT column) [z][0][y]
	
	//	 R <-- B        [2]		  =		 [1]	-------> step1
	//				{200,201,202} = {100,101,102}   
	
	//	 G <-- R        [3]		  =		 [2]	-------> step4
	//				{300,301,302} = {200,201,202}   
	
	//	 O <-- G        [4]		  =		 [3]	-------> step3 
	//				{400,401,402} = {300,301,302}   
	
	//	 B <-- O        [1]		  =		 [4]	-------> step2
	//				{100,101,102} = {400,401,402}   		
	
	// }
	//******************************* R *************************************
	// Note: char tempArr[] = new char[3];  // for copying column 
	
	char tempArr[] = cube[2][0]; // for copying row		
	// [2]  =	[1]
	cube[2][0] = cube[1][0];
	
	//##########################################################
	// [1]	= [4]
	cube[1][0] = cube[4][0];
	
	//##########################################################
	// [4]	= [3]
	cube[4][0] = cube[3][0];
	
	//##########################################################
	// [3]  = [2]
	cube[3][0] = tempArr;
	
	
	//	System.out.println(tempArr.length);  //for printing
	//	for(int i=0; i<tempArr.length; i++)
	//		System.out.println(tempArr[i]);
	//***********************************************************************
	cube[0] = FaceRotor90.faceRotateCounter90(cube[0]);   // up
	
	return cube;
	
	}// flipCounter method close


	
}//End class UpFlip

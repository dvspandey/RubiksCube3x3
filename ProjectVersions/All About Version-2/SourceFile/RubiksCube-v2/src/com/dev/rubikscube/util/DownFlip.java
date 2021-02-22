//***************************** Flip Logics **************************
		//		 R		L		U	  F 	   D	  B 
		//		 R'		L'		U'	  F'	   D'	  B'



//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

public class DownFlip {

	// Down Flip

	public static char[][][] flip(char cube[][][]){

		// U {  // flip all Down Row Clock-Wise(NOT column) [z][2][y]

		//	 R <-- B        [2]		  =		 [1]	-------> step1
		//				{220,221,222} = {120,121,122}   

		//	 G <-- R        [3]		  =		 [2]	-------> step4
		//				{320,321,322} = {220,221,222}   

		//	 O <-- G        [4]		  =		 [3]	-------> step3 
		//				{420,421,422} = {320,321,322}   

		//	 B <-- O        [1]		  =		 [4]	-------> step2
		//				{120,121,122} = {420,421,422}   		

		// }
		//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 

		char tempArr[] = cube[2][2]; // for copying row		
		// [2]  =	[1]
		cube[2][2] = cube[1][2];

		//##########################################################
		// [1]	= [4]
		cube[1][2] = cube[4][2];

		//##########################################################
		// [4]	= [3]
		cube[4][2] = cube[3][2];

		//##########################################################
		// [3]  = [2]
		cube[3][2] = tempArr;


		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[5] = FaceRotor90.faceRotate90(cube[5]);  // Down

		return cube;

	}// flip method close


	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	// Reverse Down Flip

	public static char[][][] flipCounter(char cube[][][]){

		// U {  // flip all Down Row antiClock-Wise(NOT column) [z][2][y]

		//	 B <-- R        [1]		  =		 [2]
		//				{120,121,122} =  {220,221,222}

		//	 R <-- G        [2]		  =		 [3]
		//				{220,221,222} =  {320,321,322}

		//	 G <-- O        [3]		  =		 [4] 
		//				{320,321,322} =  {420,421,422}

		//	 O <-- B        [4]		  =		 [1]
		//				{420,421,422} =  {120,121,122}		

		// }
		//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 

		char tempArr[] = cube[1][2]; // for copying row		
		// [1]  =  [2]
		cube[1][2] = cube[2][2];

		//##########################################################
		// [2]  =  [3]
		cube[2][2] = cube[3][2];

		//##########################################################
		// [3]  =  [4]
		cube[3][2] = cube[4][2];

		//##########################################################
		// [4]  =  [1]
		cube[4][2] = tempArr;

		//	System.out.println(tempArr.length);  //for printing
		//	for(int i=0; i<tempArr.length; i++)
		//		System.out.println(tempArr[i]);
		//***********************************************************************
		cube[5] = FaceRotor90.faceRotateCounter90(cube[5]);  // Down

		return cube;

	}// flipCounter method close


}// end class DownFlip

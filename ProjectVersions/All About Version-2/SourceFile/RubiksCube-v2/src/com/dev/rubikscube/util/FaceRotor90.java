//package net.cube.rubikscube.model;
package com.dev.rubikscube.util;

class FaceRotor90 {
 
	//face Rotate by 90 (clock-wise)

	//{100,101,102},	 90 		{120,110,100}
	//{110,111,112},	------>   	{121,111,101}
	//{120,121,122}					{122,112,102}

	static char[][] faceRotate90(char[][] cube){
		char temp[][] = cube;
		char temp2[][] = new char[3][3];
		/*		
	System.out.println("temp:-");
	for(int i=0; i<3; i++){ 
		for(int j=0; j<3; j++){
			System.out.print(temp[i][j]+" ");
		}
		System.out.println();
	}
		 */	
		temp2[0][0] = temp[2][0];	
		temp2[0][1] = temp[1][0];
		temp2[0][2] = temp[0][0];

		temp2[1][0] = temp[2][1];
		temp2[1][1] = temp[1][1];  //---->final
		temp2[1][2] = temp[0][1];

		temp2[2][0] = temp[2][2];
		temp2[2][1] = temp[1][2];
		temp2[2][2] = temp[0][2];

		cube = temp2;
		/*
	System.out.println("temp2(cube):-");	
	for(int i=0; i<3; i++){
		for(int j=0; j<3; j++){
			System.out.print(temp2[i][j]+" ");
		}
		System.out.println();
	}
		 */	

		return cube;
	}// end faceRotate90

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	//face Rotate by -90 (Anticlock-wise)

	//{100,101,102},	 -90		{102,112,122}
	//{110,111,112},	------>   	{101,111,121}
	//{120,121,122}					{100,110,120}

	static char[][] faceRotateCounter90(char[][] cube){
		char temp[][] = cube;
		char temp2[][] = new char[3][3];
		/*
	for(int i=0; i<3; i++){
		for(int j=0; j<3; j++){
			System.out.print(temp[i][j]+" ");
		}
		System.out.println();
	}
		 */
		temp2[0][0] = temp[0][2];	
		temp2[0][1] = temp[1][2];
		temp2[0][2] = temp[2][2];

		temp2[1][0] = temp[0][1];
		temp2[1][1] = temp[1][1];  //---->final
		temp2[1][2] = temp[2][1];

		temp2[2][0] = temp[0][0];
		temp2[2][1] = temp[1][0];
		temp2[2][2] = temp[2][0];

		cube = temp2;
		/*
	for(int i=0; i<3; i++){
		for(int j=0; j<3; j++){
			System.out.print(cube[1][i][j]+" ");
		}
		System.out.println();
	}
		 */

		return cube;

	}// end faceRotateCounter90


}

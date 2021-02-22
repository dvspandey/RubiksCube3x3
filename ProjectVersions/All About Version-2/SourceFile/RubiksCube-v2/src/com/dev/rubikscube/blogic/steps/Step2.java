
/*-----------------------------------------------------------------------------------------*/
/*-------------------------------------- UP -----------------------------------------------*/
/*		
	> UP	(Develop a White Cross at Buttom of Cube)
	  ==
	> if any time we get 4 white on top then immidiatly stop this process!
		> now rotate up and find W/B ---> btw white/Blue center when get rotate it 2(F/F') (180)
		> now rotate up and find W/R ---> btw white/Red center when get rotate it 2(R/R')
		> now rotate up and find W/G ---> btw white/Green center when get rotate it 2(B/B')
		> now rotate up and find W/O ---> btw white/Orange center when get rotate it 2(L/L')

 */


/* ********************UP method New logic ******************************* */
// Motive of UP method : [(Develop a White Cross at Buttom on Cube)]

// take Do..While loop, 1 count=0 variable
// first check any edge is correct position with respect to centers if yes then Rotate by 180
// else Rotate Up and increase count varable by 1. and again cheack while condition while(count<3)
// here count represent actual number of UP rotation



//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;

public class Step2 {
	private char[][][] cube;
	private String myRotations = "";


	public Step2(char[][][] cube) {
		super();
		this.cube = cube;
		dvspandey();
		myRotations = PrepareSteps.setRotations(myRotations);//update 17-JAN-2021
	}
	public char[][][] getCube() {
		return cube;
	}
	public String getMyRotations() {
		return myRotations;
	}
	public void setCube(char[][][] cube) {
		this.cube = cube;
	}
	public void setMyRotations(String myRotations) {
		this.myRotations = myRotations;
	}

	void dvspandey() {//Caller Method
		cube = step2Main(cube);
	}


	//------------------------------- Logic -----------------------------------------------

	char[][][] step2Main(char[][][] cube){
		cube = up(cube);
		return cube;
	}


	char[][][] up(char[][][] cube){
		//{001,010,012,021}

		int count = 0;

		do
		{
			//for Face 1
			if(cube[5][1][1]  == cube[0][2][1] && cube[1][1][1] == cube[1][0][1]){
				//rotate 2(F/F')	
				cube = FrontFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " FF";
			}


			//for Face 2
			if(cube[5][1][1]  == cube[0][1][2] && cube[2][1][1] == cube[2][0][1]){
				//rotate 2(R/R')
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " RR";
			}

			//for Face 3
			if(cube[5][1][1]  == cube[0][0][1] && cube[3][1][1] == cube[3][0][1]){
				//rotate 2(B/B')	
				cube = BackFlip.flip(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " BB";
			}


			//for Face 4
			if(cube[5][1][1]  == cube[0][1][0] && cube[4][1][1] == cube[4][0][1]){
				//rotate 2(L/L')
				cube = LeftFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " LL";
			}

			// Rotate Top 90
			if(count<3){
				cube = UpFlip.flip(cube);
				myRotations = myRotations + " U";
			}
			count++;

		}
		while(count<=3);

		return cube;
	} // End up Method


}// End class Step2

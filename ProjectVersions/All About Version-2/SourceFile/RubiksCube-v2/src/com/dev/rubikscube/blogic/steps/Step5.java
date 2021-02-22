/*
* SOLVE FINAL LAYER – Step 1 Make A Yellow Cross
 ===================================================
 * At this point you have 3 cases on top face(0)
 		> No yellow color on the edges only check face(0).
			position [x01 x10 x12 x21]
			> then apply 6 step algo 
			> then check you have three yellow collor on top face(0)
			> if NO! then somthing went wrong.
			> if yes! then check you have right angle or horizontal line.
			  if you get one on these then apply perform bellow operations.
			 

		> Three yellow colors (include center pice) on the edges only check face(0).
					position [x01 x10 x12 x21]
				> Top left corner right angle with three yellow color 
					posibilities [24 (48 86 62)] with center pice[5]
				> horizontal vertical line with three yellow color 
					posibilities [46 (28)] with center pice[5]

Hint
----
> check cube have No yellow color (exclude center pice)
> check horizontal line by three yellow color (include center pice)
> check top left right angle by three yellow color (include center pice)

		[Algorithm]
		===========
		----> FURU'R'F' 
*
*/




//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;

public class Step5 {
	private char[][][] cube;
	private String myRotations = "";
	
	public Step5(char[][][] cube) {
		super();
		this.cube = cube;
		dvspandey();
		myRotations = PrepareSteps.setRotations(myRotations); //update 17-JAN-2021
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
		cube = step5Main(cube);
	}
	

	//------------------------------- Logic -----------------------------------------------	
	
	char[][][] step5Main(char[][][] cube){ 

		if(cube[0][0][1]==cube[0][1][1] && cube[0][1][0]==cube[0][1][1] && cube[0][1][2]==cube[0][1][1] && cube[0][2][1]==cube[0][1][1]){
			//System.out.println("Cube Step4 success....");

			return cube;
		}else{
			cube = caller(cube);
		}

		return cube;
	}// end step4Main method

	//*****************************************************************************	
	char[][][] caller(char[][][] cube){ // No need this Method if we call only once caseSelect() we will get correct o/p 
		for(int i=1;i<5;i++){
			if(cube[0][0][1]==cube[0][1][1] 
					&& cube[0][1][0]==cube[0][1][1] 
							&& cube[0][1][2]==cube[0][1][1] 
									&& cube[0][2][1]==cube[0][1][1]){
				//System.out.println("Cube Step4 success....");
				return cube;
			}else{
				cube = caseSelect(cube);
			}
		}//System.out.println("Cube Step4 caller Fail....");
		return cube;
	}// end caller method	

	//*****************************************************************************
	char[][][] caseSelect(char[][][] cube){ 
		int count = 1; // 1 for center
		if(cube[0][0][1]==cube[0][1][1]){
			count++;
		}
		if(cube[0][1][0]==cube[0][1][1]){
			count++;
		}
		if(cube[0][1][2]==cube[0][1][1]){
			count++;
		}
		if(cube[0][2][1]==cube[0][1][1]){
			count++;
		}


		if(count==1){
			//Case#1 No yellow color
			// 1> Apply algo6Step 
			cube = algo6Step(cube);

			// 2> Check now cube in which case.
			cube = caseSelect(cube);
		}else if(count==3){
			//Case#2 right angle Or horizontal line
			// call search3Yellow & perform suitable Operation
			cube = search3Yellow(cube);
		}else if(count==5){
			//Already yellow cross Exist
			//System.out.println("Cube Step4 Already yellow cross Exist....");
		}else{
			//System.out.println("Cube Step4 caseSelect Fail....    count:"+count);
		}

		return cube;
	}// end caseSelect method

	//*****************************************************************************
	char[][][] search3Yellow(char[][][] cube){
		int count = 1;
		if(cube[0][1][0]==cube[0][1][1] && cube[0][1][2]==cube[0][1][1]){
			//cube have horizontal yellow line
			// algo6Step
			cube = algo6Step(cube);
		}else if(cube[0][0][1]==cube[0][1][1] && cube[0][2][1]==cube[0][1][1]){
			//cube have Vertical yellow line
			// U
			cube = UpFlip.flip(cube);
			myRotations = myRotations + " U";
			// algo6Step
			cube = algo6Step(cube);
		}

		while(true)
		{
			if(cube[0][0][1]==cube[0][1][1] && cube[0][1][0]==cube[0][1][1]){
				break;
			}else if(count>4){
				//System.out.println("Cube Step4 search3Yellow Fail....");
			}count++;
			// U
			cube = UpFlip.flip(cube);
			myRotations = myRotations + " U";
		}
		cube = algo6Step(cube);
		return cube;
	}// end search3Yellow method


	//*****************************************************************************
	char[][][] algo6Step(char[][][] cube){
		//FURU'R'F'

		cube = FrontFlip.flip(cube);
		cube = UpFlip.flip(cube); 
		cube = RightFlip.flip(cube); 

		cube = UpFlip.flipCounter(cube);
		cube = RightFlip.flipCounter(cube);
		cube = FrontFlip.flipCounter(cube);

		myRotations = myRotations + " FURU'R'F'";
		return cube;
	}// end algo6Step method


	//*****************************************************************************

}// end Step5 class

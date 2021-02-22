/*
*	SOLVE THE FINAL LAYER – Position The Yellow Edges
	=================================================

		> At this stage you have two cube Cases:

			Stage1> No one is compleated! 
				> here we try to go at Stage2 so apply below Algo.
					------> F2ULR'F2L'RUF2 
					This Algo Rotate [x01] color 90* clockwise 

			Stage2> One face is compleated! (face--->1,2,3,4)
				> here, take compleatd face Back
					-------> F2ULR'F2L'RUF2


		Note:	This Algo Rotate center tile of top layer [x01]---to-->[(x-1)01]  90* clockwise (in this algo face3(Not involve))
				But if any time we getting one face which is totally compleated take that face at back[face(3)] and apply that algo
				and then algo will only rotate face(124) of [x01] tile so,  
				cube[1][0][1]----->cube[4][0][1]-------->cube[2][0][1]-------->cube[1][0][1]

Version change:		(Idea from v1.0)
--------------

For future version: we can remove below rotations and direct select case and apply Swap
then execution time will be less.

*/


//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;

public class Step8 {
	private char[][][] cube;
	private String myRotations = "";
	
	public Step8(char[][][] cube) {
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
		cube = step8Main(cube);
	}
	
	//------------------------------- Logic -----------------------------------------------
	
	
	
	char[][][] step8Main(char[][][] cube){  

		cube = caseSelect(cube);
		return cube;
	}// end step7Main method

	
//------------------------------------------------------------------	
	char[][][] caseSelect(char[][][] cube){  
		//Checking only
		int x,p=0;
		int count=0;
		for(x=1;x<=4;x++){
			if(cube[x][0][1]==cube[x][1][1]){
				count++;
				p=x;
				
			}
		}

		//System.out.println("Vax: "+x+" "+p);

		if(count==4){
			//System.out.println("Ready Cube Step7 Success....!");
			return cube;
		}else if(count==1){//Take compleatd face Back
			if(p==1){//rotate cube horizontally 180*
				//U2Mh2D'2
				cube = UpFlip.flip(cube); 
				cube = UpFlip.flip(cube); 
				cube = MiddleFlip.flipH(cube);
				cube = MiddleFlip.flipH(cube);
				cube = DownFlip.flipCounter(cube);
				cube = DownFlip.flipCounter(cube);
				myRotations = myRotations + " UUMhMhD'D'";
				
				//Calling algo12Step
				cube = algo12Step(cube);
				
				//System.out.println("May1 Success....!"); //Debug code
				//Calling caseSelect
				cube = caseSelect(cube);
			}else if(p==2){//rotate cube horizontally -90* (Anti-clockwise) 
				//U'Mh'D
				cube = UpFlip.flipCounter(cube);
				cube = MiddleFlip.flipCounterH(cube);
				cube = DownFlip.flip(cube);
				myRotations = myRotations + " U'Mh'D";
				
				//Calling algo12Step
				cube = algo12Step(cube);

				//System.out.println("May2 Success....!"); //Debug code
				//Calling caseSelect
				cube = caseSelect(cube);
			}else if(p==3){//rotate 0* (No rotation)  

				//Calling algo12Step
				cube = algo12Step(cube);

				//System.out.println("May3 Success....!"); //Debug code
				//Calling caseSelect
				cube = caseSelect(cube);
			}else if(p==4){//rotate cube horizontally 90* (clockwise)
				//UMhD'
				cube = UpFlip.flip(cube);
				cube = MiddleFlip.flipH(cube);
				cube = DownFlip.flipCounter(cube);
				myRotations = myRotations + " UMhD'";

				//Calling algo12Step
				cube = algo12Step(cube);
				
				//System.out.println("May4 Success....!"); //Debug code
				//Calling caseSelect
				cube = caseSelect(cube);
			}
		}else if(count==0){
			//Calling algo12Step
			cube = algo12Step(cube);		
			
			//Calling caseSelect
			cube = caseSelect(cube);
		}else{
			cube = caseSelect(cube);//---------------------------------------> DVS Check
		}


		return cube;
	}// end caseSelect

//------------------------------------------------------------------
	char[][][] algo12Step(char[][][] cube){  
		//F2ULR' F2L'RUF2

		cube = FrontFlip.flip(cube);
		cube = FrontFlip.flip(cube);
		cube = UpFlip.flip(cube);
		cube = LeftFlip.flip(cube);
		cube = RightFlip.flipCounter(cube); 

		cube = FrontFlip.flip(cube);
		cube = FrontFlip.flip(cube);
		cube = LeftFlip.flipCounter(cube);
		cube = RightFlip.flip(cube);
		cube = UpFlip.flip(cube); 
		cube = FrontFlip.flip(cube);
		cube = FrontFlip.flip(cube);
		myRotations = myRotations + " FFULR' FFL'RUFF";

		return cube;
	}// end algo12Step



}//Step8


























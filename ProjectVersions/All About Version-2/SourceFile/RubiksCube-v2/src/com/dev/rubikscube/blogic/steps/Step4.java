/*
*	The Middle Layer
*   =================
	> white face at Buttom.
	> then find the edge pice of top layer and target that edge which have not Yellow colour on it.
		> when you get a edge which have not yellow color on it then apply below algorithms.
			> turn and match [x][0][1] position color with its correct center.
			> then check a vertical line (x01,x11,x21) position have same color.
			> then check the 2nd color of that edge, and check where it's center pice is exist Left/Right
				> if center found Left side then apply left algorithm
					> U'L'UL (no need but if you want to check then upto this step 120 & 121 position have 2nd color of edge,
						and 100 & 101 position have 1st color of edge [1st color means which was found in top layer], and also 402 position has White color)
					> UFU'F' 
						{after compleating above 8 rotation, Check!
						we will get that edge pice now in middle layer and also both color of that edge pice match with their correct centers}

				> if center found Right side then apply right algorithm
					> URU'R' (no need but if you want to check then upto this step 121 & 122 position have 2nd color of edge,
						and 101 & 102 position have 1st color of edge [1st color means which was found in top layer], and also 200 position has White color)
					> U'F'UF
						{after compleating above 8 rotation, Check!
						we will get that edge pice now in middle layer and also both color of that edge pice match with their correct centers}

		> if all top layer Edge pice have one side(x01) yellow color then follow below instructions.
			> here you check all x01 position really have yellow color if yes! then,
			> find a edge pice in middle layer which is in wrong place with both color of it with centers. (x12 != x11) && ((x+1)10 != (x+1)11)
			> Apply 8 rotation of Right algo [URU'R' U'F'UF] ------> after that x12 have yello color and xEdge pice go back at top layer. 
					> x12 color go to -----> at face(0) with relation [(x+2)10] color
					> [x+1]10 color go to -----> [(x+2)10]
			> Go to back using [U2 Mh 2D2] 180* rotation
			> Apply that algorithm which you apply with that edge which have not yellow color. [Line 3]


			That's All!
		
*/


//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;


public class Step4 {
	private char[][][] cube;
	private String myRotations = "";
	
	public Step4(char[][][] cube) {
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
		cube = RightFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = MiddleFlip.flipV(cube);
		cube = MiddleFlip.flipV(cube);
		cube = LeftFlip.flipCounter(cube);
		cube = LeftFlip.flipCounter(cube);
		myRotations = myRotations + " RR MvMv LL";// new 16-JAN-2021
		
		cube = step4Main(cube);
	}

	//------------------------------- Logic -----------------------------------------------	
	
	char[][][] step4Main(char[][][] cube){ 

		if(cube[1][1][0] == cube[1][1][1] && cube[1][1][1] == cube[1][1][2]
			&& cube[2][1][0] == cube[2][1][1] && cube[2][1][1] == cube[2][1][2]
			&& cube[3][1][0] == cube[3][1][1] && cube[3][1][1] == cube[3][1][2]
			&& cube[4][1][0] == cube[4][1][1] && cube[4][1][1] == cube[4][1][2]){
				//System.out.println("Cube Step3 success....");
				return cube;
		}else{
			cube = caller(cube);
		}
		
		return cube;
	}// end step4Main method


	
	//*****************************************************************************	
	char[][][] caller(char[][][] cube){ 
		for(int i=1;i<5;i++){
			if(cube[1][1][0] == cube[1][1][1] && cube[1][1][1] == cube[1][1][2]
				&& cube[2][1][0] == cube[2][1][1] && cube[2][1][1] == cube[2][1][2]
				&& cube[3][1][0] == cube[3][1][1] && cube[3][1][1] == cube[3][1][2]
				&& cube[4][1][0] == cube[4][1][1] && cube[4][1][1] == cube[4][1][2]){
					//System.out.println("Cube Ste3 success....");
					return cube;
			}else{
				cube = noEdgeYellow(cube);
				cube = allEdgeYellow(cube);
			}
		}//System.out.println("Cube Step3 Fail....");
		return cube;
	}// end caller method	

	//*****************************************************************************	
	char[][][] noEdgeYellow(char[][][] cube){ 
		if(cube[1][0][1] != cube[0][1][1] && cube[0][2][1] != cube[0][1][1]){
			//turn and match [x][0][1] position color with its correct center.
			//call findMyCenter
			cube = findMyCenter(cube,1);
		}
		
		if(cube[2][0][1] != cube[0][1][1] && cube[0][1][2] != cube[0][1][1]){
			cube = findMyCenter(cube,2);			
		}

		if(cube[3][0][1] != cube[0][1][1] && cube[0][0][1] != cube[0][1][1]){
			cube = findMyCenter(cube,3);			
		}
		
		if(cube[4][0][1] != cube[0][1][1] && cube[0][1][0] != cube[0][1][1]){
			cube = findMyCenter(cube,4);			
		}


		return cube;
	}// end noEdgeYellow method

	//*****************************************************************************	
	char[][][] findMyCenter(char[][][] cube, int face){ 
			int j=face;
			while(true){
				if(cube[j][0][1]==cube[j][1][1]){
					break;
				}
				//U
				cube = UpFlip.flip(cube);
				myRotations = myRotations + " U";
				if(j==1){j = 4;}else{j--;}
			}
			// now j has face of matching center!
			// then check the 2nd color of that edge, and check where it's center pice is exist Left/Right
			// call findMyCenterLR
			cube = findMyCenterLR(cube,j);

		return cube;
	}// end findMyCenter method

	//*****************************************************************************
	char[][][] findMyCenterLR(char[][][] cube, int face){
		int j=face;

		if(j==1 && cube[0][2][1]==cube[4][1][1] && cube[1][0][1]==cube[1][1][1]){		//aply left Algo
			// call left algo method
			cube = leftAlgo(cube, 1);
		}else if(j==2 && cube[0][1][2]==cube[1][1][1] && cube[2][0][1]==cube[2][1][1]){
			cube = leftAlgo(cube, 2);			
		}else if(j==3 && cube[0][0][1]==cube[2][1][1] && cube[3][0][1]==cube[3][1][1]){
			cube = leftAlgo(cube, 3);
		}else if(j==4 && cube[0][1][0]==cube[3][1][1] && cube[4][0][1]==cube[4][1][1]){
			cube = leftAlgo(cube, 4);			
		}else if(j==1 && cube[0][2][1]==cube[2][1][1] && cube[1][0][1]==cube[1][1][1]){	//aply right Algo
			//call right Algo method
			cube = rightAlgo(cube, 1);
		}else if(j==2 && cube[0][1][2]==cube[3][1][1] && cube[2][0][1]==cube[2][1][1]){
			cube = rightAlgo(cube, 2);
		}else if(j==3 && cube[0][0][1]==cube[4][1][1] && cube[3][0][1]==cube[3][1][1]){
			cube = rightAlgo(cube, 3);
		}else if(j==4 && cube[0][1][0]==cube[1][1][1] && cube[4][0][1]==cube[4][1][1]){
			cube = rightAlgo(cube, 4);			
		}

		return cube;
	}// end findMyCenterLR method

	//*****************************************************************************
	char[][][] leftAlgo(char[][][] cube, int face){
		int j = face;
		
		if(j==1){
			//U'L'UL
			//UFU'F'
			cube = UpFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = LeftFlip.flip(cube); 

			cube = UpFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = FrontFlip.flipCounter(cube);
			myRotations = myRotations + " U'L'UL UFU'F'";
		}else if(j==2){
			//U'F'UF
			//URU'R'
			cube = UpFlip.flipCounter(cube);
			cube = FrontFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = FrontFlip.flip(cube);

			cube = UpFlip.flip(cube);
			cube = RightFlip.flip(cube); 
			cube = UpFlip.flipCounter(cube);
			cube = RightFlip.flipCounter(cube);
			myRotations = myRotations + " U'F'UF URU'R'";
		}else if(j==3){
			//U'R'UR
			//UBU'B'
			cube = UpFlip.flipCounter(cube);
			cube = RightFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = RightFlip.flip(cube);

			cube = UpFlip.flip(cube);
			cube = BackFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = BackFlip.flipCounter(cube);
			myRotations = myRotations + " U'R'UR UBU'B'";
		}else if(j==4){
			//U'B'UB
			//ULU'L'
			cube = UpFlip.flipCounter(cube);
			cube = BackFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = BackFlip.flip(cube);

			cube = UpFlip.flip(cube);
			cube = LeftFlip.flip(cube); 
			cube = UpFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube);
			myRotations = myRotations + " U'B'UB ULU'L'";
		}
		
		int t = j;
		if(t==1){t = 4;}else{t--;}
//		if(cube[j][1][0] == cube[j][1][1] && cube[t][1][2] == cube[t][1][1]){ //Debug code
//			System.out.println("leftAlgo -------------------> True");
//		}else{
//			System.out.println("leftAlgo -------------------> Fales");
//		}

		return cube;
	}// end leftAlgo method

	//*****************************************************************************
	char[][][] rightAlgo(char[][][] cube, int face){
		int j = face;
		
		if(j==1){
			//URU'R' 
			//U'F'UF
			cube = UpFlip.flip(cube);
			cube = RightFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = RightFlip.flipCounter(cube);

			cube = UpFlip.flipCounter(cube);
			cube = FrontFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			myRotations = myRotations + " URU'R' U'F'UF";
		}else if(j==2){
			//UBU'B' 
			//U'R'UR
			cube = UpFlip.flip(cube);
			cube = BackFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = BackFlip.flipCounter(cube);

			cube = UpFlip.flipCounter(cube);
			cube = RightFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = RightFlip.flip(cube);
			myRotations = myRotations + " UBU'B' U'R'UR";
		}else if(j==3){
			//ULU'L' 
			//U'B'UB
			cube = UpFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube); 

			cube = UpFlip.flipCounter(cube);
			cube = BackFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = BackFlip.flip(cube);
			myRotations = myRotations + " ULU'L' U'B'UB";
		}else if(j==4){
			//UFU'F' 
			//U'L'UL
			cube = UpFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			cube = UpFlip.flipCounter(cube);
			cube = FrontFlip.flipCounter(cube);

			cube = UpFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube);
			cube = UpFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			myRotations = myRotations + " UFU'F' U'L'UL";
		}
		
		int t = j;
		if(t==4){t = 1;}else{t++;}
//		if(cube[j][1][2] == cube[j][1][1] && cube[t][1][0] == cube[t][1][1]){ //Debug code
//			System.out.println("rightAlgo -------------------> True");
//		}else{
//			System.out.println("rightAlgo -------------------> Fales");
//		}

		return cube;
	}// end rightAlgo method
	
	//##################################################################################
	char[][][] allEdgeYellow(char[][][] cube){
		//here you check all x01 position really have yellow color if yes! then,


		if((cube[1][0][1] != cube[0][1][1] && cube[0][2][1] != cube[0][1][1]) 
			|| (cube[2][0][1] != cube[0][1][1] && cube[0][1][2] != cube[0][1][1])
			|| (cube[3][0][1] != cube[0][1][1] && cube[0][0][1] != cube[0][1][1])
			|| (cube[4][0][1] != cube[0][1][1] && cube[0][1][0] != cube[0][1][1])){
				//System.out.println("All edge have Not yellow color!");
				return cube;				
		}
		
		//System.out.println("All edge have yellow color!, -------> so you get RightAlgo False don't worry! :) \n and if you not getting anything means already step3 Done! ");
		//find a edge pice in middle layer which is in wrong place with both color of it with centers. (x12 != x11) && ((x+1)10 != (x+1)11)		
		// Calling findEdge
		cube = findEdge(cube);


		return cube;
	}// end allEdgeYellow method

	//*****************************************************************************
	char[][][] findEdge(char[][][] cube){
		if(cube[1][1][2] != cube[1][1][1] && cube[2][1][0] != cube[2][1][1]){
			//apply right Algo
			cube = rightAlgo(cube, 1);
			cube = noEdgeYellow(cube);
		}else if(cube[2][1][2] != cube[2][1][1] && cube[3][1][0] != cube[3][1][1]){
			cube = rightAlgo(cube, 2);
			cube = noEdgeYellow(cube);
		}else if(cube[3][1][2] != cube[3][1][1] && cube[4][1][0] != cube[4][1][1]){
			cube = rightAlgo(cube, 3);
			cube = noEdgeYellow(cube);
		}else if(cube[4][1][2] != cube[4][1][1] && cube[1][1][0] != cube[1][1][1]){
			cube = rightAlgo(cube, 4);
			cube = noEdgeYellow(cube);			
		}

		return cube;
	}// end findEdge method


}// end class Step4








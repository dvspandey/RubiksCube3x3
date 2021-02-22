/*
*	 Solve The White Corners
==================================

Yellow	<---upset to----> White 
Blue	<---upset to----> Green
Red		<---upset to----> Orange

> Center pice have one color which we can touch with single fingure [Total 4]
> Edge pice have two color and we can touch with two fingures or pinch. [Total 12]
> Corner pice have Three color and we can touch with three fingurs. [Total 8]


	Take White center at Top

		cube = RightFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = MiddleFlip.flipV(cube);
		cube = MiddleFlip.flipV(cube);
		cube = LeftFlip.flipCounter(cube);
		cube = LeftFlip.flipCounter(cube);

NOTE: if any time You got Unexpected result through possibility3, possibility4 then remove below given code,

			if(cube[face][2][2] == cube[0][1][1] && cube[face+1][2][0] == cube[2][1][1]){
				cube = rigntAlgo(cube,face);
			}else{
				System.out.println("cube[2][2][0] == cube[2][1][1]--------> possibility4 False, rotate D' & find correct center");
			}

	And then call direct 
			cube = colorFindLogicRignt(cube, face);
		then it will find correct place for color and high chance is there your problem will solve.

*/



//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;

public class Step3 {
	private char[][][] cube;
	private String myRotations = "";

	
	
	public Step3(char[][][] cube) {
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
		//Rotate 180* Vertically so White Cross on Top
		cube = RightFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = MiddleFlip.flipV(cube);
		cube = MiddleFlip.flipV(cube);
		cube = LeftFlip.flipCounter(cube);
		cube = LeftFlip.flipCounter(cube);
		myRotations = myRotations + " RR MvMv LL";// new 16-JAN-2021
		
		cube = step3Main(cube);
	}


	//------------------------------- Logic -----------------------------------------------


	static int i = 1;
	 char[][][] step3Main(char[][][] cube){ 

		if(cube[1][0][1]==cube[1][1][1] && cube[2][0][1]==cube[2][1][1] && cube[3][0][1]==cube[3][1][1] && cube[4][0][1]==cube[4][1][1]){
			if((cube[1][0][0]==cube[1][1][1] && cube[2][0][0]==cube[2][1][1] && cube[3][0][0]==cube[3][1][1] && cube[4][0][0]==cube[4][1][1])
				&& (cube[1][0][2]==cube[1][1][1] && cube[2][0][2]==cube[2][1][1] && cube[3][0][2]==cube[3][1][1] && cube[4][0][2]==cube[4][1][1])){
				return cube;
			}else{
				cube = makeReady(cube);
			}
		}
		
	
		if(i<5){
			cube = step3Main(cube);
			i++;
		}else{
			//System.out.println("dvs: Error Occur in Step2");
			return cube;
		}

		return cube;
	}// end step3Main
	
	char[][][] makeReady(char[][][] cube){
		cube = rightCornerPice(cube);
		cube = leftButtomPice(cube);
		cube = possibility3(cube);
		cube = possibility4(cube);
		cube = randomCheckRight(cube);
		return cube;
	} // end makeReady

	
	//**************************************************************

	char[][][] colorFindLogicRignt(char[][][] cube, int face){
			
			if(face==4){face=1;}else{++face;}

				int j = face; //face(face) so j=face because rotate anticlockwise
				while(true){ //it will match cube[1][2][0] color with it's matching color center
					if(cube[j][2][0] == cube[j][1][1]){	break; }
					if(j==1){j=4;}else{--j;}  // Or use Ternary Operator [j = (j==1) ? 4 : --j;]
					
					//D' 
					cube = DownFlip.flipCounter(cube);
					myRotations = myRotations + " D'";
				}
				// j have the Corner pice position,below it will give that place cube[j][2][2] where the white pice exist
				//which is also the part of same CORNER pice which is detached.
				if(j==1){j = 4;}else{j--;} // Or use Ternary Operator [j = (j==1) ? 4 : --j;]
//				if (cube[j][2][2] == cube[0][1][1]){ //Debug code
//					System.out.println("colorFindLogicRignt----------------------> True");
//				}else{
//					System.out.println("colorFindLogicRignt----------------------> False");
//				}
				cube = rigntAlgo(cube, j); // Apply Right Algorithm {D'R'DR} //if (cube[j][2][2] == cube[0][1][1] && cube[0][1][2] == cube[0][1][1]) w.r.t face(1)
			//}else{
			//	System.out.println("Error: colorFindLogicRignt method");//Debug code
			//}
		return cube;
	}// end colorFindLogicRignt
	//*********************************************************************************
	char[][][] colorFindLogicLeft(char[][][] cube, int face){
		
			if(face==1){face=4;}else{--face;}

			//if(cube[face][2][2] != cube[face][1][1]){//pos(9)

				int j = face; 
				while(true){ //it will match cube[2][2][2] color with it's matching color center
					if(cube[j][2][2] == cube[j][1][1]){	break; }
					if(j==1){j=4;}else{--j;}  // Or use Ternary Operator [j = (j==1) ? 4 : --j;]
					
					//D' 
					cube = DownFlip.flipCounter(cube);
					myRotations = myRotations + " D'";
				}
				// j have the Corner pice position,below it will give that place cube[j][2][0] where the white pice exist
				//which is also the part of same CORNER pice.
				if(j==4){j = 1;}else{j++;} // Or use Ternary Operator [j = (j==4) ? 1 : ++j;]
				
				cube = leftAlgo(cube, j); // Apply Left Algorithm {DLD'L'}
			//}else{										//Debug code
			//	System.out.println("Error: colorFindLogicLeft method Or may it already done!");
			//}
		return cube;
	}// end colorFindLogicLeft

	//*********************************************************************************
	//*********************************************************************************

	char[][][] rigntAlgo(char[][][] cube,int j){
		
				if(j==1 && cube[1][2][2] == cube[0][1][1] && cube[2][2][0] == cube[2][1][1] && cube[0][1][2] == cube[0][1][1]){// Apply Right Algorithm {D'R'DR}
					//D'R'DR
					cube = DownFlip.flipCounter(cube);	
					cube = RightFlip.flipCounter(cube); 	
					cube = DownFlip.flip(cube); 
					cube = RightFlip.flip(cube);
					myRotations = myRotations + " D' R' D R";
					if(cube[1][0][2] == cube[1][1][1] && cube[2][0][0] == cube[2][1][1]){ //Debug code Blue pos(1) match with Blue center && Orange match pos(3) with Orange center
						//System.out.println("Right algo Success...face(1) && face(2)");
					}//else{System.out.println("Error: rigntAlgo");}
				}else if(j==2 && cube[2][2][2] == cube[0][1][1] && cube[3][2][0] == cube[3][1][1] && cube[0][0][1] == cube[0][1][1]){
					//D'B'DB
					cube = DownFlip.flipCounter(cube);	
					cube = BackFlip.flipCounter(cube); 	
					cube = DownFlip.flip(cube); 
					cube = BackFlip.flip(cube);
					myRotations = myRotations + " D' B' D B";
					if(cube[2][0][2] == cube[2][1][1] && cube[3][0][0] == cube[3][1][1]){ //Debug code
						//System.out.println("Right algo Success...face(2) && face(3)");
					}//else{System.out.println("Error: rigntAlgo");}
				}else if(j==3 && cube[3][2][2] == cube[0][1][1] && cube[4][2][0] == cube[4][1][1] && cube[0][1][0] == cube[0][1][1]){
					//D'L'DL
					cube = DownFlip.flipCounter(cube);	
					cube = LeftFlip.flipCounter(cube);	
					cube = DownFlip.flip(cube); 
					cube = LeftFlip.flip(cube);
					myRotations = myRotations + " D' L' D L";
					if(cube[3][0][2] == cube[3][1][1] && cube[4][0][0] == cube[4][1][1]){ //Debug code
						//System.out.println("Right algo Success...face(3) && face(4)");
					}//else{System.out.println("Error: rigntAlgo");}
				}else if(j==4 && cube[4][2][2] == cube[0][1][1] && cube[1][2][0] == cube[1][1][1] && cube[0][2][1] == cube[0][1][1]){
					//D'F'DF
					cube = DownFlip.flipCounter(cube);	
					cube = FrontFlip.flipCounter(cube);	
					cube = DownFlip.flip(cube); 
					cube = FrontFlip.flip(cube); 
					myRotations = myRotations + " D' F' D F";
					if(cube[4][0][2] == cube[4][1][1] && cube[1][0][0] == cube[1][1][1]){ //Debug code
						//System.out.println("Right algo Success...face(4) && face(1)");
					}//else{System.out.println("Error: rigntAlgo");}
					
				}else{
					//Debug code
					//System.out.println("Appling Rignt algorithm getting som difficulty! plz Check!");
				}

		return cube;
	}// end rigntAlgo

	//*********************************************************************************

	char[][][] leftAlgo(char[][][] cube,int i){
		
				if(i==1 && cube[1][2][0] == cube[0][1][1] && cube[4][2][2] == cube[4][1][1]){// Apply Left Algorithm {DLD'L'} but cube[x][2][0] shoud not have white otherwise override which is waste(No use)!
					//DLD'L'
					cube = DownFlip.flip(cube);
					cube = LeftFlip.flip(cube); 
					cube = DownFlip.flipCounter(cube);	
					cube = LeftFlip.flipCounter(cube); 
					myRotations = myRotations + " D L D' L'";
					if(cube[4][0][2] == cube[4][1][1] && cube[1][0][0] == cube[1][1][1]){ //Debug code Red pos(3) match with Red center && Blue match pos(1) with Blue center
						//System.out.println("Left algo Success...face(1) && face(4)");
					}//else{System.out.println("Error: leftAlgo");}
				}else if(i==2 && cube[2][2][0] == cube[0][1][1] && cube[1][2][2] == cube[1][1][1]){
					//DFD'F'
					cube = DownFlip.flip(cube);
					cube = FrontFlip.flip(cube);  
					cube = DownFlip.flipCounter(cube);	
					cube = FrontFlip.flipCounter(cube);
					myRotations = myRotations + " D F D' F'";
					if(cube[1][0][2] == cube[1][1][1] && cube[2][0][0] == cube[2][1][1]){ //Debug code
						//System.out.println("Left algo Success...face(2) && face(1)");
					}//else{System.out.println("Error: leftAlgo");}
				}else if(i==3 && cube[3][2][0] == cube[0][1][1] && cube[2][2][2] == cube[2][1][1]){
					//DRD'R'
					cube = DownFlip.flip(cube);
					cube = RightFlip.flip(cube); 
					cube = DownFlip.flipCounter(cube);	
					cube = RightFlip.flipCounter(cube);
					myRotations = myRotations + " D R D' R'";
					if(cube[2][0][2] == cube[2][1][1] && cube[3][0][0] == cube[3][1][1]){ //Debug code
						//System.out.println("Left algo Success...face(3) && face(2)");
					}//else{System.out.println("Error: leftAlgo");}
				}else if(i==4 && cube[4][2][0] == cube[0][1][1] && cube[3][2][2] == cube[3][1][1]){
					//DBD'B'
					cube = DownFlip.flip(cube);
					cube = BackFlip.flip(cube); 
					cube = DownFlip.flipCounter(cube);	
					cube = BackFlip.flipCounter(cube);
					myRotations = myRotations + " D B D' B'";
					if(cube[3][0][2] == cube[3][1][1] && cube[4][0][0] == cube[4][1][1]){ //Debug code
						//System.out.println("Left algo Success...face(4) && face(3)");
					}//else{System.out.println("Error: leftAlgo");}
				}else{
					//Debug code
					//System.out.println("Appling Left algorithm getting som difficulty! plz Check!");
				}
		
		return cube;
	}//end leftAlgo

	//***********************************************************************************
	//***********************************************************************************

	
	char[][][] rightCornerPice(char[][][] cube){
	/*	
	________________________________________________________________________________________________________________________________________________________
	|																																						|
	|	// For RIGHT SIDE TOP CORNER PICE																													|
	|	//==================================																												|
	|	// here we focus on pos(3) of each face if any pos(3) is not match with its face center then we apply this folloing step.							|
	|	//	> if pos(3) has not its correct color so there is high possibility pos(0) also has also not its correct color of (currentFace+1)th face			|
	|	//			Ex: current face is face(1) so (currentFace+1) is face(3).																				|
	|	//	> And after these two conditions are satisfy we will check one more condition which is mandetory 												|
	|	//		if we focus On cube and take these two position pos(3) of face(1) & pos(1) of face(2), it is the part of one CORNER pice of cube.			|
	|	//		and we know that CORNER pice have 3 color so last third color should be of White color.														|
	|	// If all these conditions satisfy then here we apply Right algorith but before that we do some additional steps for take(detach) this CORNER pice	|
	|	//		at buttom layer for no distruction of top layer.																							|
	|	//		> Detach Corner pice																														|
	|	//		> Find that corner pice using colorFindLogicRignt(); method.																				|
	|	//		> Apply Right Algorithm.																													|
	|_______________________________________________________________________________________________________________________________________________________|
	*/																																							
		if(cube[0][2][2] == cube[0][1][1] && cube[1][0][2] != cube[1][1][1] && cube[2][0][0] != cube[2][1][1]){												
			//R'D'R		for Detach Corner pice
			cube = RightFlip.flipCounter(cube); 
			cube = DownFlip.flipCounter(cube);	
			cube = RightFlip.flip(cube);
			myRotations = myRotations + " R' D' R";
			
			cube = colorFindLogicRignt(cube,4); //old cube = colorFindLogicRignt(cube,1);
		}
		if(cube[0][0][2] == cube[0][1][1] && cube[2][0][2] != cube[2][1][1] && cube[3][0][0] != cube[3][1][1]){
			//B'D'B
			cube = BackFlip.flipCounter(cube); 
			cube = DownFlip.flipCounter(cube);	
			cube = BackFlip.flip(cube);
			myRotations = myRotations + " B' D' B";
			
			cube = colorFindLogicRignt(cube,1); //old cube = colorFindLogicRignt(cube,2);	
		}
		if(cube[0][0][0] == cube[0][1][1] && cube[3][0][2] != cube[3][1][1] && cube[4][0][0] != cube[4][1][1]){
			//L'D'L
			cube = LeftFlip.flipCounter(cube);  
			cube = DownFlip.flipCounter(cube);	
			cube = LeftFlip.flip(cube);
			myRotations = myRotations + " L' D' L";

			cube = colorFindLogicRignt(cube,2); //old cube = colorFindLogicRignt(cube,3);	
		}
		if(cube[0][2][0] == cube[0][1][1] && cube[4][0][2] != cube[4][1][1] && cube[1][0][0] != cube[1][1][1]){
			//F'D'F
			cube = FrontFlip.flipCounter(cube);  
			cube = DownFlip.flipCounter(cube);	
			cube = FrontFlip.flip(cube); 
			myRotations = myRotations + " F' D' F";

			cube = colorFindLogicRignt(cube,3); //old cube = colorFindLogicRignt(cube,4);
		}
		return cube;
	}// end rightCornerPice

	//***********************************************************************************
	char[][][] leftButtomPice(char[][][] cube){
	/*	
	________________________________________________________________________________________________________________________________________________________
	|																																						|
	|	// For LEFT SIDE BUTTOM CORNER PICE																													|
	|	//==================================																												|
	|	// Here we focus on pos(7) of each face if any pos(7) is White color then we apply this folloing step.												|
	|	//	> if pos(3) has white color so there is high possibility pos(9) also has also not its correct color of (currentFace-1)th face					|
	|	//			Ex: current face is face(1) so (currentFace-1) is face(4).																				|
	|																																						|
	|	// If all these conditions satisfy then here we apply Find correct position for pos(9) element matching color center with help of D' rotation		|
	|	//		> Find that corner position for pos(9) element using colorFindLogicLeft(); method.															|
	|				if it is already of its correct position then we directly apply below Left Algorithm													|
	|	//		> Apply Right Algorithm.																													|
	|_______________________________________________________________________________________________________________________________________________________|
	*/	
		if(cube[1][2][0] == cube[0][1][1]){
			cube = colorFindLogicLeft(cube, 1);
		}
		if(cube[2][2][0] == cube[0][1][1]){
			cube = colorFindLogicLeft(cube, 2);
		}
		if(cube[3][2][0] == cube[0][1][1]){
			cube = colorFindLogicLeft(cube, 3);
		}
		if(cube[4][2][0] == cube[0][1][1]){
			cube = colorFindLogicLeft(cube, 4);
		}

		return cube;
	}// end leftButtomPice



	//***********************************************************************************
	//***********************************************************************************
	
	
	
	/*
		Possibility #3
		===============
	> some times white color at button to buttom corner pices. && with digonal relationship 'missing element' is there with upset Top to top corner
			
			face(5)				  missing face(0)		(Note: maybe color not same but taking for reference)
			=======				  ===============		
			pos(7)	<--------------->	pos(1) 	w.r.t face(1) Blue
			pos(1)	<--------------->	pos(7) 	w.r.t face(2) Red
			pos(3)	<--------------->	pos(9) 	w.r.t face(3) Green
			pos(9)	<--------------->	pos(3) 	w.r.t face(4) Orange
			
			> cube[5][2][0] <---------------> missing cube[0][0][0] 
			> cube[5][0][0] <---------------> missing cube[0][2][0] 
			> cube[5][0][2] <---------------> missing cube[0][2][2] 
			> cube[5][2][2] <---------------> missing cube[0][0][2] 
	

			Steps To solve
			==============
			> Center setting : Rotate D' upto corner come with expected center.
			> Apply FD'F'D2 Algo
			> then Apply Right Algo
	*/
	char[][][] possibility3(char[][][] cube){
		// find that face w.r.t  white buttom occure and pass centerSetting
		if(cube[5][2][0]==cube[0][1][1]){
			cube = centerSetting(cube,1);
		}
		if(cube[5][0][0]==cube[0][1][1]){
			cube = centerSetting(cube,2);
		}
		if(cube[5][0][2]==cube[0][1][1]){
			cube = centerSetting(cube,3);
		}
		if(cube[5][2][2]==cube[0][1][1]){
			cube = centerSetting(cube,4);
		}

		return cube;
	}//end possibility3
	
	
	char[][][] centerSetting(char[][][] cube,int face){
		int j = face;
		while(true){
			// int x = (j==4) ? 1 :j+1;
			if(cube[(j==4) ? 1 :j+1][2][0] == cube[j][1][1] && cube[j][2][2] == cube[(j==4) ? 1 :j+1][1][1]){
				// call FD'F'D2 | cube = possibility3Algo(cube,j);
				// then Right algo| cube = rigntAlgo(cube,j);
				cube = possibility3Algo(cube,j);
				
				cube = colorFindLogicRignt(cube, j);
/*//---------------------------------------------------------------------// Why i comment this code Check Note at Top of that file.
			if(cube[j][2][2] == cube[0][1][1] && cube[j+1][2][0] == cube[j+1][1][1]){
				cube = rigntAlgo(cube,j);
			}else{
				System.out.println("cube[j+1][2][0] == cube[j+1][1][1]--------> [possibility3] centerSetting False, rotate D' & find correct center");
			}
//---------------------------------------------------------------------
*/				//System.out.println("centerSetting Success.. ");
				break;
			}else{
				//System.out.println("centerSetting white(true)");
			}
			if(j==1){j=4;}else{--j;}
			//D'
			cube = DownFlip.flipCounter(cube);
			myRotations = myRotations + " D'";
		}


		return cube;
	}// end centerSetting
	
	char[][][] possibility3Algo(char[][][] cube,int face){
		if(face==1){
			//FD'F'D2
			cube = FrontFlip.flip(cube);
			cube = DownFlip.flipCounter(cube);
			cube = FrontFlip.flipCounter(cube);  	
			cube = DownFlip.flip(cube); 
			cube = DownFlip.flip(cube); 
			myRotations = myRotations + " F D' F' DD";
		}else if(face==2){
			//RD'R'D2
			cube = RightFlip.flip(cube); 
			cube = DownFlip.flipCounter(cube);
			cube = RightFlip.flipCounter(cube);    	
			cube = DownFlip.flip(cube); 
			cube = DownFlip.flip(cube); 
			myRotations = myRotations + " R D' R' DD";
		}else if(face==3){
			//BD'B'D2
			cube = BackFlip.flip(cube); 
			cube = DownFlip.flipCounter(cube);
			cube = BackFlip.flipCounter(cube);	   	
			cube = DownFlip.flip(cube); 
			cube = DownFlip.flip(cube); 
			myRotations = myRotations + " B D' B' DD";
		}else if(face==4){
			//LD'L'D2
			cube = LeftFlip.flip(cube); 
			cube = DownFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube);	   	
			cube = DownFlip.flip(cube); 
			cube = DownFlip.flip(cube);
			myRotations = myRotations + " L D' L' DD";
		}

//		if(cube[face][2][2] == cube[0][1][1]){ //Debug code
//			System.out.println("possibility3Algo: GooD ");
//		}else{
//			System.out.println("possibility3Algo: Bad ");
//		}
		return cube;
	} // end possibility3Algo


//****************************************************************************************************
//****************************************************************************************************

	/*
		Possibility #4  
		=============== 
			> Possibility4 is white color at the top layer 'pos(3)' cube[x][0][2]
			> Apply R'DR algo
			> Apply right algo
	*/

	char[][][] possibility4(char[][][] cube){
		if(cube[1][0][2]==cube[0][1][1]){
			// R'DR
			cube = RightFlip.flipCounter(cube); 
			cube = DownFlip.flip(cube);
			cube = RightFlip.flip(cube);
			myRotations = myRotations + " R' D R";
			
			cube = colorFindLogicRignt(cube, 1);
			/*if(cube[1][2][2] == cube[0][1][1] && cube[2][2][0] == cube[2][1][1]){ // Why i comment this code Check Note at Top of that file.
				cube = rigntAlgo(cube,1);
			}else{
				System.out.println("cube[2][2][0] == cube[2][1][1]--------> possibility4 False, rotate D' & find correct center");
			}*/
		}else if(cube[2][0][2]==cube[0][1][1]){
			// B'DB
			cube = BackFlip.flipCounter(cube); 
			cube = DownFlip.flip(cube);
			cube = BackFlip.flip(cube); 
			myRotations = myRotations + " B' D B";

			cube = colorFindLogicRignt(cube, 2);
			/*if(cube[2][2][2] == cube[0][1][1] && cube[3][2][0] == cube[3][1][1]){
				cube = rigntAlgo(cube,2);
			}else{
				System.out.println("cube[3][2][0] == cube[3][1][1]--------> possibility4 False, rotate D' & find correct center");
			}*/
		}else if(cube[3][0][2]==cube[0][1][1]){
			// L'DL
			cube = LeftFlip.flipCounter(cube); 
			cube = DownFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			myRotations = myRotations + " L' D L";

			cube = colorFindLogicRignt(cube, 3);
			/*if(cube[3][2][2] == cube[0][1][1] && cube[4][2][0] == cube[4][1][1]){
				cube = rigntAlgo(cube,3);
			}else{
				System.out.println("cube[4][2][0] == cube[4][1][1]--------> possibility4 False, rotate D' & find correct center");
			}*/
		}else if(cube[4][0][2]==cube[0][1][1]){
			// F'DF
			cube = FrontFlip.flipCounter(cube); 
			cube = DownFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			myRotations = myRotations + " F' D F";
			
			cube = colorFindLogicRignt(cube, 4);
			/*if(cube[4][2][2] == cube[0][1][1] && cube[1][2][0] == cube[1][1][1]){
				cube = rigntAlgo(cube,4);
			}else{
				System.out.println("cube[1][2][0] == cube[1][1][1]--------> possibility4 False, rotate D' & find correct center");
			}*/
		}

		return cube;
	}// end possibility4


//****************************************************************************************************
//****************************************************************************************************

/*-------------------------------------- Random Check ---------------------------------------------------------
	If apply all above logics and we not getting correct result at that time apply below logic!
	> rightAlgo Application
	> leftAlgo Application

	> For rightAlgo Application
		> find there is any white color exist at buttom layer at (122,222,322,422) if yes then
		> check at (220,320,420,120) respectivly are correct match with its center color?
			> if Yes then apply(or call) direct rightAlgo(cube,faceWhereWhiteexist).
			> if Not then before you must match color with center pice Using D' then
				apple(or call) direct rightAlgo(cube,faceWhereWhiteexist).




	> For leftAlgo Application
			> find there is any white color exist at buttom layer at (120,220,320,420) if yes then
			> check at (422,122,222,322) respectivly are correct match with its center color?
				> if Yes then apply(or call) direct leftAlgo(cube,faceWhereWhiteexist).
				> if Not then before you must match color with center pice Using D' then
					apple(or call) direct lightAlgo(cube,faceWhereWhiteexist).
		Simillar: leftButtomPice(char[][][] cube)

*/

	char[][][] randomCheckRight(char[][][] cube){
	
		if(cube[1][2][2] == cube[0][1][1]){
			cube = colorFindLogicRignt(cube, 1);
		}
		if(cube[2][2][2] == cube[0][1][1]){
			cube = colorFindLogicRignt(cube, 2);
		}
		if(cube[3][2][2] == cube[0][1][1]){
			cube = colorFindLogicRignt(cube, 3);
		}
		if(cube[4][2][2] == cube[0][1][1]){
			cube = colorFindLogicRignt(cube, 4);
		}

		return cube;
	}


}// end Step2

	

































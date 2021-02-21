//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic.steps;

import com.dev.rubikscube.blogic.PrepareSteps;
import com.dev.rubikscube.util.*;

public class Step1 { 
	private char[][][] cube; 
	private String myRotations = "";
	
	
	
	
	public Step1(char[][][] cubeArg) {
		super();
		this.cube = cubeArg;

		dvspandey();//Caller Method
		myRotations = PrepareSteps.setRotations(myRotations); //update 17-JAN-2021
	}

	//Getter Methods	
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
		  try{
			 cube = step1Main(cube); //The daisy
		  }
			 catch(MyException e){
			//System.out.println(e.toString());
			//System.out.println("Devesh: "+myRotations); 
			cube = e.cube();
		 }
	}
	
	
	
	
	//------------------------------- Logic -----------------------------------------------	
	

	static int value = 0;
	char[][][] step1Main(char[][][] cube)throws MyException {
		myRotations = myRotations + "";
		if((cube[5][0][1] == cube[5][1][1]) && (cube[5][1][0] == cube[5][1][1]) && (cube[5][1][2] == cube[5][1][1]) && (cube[5][2][1] == cube[5][1][1])){
			myRotations = myRotations + "";
			return cube;
		}else if(check(cube)<4){
			int count = 0; 
			main: while(true){ //[Note: I think 'if(check(cube) == 4)' this if block is not usefull because Now we exit from check function so no use of 'break main;' we will exit before this statement executeing compleatly because of User-defined exception statement.] 
				cube = sideMiddleLayer(cube);	//sideMiddleLayer
				if(check(cube) == 4){					// add "if block" 25-dec-2020  --dvspandey
					//System.out.println("\nSteps for White Cross at button.."); //Debug_code
					break main;
				}
				cube = down(cube);				//down
				if(check(cube) == 4){					// add "if block" 25-dec-2020  --dvspandey
					//System.out.println("\nSteps for White Cross at button.."); //Debug_code
					break main;
				}
				cube = sideTopLayer(cube);		//sideTopLayer
				if(check(cube) == 4){					// add "if block" 25-dec-2020  --dvspandey
					//System.out.println("\nSteps for White Cross at button.."); //Debug_code
					break main;
				}
				cube = sideButtomLayer(cube);	//sideButtomLayer
				if(check(cube) == 4){					// add "if block" 25-dec-2020  --dvspandey
					//System.out.println("\nSteps for White Cross at button.."); //Debug_code
					break main;
				}
				cube = elsePart(cube);			//elsePart
				if(check(cube)==4){ //[Note: I think 'if(check(cube) == 4)' this if block is not usefull because Now we exit from check function so no use of 'break main;' we automatically break early with User-defined exception statement ] 
					//System.out.println("Exit while loop");	//Debug_code
					//System.out.println("\nSteps for White Cross at button..");	//Debug_code
					break main;
				}

				if(count==3){	// after 3rd time it will gives Repeated Face
					myRotations = myRotations + " ERROR:dvs-Step1-00: CUBE Defected....";
					return cube;
				}
					
				//System.out.println("Main while loop Running..."); //Debug_code
				cube = justBehindSide(cube); // add "for rare Case-(when Main while loop compleate 1 itteration but not get expected cube)" 25-dec-2020 --dvspandey
				count++;
			}
		}// daisy compleated yellow with white


/* Commented on 25-dec-2020 with adding UserDefined exceptions for Stoppig Recursion
		//Step 1.2  [make white cross at buttom with matching center with edges]
		if(check(cube) == 4){
			cube = up(cube);
		}
*/
		
		//System.out.println("Step1 Compleated..."); //[Note: I think 'if(check(cube) == 4)' this if block is not usefull because Now we exit from check function so no use of 'break main;' we will exit before this statement executeing because of User-defined exception statement.] 
		return cube; //Never executed !! because of User-defined exception statement in check function.
	}//end step1Main method





	// Check Function
	int check(char[][][] cube)throws MyException{
		int count = 0;
		
		if(cube[5][1][1] == cube[0][0][1]){
			count++;
		}else{
			//System.out.println("Change It -------> cube[0][0][1]: "+cube[0][0][1]); //Debug_code
		}

		if(cube[5][1][1] == cube[0][1][0]){
			count++;
		}else{
			//System.out.println("Change It -------> cube[0][1][0]: "+cube[0][1][0]); //Debug_code
		}
		
		if(cube[5][1][1] == cube[0][1][2]){
			count++;
		}else{
			//System.out.println("Change It -------> cube[0][1][2]: "+cube[0][1][2]); //Debug_code
		}

		if(cube[5][1][1] == cube[0][2][1]){
			count++;
		}else{
			//System.out.println("Change It -------> cube[0][2][1]: "+cube[0][2][1]); //Debug_code
		}
		
		if(count == 4){
			//System.out.println(myRotations);
			throw new MyException(cube); // User defined exception for break Recursion if got expected cube!!		
		}
		
		if(count>value){
			value = count;
			//System.out.println("\n step1Main Again! \n");
			//System.out.println("U");
			cube = UpFlip.flip(cube); 
			myRotations = myRotations + " U";
			cube = step1Main(cube);
			count = check(cube);
		}
		return count;
	}//check



/*-----------------------------------------------------------------------------------------*/
	/*
	> SIDE
	  ====	
	middle layer
	------------
		> if white in [(1,2,3,4)][1][(0,2)] then use rotation
			> if [1][1][0] then L'  & check 
			> if [1][1][2] then R	& check 

			> if [2][1][0] then F' 	& check 
			> if [2][1][2] then B	& check 

			>  if [3][1][0] then R'	& check 
			>  if [3][1][2] then L 	& check 

			>  if [4][1][0] then B'	& check 
			>  if [4][1][2] then F	& check 

	*/
	char[][][] sideMiddleLayer(char[][][] cube)throws MyException{

		if(check(cube) == 4){		// add "if block" 25-dec-2020 --dvspandey
				return cube;
		}

		if(cube[5][1][1] == cube[1][1][0] && cube[5][1][1] != cube[0][1][0]){
			//L'
			cube = LeftFlip.flipCounter(cube);
			myRotations = myRotations + " L'";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[1][1][2] && cube[5][1][1] != cube[0][1][2]){
			//R
			cube = RightFlip.flip(cube);
			myRotations = myRotations + " R";
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[2][1][0] && cube[5][1][1] != cube[0][2][1]){
			//F'
			cube = FrontFlip.flipCounter(cube);
			myRotations = myRotations + " F'";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[2][1][2] && cube[5][1][1] != cube[0][0][1]){
			//B
			cube = BackFlip.flip(cube);
			myRotations = myRotations + " B";
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[3][1][0] && cube[5][1][1] != cube[0][1][2]){
			//R'
			cube = RightFlip.flipCounter(cube);
			myRotations = myRotations + " R'";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[3][1][2] && cube[5][1][1] != cube[0][1][0]){
			//L
			cube = LeftFlip.flip(cube);
			myRotations = myRotations + " L";
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[4][1][0]  && cube[5][1][1] != cube[0][0][1]){
			//B'
			cube = BackFlip.flipCounter(cube);
			myRotations = myRotations + " B'";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[4][1][2] && cube[5][1][1] != cube[0][2][1]){
			//F
			cube = FrontFlip.flip(cube);
			myRotations = myRotations + " F";
			if(check(cube) == 4){
				return cube;
			}
		}

		return cube;
	}// end sideMiddleLayer



/*-----------------------------------------------------------------------------------------*/
	/*
	_______________________________________________________________________________ 
	> DOWN																			|
	  ----										 									|
																					|
	> if white found down {501,510,512,521} then rotate 180*						| 
		> if white found [5][2][1] then 2(F/F')		note: /   ----> OR				| 
		> if white found [5][1][0] then 2(R/R')										| 
		> if white found [5][0][1] then 2(B/B')										| 
		> if white found [5][1][2] then 2(L/L')										| 
																					| 
	________________________________________________________________________________| 
	*/
	char[][][] down(char[][][] cube)throws MyException{
		
		if(check(cube) == 4){			// add "if block" 25-dec-2020 --dvspandey
				return cube;
		}

		if(cube[5][1][1] == cube[5][2][1] && cube[5][1][1] != cube[0][2][1]){
			//2(F/F')
			cube = FrontFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			myRotations = myRotations + " FF";
			if(check(cube) == 4){
				return cube;
			}                   
		}
		if(cube[5][1][1] == cube[5][1][0] && cube[5][1][1] != cube[0][1][2]){
			//2(R/R')
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			myRotations = myRotations + " RR";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[5][0][1] && cube[5][1][1] != cube[0][0][1]){
			//2(B/B')	
			cube = BackFlip.flip(cube);
			cube = BackFlip.flip(cube);
			myRotations = myRotations + " BB";
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[5][1][2] && cube[5][1][1] != cube[0][1][0]){
			//2(L/L')
			cube = LeftFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			myRotations = myRotations + " LL";
			if(check(cube) == 4){
				return cube;
			}
		}

		return cube;
	} //end down



/*-----------------------------------------------------------------------------------------*/
	/*
	top layer
	--------- (101,201,301,401)

		> if white found [1][0][1] then FR/F'L'  
		> if white found [2][0][1] then RB/R'F'
		> if white found [3][0][1] then BL/B'R'
		> if white found [4][0][1] then LF/L'B'

	*/
	char[][][] sideTopLayer(char[][][] cube)throws MyException{ // updated on 28-dec-2020  --dvspandey 
		
		if(check(cube)<4){
			//Face1			
			if(cube[5][1][1] == cube[1][0][1] && cube[5][1][1] != cube[0][1][2]){ 
				//FR/F'L'  ---> [Note : here we using FR so above if condition based on it if in feature you want to use F'L' then you should use "cube[5][1][1] != cube[0][1][0]"]
				cube = FrontFlip.flip(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " F R";
				if(check(cube) == 4){
					return cube;
				}
			}else if(cube[5][1][1] == cube[1][0][1] &&  cube[5][1][1] != cube[0][2][1]){ //add else-if block 'cube[5][1][1] != cube[0][2][1]'      [28-Dec-2020] --dvspandey [we can combine it with if block using || OR statement and remover else-if]
				// f'UL'
				cube = FrontFlip.flipCounter(cube);
				cube = UpFlip.flip(cube); 
				cube = LeftFlip.flipCounter(cube);
				myRotations = myRotations + " F' U L'";
			}
				
			//Face2
			if(cube[5][1][1] == cube[2][0][1] && cube[5][1][1] != cube[0][0][1]){
				//RB/R'F'
				cube = RightFlip.flip(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " R B";
				if(check(cube) == 4){
					return cube;
				}
			}else if(cube[5][1][1] == cube[2][0][1] &&  cube[5][1][1] != cube[0][1][2]){ //add else-if block 'cube[5][1][1] != cube[0][1][2]'      [28-Dec-2020] --dvspandey
				// R'UF'
				cube = RightFlip.flipCounter(cube);
				cube = UpFlip.flip(cube);
				cube = FrontFlip.flipCounter(cube);
				myRotations = myRotations + " R' U F'";
			}

			//Face3
			if(cube[5][1][1] == cube[3][0][1] && cube[5][1][1] != cube[0][1][0]){
				//BL/B'R'
				cube = BackFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " B L";
				if(check(cube) == 4){
					return cube;
				}
			}else if(cube[5][1][1] == cube[3][0][1] &&  cube[5][1][1] != cube[0][0][1]){  //add else-if block 'cube[5][1][1] != cube[0][0][1]'      [28-Dec-2020] --dvspandey
				// B'UR'
				cube = BackFlip.flipCounter(cube);
				cube = UpFlip.flip(cube);
				cube = RightFlip.flipCounter(cube);
				myRotations = myRotations + " B' U R'";
			}

			//Face4
			if(cube[5][1][1] == cube[4][0][1] && cube[5][1][1] != cube[0][2][1]){
				//LF/L'B'
				cube = LeftFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " L F";
				if(check(cube) == 4){
					return cube;
				}
			}else if(cube[5][1][1] == cube[4][0][1] &&  cube[5][1][1] != cube[0][1][0]){  //add else-if block 'cube[5][1][1] != cube[0][1][0]'      [28-Dec-2020] --dvspandey
				// L'UB'
				cube = LeftFlip.flipCounter(cube);
				cube = UpFlip.flip(cube);
				cube = BackFlip.flipCounter(cube);
				myRotations = myRotations + " L' U B'";
			}
		}else{
			//System.out.println("sideTopLayer Return! "); //Debug_code
		}

		return cube;
	}// end sideTopLayer



/*-----------------------------------------------------------------------------------------*/
	/*
	bottom layer
	--------- (121,221,321,421)

			buttom layer go top daisy which need now		buttom layer go buttom daisy which no need now 
		> if white found [1][2][1] then F'R/FL'		|	> if white found [1][2][1] then F'R'/FL        
		> if white found [2][2][1] then R'B/RF'		|	> if white found [2][2][1] then R'B'/RF        
		> if white found [3][2][1] then B'L/BR'		|	> if white found [3][2][1] then B'L'/BR        
		> if white found [4][2][1] then L'F/LB'		|	> if white found [4][2][1] then L'F'/LB        

	*/
	char[][][] sideButtomLayer(char[][][] cube)throws MyException{
		
		if(check(cube)<4){
			if(cube[5][1][1] == cube[1][2][1] && cube[5][1][1] != cube[0][1][2]){
				//F'R/FL'
				cube = FrontFlip.flipCounter(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " F' R";
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[2][2][1] && cube[5][1][1] != cube[0][0][1]){
				//R'B/RF'
				cube = RightFlip.flipCounter(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " R' B";
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[3][2][1] && cube[5][1][1] != cube[0][1][0]){
				//B'L/BR'
				cube = BackFlip.flipCounter(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " B' L";
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[4][2][1] && cube[5][1][1] != cube[0][2][1]){
				//L'F/LB'	
				cube = LeftFlip.flipCounter(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " L' F";
				if(check(cube) == 4){
					return cube;
				}
			}
		}else{
			//System.out.println("sideButtomLayer Return! "); //Debug_code
		}

		return cube;
	}//end sideButtomLayer


/*-----------------------------------------------------------------------------------------*/
	/*
	else part
	=========
	3 white get their appropriate position but one place not find its white then read that
	location[001/010/012/021] where not have white and then check 'after rotate UP'  
	get one of above place and check [(110/112)/(210/212)/(310/312)/(410/412)]

		unfounded
		> 001	after rotate 
			> 410 	------> B' 
			> 212	------> B
		> 010
			> 110 	------> L'  
			> 312 	------> L 
		> 012*
			> 310 	------> R'  
			> 112 	------> R
		> 021
			> 210 	------> F'
			> 412 	------> F
	*/
	char[][][] elsePart(char[][][] cube)throws MyException{
		int count= 0;

		elsePart:while(true){
			
			if(check(cube) == 4 || check(cube) < 3){			// add "if block" 25-dec-2020 --dvspandey
				return cube;
			
			}else if(3 == check(cube)){		// add "convert all child(if-if) into child(else-if) + check 001 position also" 25-dec-2020 --dvspandey
				//System.out.println("I'm inside Else Part"); //Debug_code
				//cube = UpFlip.flip(cube);   // add "For Rotate [0] face before checking" 25-dec-2020 --dvspandey
				//System.out.println("U");
				if(cube[5][1][1] != cube[0][0][1]){
					if(cube[5][1][1] == cube[4][1][0]){
						//B' then Check	
						cube = BackFlip.flipCounter(cube);
						myRotations = myRotations + " B'";
						if(check(cube)==4){
							return cube;
						}
																
					}else if(cube[5][1][1] == cube[2][1][2]){
						//B then Check	
						cube = BackFlip.flip(cube);
						myRotations = myRotations + " B";
						if(check(cube)==4){
							return cube;
						}
					}
				}

				
				if(cube[5][1][1] != cube[0][1][0]){
					if(cube[5][1][1] == cube[1][1][0]){
						//L' then Check	
						cube = LeftFlip.flipCounter(cube);
						myRotations = myRotations + " L'";
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[3][1][2]){
						//L then Check	
						cube = LeftFlip.flip(cube);
						myRotations = myRotations + " L";
						if(check(cube)==4){
							return cube;
						}
					}
				}


				if(cube[5][1][1] != cube[0][1][2]){
					if(cube[5][1][1] == cube[3][1][0]){
						//R' then Check	return cube	
						cube = RightFlip.flipCounter(cube);
						myRotations = myRotations + " R'";
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[1][1][2]){
						//R then Check return cube
						cube = RightFlip.flip(cube);
						myRotations = myRotations + " R";
						if(check(cube)==4){
							return cube;
						}
					}
				}


				if(cube[5][1][1] != cube[0][2][1]){
					if(cube[5][1][1] == cube[2][1][0]){
						//F' then Check	return cube	
						cube = FrontFlip.flipCounter(cube);
						myRotations = myRotations + " F'";
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[4][1][2]){
						//F then Check then return cube	
						cube = FrontFlip.flip(cube);
						myRotations = myRotations + " F";
						if(check(cube)==4){
							return cube;
						}
					}
				}


			}
			if(3 == check(cube)){
				
				cube = justBehindSide(cube);
				if(check(cube)==4){
					//System.out.println("\njustBehindSide i done!!\n"); //Debug_code
					return cube;
				}
				//U/U' rotate UP
				cube = UpFlip.flip(cube);
				myRotations = myRotations + " U";
				count++;
				if(count==3)
					break elsePart;
				//System.out.println("[elsePart] Continued..."); //Debug_code
			}else{
				//System.out.println("Return! "); //Debug_code
				break elsePart;
			}
		}


		return cube;
	} // End elsePart method



	// ************************************* justBehindSide ************************************************
	// This Method for: if any time we place 3 White with good position but one is Just Behain face(0) <------> face(5)
	// then we can use to take it its correct position
	char[][][] justBehindSide(char[][][] cube)throws MyException{
		
		// first check if same colour('W') corrospondinf face(0) <----> face(5) 
		//then find non 'W' in(Left,right) in face(0) if get then prform operation
	
		// 021
		if(cube[5][1][1] == cube[0][2][1] && cube[0][2][1] == cube[5][2][1]){
			if(cube[5][1][1] != cube[0][1][2]){
				//D2(R/R') then check == 4 then return
				cube = DownFlip.flip(cube);
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " D RR";
			
			}else if(cube[5][1][1] != cube[0][0][1]){		// add "convert child(if-if) into child(else-if) + check 001 position also" 25-dec-2020 --dvspandey
				cube = DownFlip.flip(cube);
				cube = DownFlip.flip(cube);
				cube = BackFlip.flip(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " DD BB";
			
			}else if(cube[5][1][1] != cube[0][1][0]){
				//D'2(L/L') then check == 4 then return
				cube = DownFlip.flipCounter(cube);  // or 3times cube = DownFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " D' LL";
			}
	
		}
					if(check(cube)==4)			// add "if block" 25-dec-2020  --dvspandey
						return cube;		//[ "For Version Change"  Note: for next version we can also check to remove all "if(check(cube)==4)" block and convert parent(if-if) into parent(else-if) ]
		
		// 010
		if(cube[5][1][1] == cube[0][1][0] && cube[0][1][0] == cube[5][1][2]){
			if(cube[5][1][1] != cube[0][2][1]){
				//D2(F/F') then check == 4 then return
				cube = DownFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " D FF";
			
			}else if(cube[5][1][1] != cube[0][1][2]){
				cube = DownFlip.flip(cube);
				cube = DownFlip.flip(cube);
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " DD RR";
	
			}else if(cube[5][1][1] != cube[0][0][1]){
				//D'2(B/B') then check == 4 then return
				cube = DownFlip.flipCounter(cube);
				cube = BackFlip.flip(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " D' BB";
			}
		}
					if(check(cube)==4)			// add "if block" 25-dec-2020  --dvspandey
						return cube;
		
		// 001
		if(cube[5][1][1] == cube[0][0][1] && cube[0][0][1] == cube[5][0][1]){
			if(cube[5][1][1] != cube[0][1][0]){
				//D2(L/L') then check == 4 then return
				cube = DownFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " D LL";
			
			}else if(cube[5][1][1] != cube[0][2][1]){
				cube = DownFlip.flip(cube);
				cube = DownFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " DD FF";
	
			}else if(cube[5][1][1] != cube[0][1][2]){
				//D'2(R/R') then check == 4 then return
				cube = DownFlip.flipCounter(cube);
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				myRotations = myRotations + " D' RR";
			}
		}
					if(check(cube)==4)			// add "if block" 25-dec-2020  --dvspandey
						return cube;
	    
		// 012
		if(cube[5][1][1] == cube[0][1][2] && cube[0][1][2] == cube[5][1][0]){
			if(cube[5][1][1] != cube[0][0][1]){
				//D2(B/B') then check == 4 then return
				cube = DownFlip.flip(cube);
				cube = BackFlip.flip(cube);
				cube = BackFlip.flip(cube);
				myRotations = myRotations + " D BB";
			
			}else if(cube[5][1][1] != cube[0][1][0]){
				cube = DownFlip.flip(cube);
				cube = DownFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				myRotations = myRotations + " DD LL";
			}else if(cube[5][1][1] != cube[0][2][1]){
				//D'2(F/F') then check == 4 then return
				cube = DownFlip.flipCounter(cube);
				cube = FrontFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				myRotations = myRotations + " D' FF";
			}
		}
	
		return cube;
	
	}// end method justBehindSide

}//Step1



//********************************************** Exception class  ****************************************************************


class MyException extends Exception{ 
	// for Suppress warning
	private static final long serialVersionUID = 1L;
//adding UserDefined exceptions for Stopping Recursion and sending expected cube to caller // 25-dec-2020 --dvspandey
 
 char[][][] cube;
 MyException(char[][][] cube){
   this.cube = cube;
 }

 public String toString(){
   return ("dvs-Step1-02: White Cross at butttom Ready! :) ") ;
}
public char[][][] cube(){
	return cube;
} 

}




//*************************************************************************************************************************************



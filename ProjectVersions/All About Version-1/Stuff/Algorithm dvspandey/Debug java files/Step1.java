/*
	> In place of charecter eg: 'W'  we can use fix values eg: cube[z][1][1]
	> Never executed means == [Note: I think 'if(check(cube) == 4)' this if block is not usefull because Now we exit from check function so no use of 'break main;' we will exit before this statement executeing compleatly because of User-defined exception statement.] 
															[OR]
									[function will terminate before executing this statement completely, because where-ever we call check function it will become recurcive call so Before we getting 'return answer' from check funtion it will finding correct[or expected] result of step1 Class and when check function get expected result of Step1 class it will take that perfect cube refrence and throws a user-defined exception with "cube refrence" and terminated its execution. 
									After that the caller of Step1 class will handel that user-defined exception and get cube refrence of white cross at buttom.
									So conclusion is every check function call will do these two thing:
										> Terminate program using user-defined exception after getting correct cube reference,
										> Or going to call again Main function]							
*/
class Step1{
	static int value = 0;
	static char[][][] step1Main(char[][][] cube)throws MyException{
		if((cube[5][0][1] == cube[5][1][1]) && (cube[5][1][0] == cube[5][1][1]) && (cube[5][1][2] == cube[5][1][1]) && (cube[5][2][1] == cube[5][1][1])){
			System.out.println(" > > > > < Already white Cross at buttom. > < < < < <");
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
					System.out.println("\nERROR:\ndvs-Step1-00: CUBE Defected....");
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
	static int check(char[][][] cube)throws MyException{
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
			//System.out.println("Stop immidiatly sir! count become 4 daisy compleated"); //Debug_code
			System.out.println("Rotate 180* with matching edges + center!");
			//Step 1.2  [make white cross at buttom with matching center with edges]			
			cube = up(cube);
			throw new MyException(cube); // User defined exception for break Recursion if got expected cube!!		
		}
		
		if(count>value){
			value = count;
			//System.out.println("\n step1Main Again! \n");
			System.out.println("U");
			cube = UpFlip.flip(cube); 
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
	static char[][][] sideMiddleLayer(char[][][] cube)throws MyException{

		if(check(cube) == 4){		// add "if block" 25-dec-2020 --dvspandey
				return cube;
		}

		if(cube[5][1][1] == cube[1][1][0] && cube[5][1][1] != cube[0][1][0]){
			//L'
			cube = LeftFlip.flipCounter(cube);
			System.out.println("L'");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[1][1][2] && cube[5][1][1] != cube[0][1][2]){
			//R
			cube = RightFlip.flip(cube);
			System.out.println("R");
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[2][1][0] && cube[5][1][1] != cube[0][2][1]){
			//F'
			cube = FrontFlip.flipCounter(cube);
			System.out.println("F'");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[2][1][2] && cube[5][1][1] != cube[0][0][1]){
			//B
			cube = BackFlip.flip(cube);
			System.out.println("B");
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[3][1][0] && cube[5][1][1] != cube[0][1][2]){
			//R'
			cube = RightFlip.flipCounter(cube);
			System.out.println("R'");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[3][1][2] && cube[5][1][1] != cube[0][1][0]){
			//L
			cube = LeftFlip.flip(cube);
			System.out.println("L");
			if(check(cube) == 4){
				return cube;
			}
		}


		if(cube[5][1][1] == cube[4][1][0]  && cube[5][1][1] != cube[0][0][1]){
			//B'
			cube = BackFlip.flipCounter(cube);
			System.out.println("B'");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[4][1][2] && cube[5][1][1] != cube[0][2][1]){
			//F
			cube = FrontFlip.flip(cube);
			System.out.println("F");
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
	static char[][][] down(char[][][] cube)throws MyException{
		
		if(check(cube) == 4){			// add "if block" 25-dec-2020 --dvspandey
				return cube;
		}

		if(cube[5][1][1] == cube[5][2][1] && cube[5][1][1] != cube[0][2][1]){
			//2(F/F')
			cube = FrontFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			System.out.println("2F");
			if(check(cube) == 4){
				return cube;
			}                   
		}
		if(cube[5][1][1] == cube[5][1][0] && cube[5][1][1] != cube[0][1][2]){
			//2(R/R')
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			System.out.println("2R");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[5][0][1] && cube[5][1][1] != cube[0][0][1]){
			//2(B/B')	
			cube = BackFlip.flip(cube);
			cube = BackFlip.flip(cube);
			System.out.println("2B");
			if(check(cube) == 4){
				return cube;
			}
		}
		if(cube[5][1][1] == cube[5][1][2] && cube[5][1][1] != cube[0][1][0]){
			//2(L/L')
			cube = LeftFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			System.out.println("2L");
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
	static char[][][] sideTopLayer(char[][][] cube)throws MyException{
		
		if(check(cube)<4){
			if(cube[5][1][1] == cube[1][0][1] && cube[5][1][1] != cube[0][1][2]){
				//FR/F'L'  ---> [Note : here we using FR so above if condition based on it if in feature you want to use F'L' then you should use "cube[5][1][1] != cube[0][1][0]"]
				cube = FrontFlip.flip(cube);
				cube = RightFlip.flip(cube);
				System.out.println("FR");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[2][0][1] && cube[5][1][1] != cube[0][0][1]){
				//RB/R'F'
				cube = RightFlip.flip(cube);
				cube = BackFlip.flip(cube);
				System.out.println("RB");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[3][0][1] && cube[5][1][1] != cube[0][1][0]){
				//BL/B'R'
				cube = BackFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				System.out.println("BL");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[4][0][1] && cube[5][1][1] != cube[0][2][1]){
				//LF/L'B'
				cube = LeftFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				System.out.println("LF");
				if(check(cube) == 4){
					return cube;
				}
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
	static char[][][] sideButtomLayer(char[][][] cube)throws MyException{
		
		if(check(cube)<4){
			if(cube[5][1][1] == cube[1][2][1] && cube[5][1][1] != cube[0][1][2]){
				//F'R/FL'
				cube = FrontFlip.flipCounter(cube);
				cube = RightFlip.flip(cube);
				System.out.println("F'R");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[2][2][1] && cube[5][1][1] != cube[0][0][1]){
				//R'B/RF'
				cube = RightFlip.flipCounter(cube);
				cube = BackFlip.flip(cube);
				System.out.println("R'B");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[3][2][1] && cube[5][1][1] != cube[0][1][0]){
				//B'L/BR'
				cube = BackFlip.flipCounter(cube);
				cube = LeftFlip.flip(cube);
				System.out.println("B'L");
				if(check(cube) == 4){
					return cube;
				}
			}
			if(cube[5][1][1] == cube[4][2][1] && cube[5][1][1] != cube[0][2][1]){
				//L'F/LB'	
				cube = LeftFlip.flipCounter(cube);
				cube = FrontFlip.flip(cube);
				System.out.println("L'F");
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
	static char[][][] elsePart(char[][][] cube)throws MyException{
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
						System.out.println("B'");
						if(check(cube)==4){
							return cube;
						}
																
					}else if(cube[5][1][1] == cube[2][1][2]){
						//B then Check	
						cube = BackFlip.flip(cube);
						System.out.println("B");
						if(check(cube)==4){
							return cube;
						}
					}
				}

				
				if(cube[5][1][1] != cube[0][1][0]){
					if(cube[5][1][1] == cube[1][1][0]){
						//L' then Check	
						cube = LeftFlip.flipCounter(cube);
						System.out.println("L'");
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[3][1][2]){
						//L then Check	
						cube = LeftFlip.flip(cube);
						System.out.println("L");
						if(check(cube)==4){
							return cube;
						}
					}
				}


				if(cube[5][1][1] != cube[0][1][2]){
					if(cube[5][1][1] == cube[3][1][0]){
						//R' then Check	return cube	
						cube = RightFlip.flipCounter(cube);
						System.out.println("R'");
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[1][1][2]){
						//R then Check return cube
						cube = RightFlip.flip(cube);
						System.out.println("R");
						if(check(cube)==4){
							return cube;
						}
					}
				}


				if(cube[5][1][1] != cube[0][2][1]){
					if(cube[5][1][1] == cube[2][1][0]){
						//F' then Check	return cube	
						cube = FrontFlip.flipCounter(cube);
						System.out.println("F'");
						if(check(cube)==4){
							return cube;
						}
					}else if(cube[5][1][1] == cube[4][1][2]){
						//F then Check then return cube	
						cube = FrontFlip.flip(cube);
						System.out.println("F");
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
				System.out.println("U");
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


/*-----------------------------------------------------------------------------------------*/
	/*-------------------------------------- UP -----------------------------------------------*/
	/*		
	> UP	(Develop a White Cross at Buttom on Cube)
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


	static char[][][] up(char[][][] cube)throws MyException{
		//{001,010,012,021}
	
		int count = 0;

		do
		{
			//for Face 1
			if(cube[5][1][1]  == cube[0][2][1] && cube[1][1][1] == cube[1][0][1]){
				//rotate 2(F/F')	
				cube = FrontFlip.flip(cube);
				cube = FrontFlip.flip(cube);
				System.out.println("2F");
			}
			
			
			//for Face 2
			if(cube[5][1][1]  == cube[0][1][2] && cube[2][1][1] == cube[2][0][1]){
				//rotate 2(R/R')
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				System.out.println("2R");
			}

			//for Face 3
			if(cube[5][1][1]  == cube[0][0][1] && cube[3][1][1] == cube[3][0][1]){
				//rotate 2(B/B')	
				cube = BackFlip.flip(cube);
				cube = BackFlip.flip(cube);
				System.out.println("2B");	
			}
			
			
			//for Face 4
			if(cube[5][1][1]  == cube[0][1][0] && cube[4][1][1] == cube[4][0][1]){
				//rotate 2(L/L')
				cube = LeftFlip.flip(cube);
				cube = LeftFlip.flip(cube);
				System.out.println("2L");
			}

			// Rotate Top 90
			if(count<3){
				cube = UpFlip.flip(cube);
				System.out.println("U");
			}
			count++;
			
		}
		while(count<=3);
		
		if(cube[5][1][1] == cube[5][0][1] && cube[5][1][1] == cube[5][1][0] && cube[5][1][1] == cube[5][1][2] && cube[5][1][1] == cube[5][2][1]){
			System.out.println("White CROSS at buttom, Success....");
		}else{

			System.out.println(" CUBE is defected [OR] Make sure Daisy compleated!");
			System.out.println("ERROR:\ndvs-Step1-01 White CROSS at buttom, UnSuccess....");
			System.out.println("\ncube[5][1][1]: "+cube[5][1][1]);
			System.out.println("cube[5][0][1]: "+cube[5][0][1]);
			System.out.println("cube[5][1][0]: "+cube[5][1][0]);
			System.out.println("cube[5][1][2]: "+cube[5][1][2]);
			System.out.println("cube[5][2][1]: "+cube[5][2][1]);
		}

		return cube;
	} // End up Method






// ************************************* justBehindSide ************************************************
// This Method for: if any time we place 3 White with good position but one is Just Behain face(0) <------> face(5)
// then we can use to take it its correct position
static char[][][] justBehindSide(char[][][] cube)throws MyException{
	
	// first check if same colour('W') corrospondinf face(0) <----> face(5) 
	//then find non 'W' in(Left,right) in face(0) if get then prform operation

	// 021
	if(cube[5][1][1] == cube[0][2][1] && cube[0][2][1] == cube[5][2][1]){
		if(cube[5][1][1] != cube[0][1][2]){
			//D2(R/R') then check == 4 then return
			cube = DownFlip.flip(cube);
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			System.out.println("DRR");
		
		}else if(cube[5][1][1] != cube[0][0][1]){		// add "convert child(if-if) into child(else-if) + check 001 position also" 25-dec-2020 --dvspandey
			cube = DownFlip.flip(cube);
			cube = DownFlip.flip(cube);
			cube = BackFlip.flip(cube);
			cube = BackFlip.flip(cube);
			System.out.println("DDBB");
		
		}else if(cube[5][1][1] != cube[0][1][0]){
			//D'2(L/L') then check == 4 then return
			cube = DownFlip.flipCounter(cube);  // or 3times cube = DownFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			System.out.println("D'LL");
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
			System.out.println("DFF");
		
		}else if(cube[5][1][1] != cube[0][1][2]){
			cube = DownFlip.flip(cube);
			cube = DownFlip.flip(cube);
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			System.out.println("DDRR");

		}else if(cube[5][1][1] != cube[0][0][1]){
			//D'2(B/B') then check == 4 then return
			cube = DownFlip.flipCounter(cube);
			cube = BackFlip.flip(cube);
			cube = BackFlip.flip(cube);
			System.out.println("D'BB");
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
			System.out.println("DLL");
		
		}else if(cube[5][1][1] != cube[0][2][1]){
			cube = DownFlip.flip(cube);
			cube = DownFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			System.out.println("DDFF");

		}else if(cube[5][1][1] != cube[0][1][2]){
			//D'2(R/R') then check == 4 then return
			cube = DownFlip.flipCounter(cube);
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			System.out.println("D'RR");
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
			System.out.println("DBB");
		
		}else if(cube[5][1][1] != cube[0][1][0]){
			cube = DownFlip.flip(cube);
			cube = DownFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			cube = LeftFlip.flip(cube);
			System.out.println("DDLL");
		}else if(cube[5][1][1] != cube[0][2][1]){
			//D'2(F/F') then check == 4 then return
			cube = DownFlip.flipCounter(cube);
			cube = FrontFlip.flip(cube);
			cube = FrontFlip.flip(cube);
			System.out.println("D'FF");
		}
	}

	return cube;

}// end method justBehindSide





}// end Step1 class


//********************************************** Exception class  ****************************************************************


class MyException extends Exception{ //adding UserDefined exceptions for Stoppig Recursion and sending expected cube to caller // 25-dec-2020 --dvspandey
   
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






/*  ********************************* OLD LOGIC White Cross at BUTTOM ***************
						// Faild Logic

	static char[][][] up(char[][][] cube){
		//{001,010,012,021}
		int count = 0;
		while(true){ // for face 1
			if(cube[5][1][1]  == cube[0][2][1]){
				if(cube[1][1][1] == cube[1][0][1]){
					//rotate 2(F/F')	
					cube = FrontFlip.flip(cube);
					cube = FrontFlip.flip(cube);
					System.out.println("2F");
					break;
				}else if(count==4){
						System.out.println("Not found edge btw correct centers!!\n\t something went Wrong.");
						break;
				}else{
					// U/U' 
					cube = UpFlip.flip(cube);
					System.out.println("U");
					count++;
				}
			}else{
				System.out.println("Daisy not found 1");				
			}
		}

		count = 0;
		while(true){ // for face 2
			if(cube[5][1][1]  == cube[0][1][2]){
				if(cube[2][1][1] == cube[2][0][1]){
					//rotate 2(R/R')
					cube = RightFlip.flip(cube);
					cube = RightFlip.flip(cube);
					System.out.println("2R");
					break;
				}else if(count==4){
						System.out.println("Not found edge btw correct centers!!\n\t something went Wrong.");
						break;
				}else{
					// U/U'
					cube = UpFlip.flip(cube);
					System.out.println("U");
					count++;
				}
			}else{
				System.out.println("Daisy not found 2");
				break;
			}
		}

		count = 0;
		while(true){ // for face 3
			if(cube[5][1][1]  == cube[0][0][1]){
				if(cube[3][1][1] == cube[3][0][1]){
					//rotate 2(B/B')	
					cube = BackFlip.flip(cube);
					cube = BackFlip.flip(cube);
					System.out.println("2B");
					break;
				}else if(count==4){
						System.out.println("Not found edge btw correct centers!!\n\t something went Wrong.");
						break;
				}else{
					// U/U'
					cube = UpFlip.flip(cube);
					System.out.println("U");
					count++;
				}
			}else{
				System.out.println("Daisy not found 3");
				break;
			}
		}

		count = 0;
		while(true){ // for face 4
			if(cube[5][1][1]  == cube[0][1][0]){
				if(cube[4][1][1] == cube[4][0][1]){
					//rotate 2(L/L')
					cube = LeftFlip.flip(cube);
					cube = LeftFlip.flip(cube);
					System.out.println("2L");
					break;
				}else if(count==4){
						System.out.println("Not found edge btw correct centers!!\n\t something went Wrong.");
						break;
				}else{
					// U/U'
					cube = UpFlip.flip(cube);
					System.out.println("U");
					count++;
				}
			}else{
				System.out.println("Daisy not found 4");
				break;
			}
		}


		return cube;
	} // End up Method

*/

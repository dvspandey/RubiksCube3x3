/*
*
*	Class ---> MiddleFlip
	 (static Methods)
	 > flipH
	 > flipCounterH
	 > flipV
	 > flipCounterV
*-----------------------------> [ Note: here 2 vertical flip and 2 vertical flipcounter
											> Front---Back
											> Right---Left
									but here we make only with respect to 'Front----Back' or Blue or face(1)
									because it full fill our requirement so no need to make and add vertical 'Right---Left' flip,flipcounter]
*
* Flip(clockwise) -------> with respect to [Top & Right].
* FlipCounter(Anti-clockwise) -------> with respect to [Top & Right].
*
*
*
*  Horizontal														*  Vertical                                                   
  ============														  ==========                                                  
	* FlipH:															* FlipV:								* FlipCounterV:   
	 ------																 ------									  ------------    
	 	110 <---- 210 <---- 310 <---- 410 <---- 110						 	001  011  021							001  011  021 
	 	111 <---- 211 <---- 311 <---- 411 <---- 111							 ^	  ^    ^ 							 |	  |    |  
	 	112 <---- 212 <---- 312 <---- 412 <---- 112							 |	  |    | 							 *	  *    *  
																			101	 111  121							101	 111  121 
	* FlipCounterH:															 ^	  ^    ^ 							 |	  |    |  
	 ------																	 |	  |    | 							 *	  *    *  
	 	110 ----> 210 ----> 310 ----> 410 ----> 110							521	 511  501							521	 511  501 
	 	111 ----> 211 ----> 311 ----> 411 ----> 111							 ^	  ^    ^ 							 |	  |    |  
	 	112 ----> 212 ----> 312 ----> 412 ----> 112							 |	  |    | 							 *	  *    *  
																			321	 311  301							321	 311  301 
																			 ^	  ^    ^ 							 |	  |    |  
																			 |	  |    | 							 *	  *    *  
																			001	 011  021							001	 011  021 

* 
*/



class  MiddleFlip{
//>>>>>>>>>>>>>>>>>>>>>>>>>>> Horizontal <<<<<<<<<<<<<<<<<<<<<<<<
	
	static char[][][] flipH(char cube[][][]){ 
				// Middle Flip
		char tempArr[] = new char[3];  
								
		tempArr[0] = cube[1][1][0]; 																					
		tempArr[1] = cube[1][1][1]; 																					
		tempArr[2] = cube[1][1][2]; 																					
																							
		cube[1][1][0] = cube[2][1][0]; 
		cube[1][1][1] = cube[2][1][1]; 																					
		cube[1][1][2] = cube[2][1][2];

		cube[2][1][0] = cube[3][1][0]; 
		cube[2][1][1] = cube[3][1][1]; 																					
		cube[2][1][2] = cube[3][1][2];

		cube[3][1][0] = cube[4][1][0]; 
		cube[3][1][1] = cube[4][1][1]; 																					
		cube[3][1][2] = cube[4][1][2];

		cube[4][1][0] = tempArr[0];
		cube[4][1][1] =	tempArr[1];
		cube[4][1][2] =	tempArr[2];
			
		return cube;
	}// end flipH

//**********************************************************
				// Reverse Middle Flip Horizontal


	static char[][][] flipCounterH(char cube[][][]){ 
				// Middle flipCounter
		char tempArr[] = new char[3];  
								
		tempArr[0] = cube[4][1][0]; 			
		tempArr[1] = cube[4][1][1]; 			
		tempArr[2] = cube[4][1][2]; 																					

		cube[4][1][0] = cube[3][1][0];
		cube[4][1][1] =	cube[3][1][1];			
		cube[4][1][2] =	cube[3][1][2];

		cube[3][1][0] = cube[2][1][0];
		cube[3][1][1] = cube[2][1][1];
		cube[3][1][2] = cube[2][1][2];

		cube[2][1][0] = cube[1][1][0]; 
		cube[2][1][1] = cube[1][1][1]; 			
		cube[2][1][2] = cube[1][1][2];

		cube[1][1][0] = tempArr[0]; 
		cube[1][1][1] = tempArr[1]; 			
		cube[1][1][2] = tempArr[2];

		return cube;
	}// end flipCounterH

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

//>>>>>>>>>>>>>>>>>>>>>>>>>>>> Vertical <<<<<<<<<<<<<<<<<<<<<<<<
	
	static char[][][] flipV(char cube[][][]){ 
				// Middle Flip
		char tempArr[] = new char[3];  
								
		tempArr[0] = cube[0][0][1]; 																					
		tempArr[1] = cube[0][1][1]; 																					
		tempArr[2] = cube[0][2][1]; 																					
																							
		cube[0][0][1] = cube[1][0][1]; 
		cube[0][1][1] = cube[1][1][1]; 																					
		cube[0][2][1] = cube[1][2][1];

		cube[1][0][1] = cube[5][2][1]; 
		cube[1][1][1] = cube[5][1][1]; 																					
		cube[1][2][1] = cube[5][0][1];

		cube[5][2][1] = cube[3][2][1]; 
		cube[5][1][1] = cube[3][1][1]; 																					
		cube[5][0][1] = cube[3][0][1];

		cube[3][2][1] = tempArr[0];
		cube[3][1][1] =	tempArr[1];
		cube[3][0][1] =	tempArr[2];

		return cube;
	}// end flipV

//**********************************************************
				// Reverse Middle Flip Vertical


	static char[][][] flipCounterV(char cube[][][]){ 
				// Middle flipCounter
		char tempArr[] = new char[3];  
								
		tempArr[0] = cube[3][2][1]; 																					
		tempArr[1] = cube[3][1][1]; 																					
		tempArr[2] = cube[3][0][1]; 																					

		cube[3][2][1] = cube[5][2][1];
		cube[3][1][1] =	cube[5][1][1];
		cube[3][0][1] =	cube[5][0][1];
	
		cube[5][2][1] = cube[1][0][1]; 
		cube[5][1][1] = cube[1][1][1]; 																					
		cube[5][0][1] = cube[1][2][1];

		cube[1][0][1] = cube[0][0][1]; 
		cube[1][1][1] = cube[0][1][1]; 																					
		cube[1][2][1] = cube[0][2][1];		

		cube[0][0][1] = tempArr[0]; 
		cube[0][1][1] = tempArr[1]; 																					
		cube[0][2][1] = tempArr[2];

		return cube;
	}// end flipCounterV




}// end MiddleFlip

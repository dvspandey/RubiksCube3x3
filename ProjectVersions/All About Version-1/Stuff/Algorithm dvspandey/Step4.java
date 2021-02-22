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



class Step4{
	static char[][][] step4Main(char[][][] cube){ 
		
		if(cube[0][0][1]==cube[0][1][1] && cube[0][1][0]==cube[0][1][1] && cube[0][1][2]==cube[0][1][1] && cube[0][2][1]==cube[0][1][1]){
			System.out.println("Cube Step4 success....");
			
			return cube;
		}else{
			cube = caller(cube);
		}

		return cube;
	}// end step4Main method

	//*****************************************************************************	
	static char[][][] caller(char[][][] cube){ // No need this Method if we call only once caseSelect() we will get correct o/p 
		for(int i=1;i<5;i++){
			if(cube[0][0][1]==cube[0][1][1] 
				&& cube[0][1][0]==cube[0][1][1] 
				&& cube[0][1][2]==cube[0][1][1] 
				&& cube[0][2][1]==cube[0][1][1]){
					System.out.println("Cube Step4 success....");
					return cube;
			}else{
				cube = caseSelect(cube);
			}
		}System.out.println("Cube Step4 caller Fail....");
		return cube;
	}// end caller method	

	//*****************************************************************************
	static char[][][] caseSelect(char[][][] cube){ 
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
			// call search3Yellow & perform sutable Operation
			cube = search3Yellow(cube);
		}else if(count==5){
			//Already yellow cross Exist
			System.out.println("Cube Step4 Already yellow cross Exist....");
		}else{
			System.out.println("Cube Step4 caseSelect Fail....    count:"+count);
		}
	
		return cube;
	}// end caseSelect method

	//*****************************************************************************
	static char[][][] search3Yellow(char[][][] cube){
		int count = 1;
		if(cube[0][1][0]==cube[0][1][1] && cube[0][1][2]==cube[0][1][1]){
			//cube have horizontal yellow line
			// algo6Step
			cube = algo6Step(cube);
		}else if(cube[0][0][1]==cube[0][1][1] && cube[0][2][1]==cube[0][1][1]){
			//cube have Vertical yellow line
			// U
			cube = UpFlip.flip(cube);
			// algo6Step
			cube = algo6Step(cube);
		}

		while(true)
		{
			if(cube[0][0][1]==cube[0][1][1] && cube[0][1][0]==cube[0][1][1]){
				break;
			}else if(count>4){
				System.out.println("Cube Step4 search3Yellow Fail....");
			}count++;
			// U
			cube = UpFlip.flip(cube);
		}
		cube = algo6Step(cube);
		return cube;
	}// end search3Yellow method


	//*****************************************************************************
	static char[][][] algo6Step(char[][][] cube){
		//FURU'R'F'
				
		cube = FrontFlip.flip(cube);
		cube = UpFlip.flip(cube); 
		cube = RightFlip.flip(cube); 

		cube = UpFlip.flipCounter(cube);
		cube = RightFlip.flipCounter(cube);
		cube = FrontFlip.flipCounter(cube);

		return cube;
	}// end algo6Step method


	//*****************************************************************************

}// end Step4 class


/*
Test case
=========
for case#1(1 yellow)					for case#2(3 yellow)
----------								----------
B W B									'G','B','W'
W Y Y									'R','Y','B'
G O G									'W','Y','G'
													
W Y W												
B B B									'G','O','R'
G R W									'R','B','G'
										'B','Y','R'
O R R												
W R W												
O B O									'W','Y','B'
										'O','R','B'
Y O W									'W','W','O'
R G G												
Y G G												
													
R G R									'O','O','O'
Y O Y									'W','G','R'
R B O									'Y','O','G'
													
B O Y												
R W O									'Y','Y','O'
B G Y									'B','O','G'
										'R','G','R'
												
													
													
										'B','W','Y'
										'R','W','W'
										'B','G','Y'


*/	
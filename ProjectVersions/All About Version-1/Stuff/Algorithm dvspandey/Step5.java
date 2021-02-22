/*
* SOLVE FINAL LAYER – Step 2 Orient the Corners
  ==============================================
> At this stage you have 3 cases on top face(0):
	> 4 non yellow tiles
	> 3 non yellow tiles [look like Fish]
	> 2 non yellow tiles
		> Inline position [pos(x00) have yellow]
		> Digonal position [pos(x00) have yellow]
		
Checking Rules
---------------
	> 4 non yellow tiles
		pos((x-1)02) have yello color OR [if not perform U]

	> 3 non yellow tiles [look like Fish]
		> fish pointing down to left hand side pos(020) have yellow OR [if not perform U]

	> 2 non yellow tiles
		> Inline position pos(000, 020) Not have yellow color && [pos(100) have yellow]
		> Digonal position pos(020, 002) not have yellow color && [pos(100) have yellow]

Solving
=======
> Digonal position [RUR'URU2R']-------> you got Fish [3 non yellow tiles]
	--------> [RUR'URU2R']------> opposite fish ---180* horizontally rotate--> U2Mh2D'2 ------> Fish
	--------> [RUR'URU2R']

*/


class Step5{
	static char[][][] step5Main(char[][][] cube){  

		cube = caseSelect(cube);
		return cube;
	}// end step5Main method



	//*****************************************************************************
	static char[][][] caseSelect(char[][][] cube){ // checking Non-yellow tiles
		int count = 0; 
		if(cube[0][0][0]!=cube[0][1][1]){
			count++;
		}
		if(cube[0][0][2]!=cube[0][1][1]){
			count++;
		}
		if(cube[0][2][0]!=cube[0][1][1]){
			count++;
		}
		if(cube[0][2][2]!=cube[0][1][1]){
			count++;
		}
		

		if(count==4){//Case#1 4 non yellow tiles
			cube = count4(cube);
		}else if(count==3){//Case#2 3 non yellow tiles [look like Fish]
			cube = count3(cube);
		}else if(count==2){//Case#3 2 non yellow tiles
			cube = count2(cube);
		}else if(count==1){
			System.out.println("Error: Cube Step5 caseSelect Fail...."+count);
		}else if(count==0){
			System.out.println("Ready Cube Step5 Done!");
		}
	
		return cube;
	}// end caseSelect method

	//*****************************************************************************

	static char[][][] count2(char[][][] cube){ 
		int count = 1;
		 while(true){
			if(cube[1][0][0]==cube[0][1][1] && cube[0][2][0]!=cube[0][1][1] && cube[0][2][2]!=cube[0][1][1]){
				break;
			}else if(cube[1][0][0]==cube[0][1][1] && cube[0][2][0]!=cube[0][1][1] && cube[0][0][2]!=cube[0][1][1]){ 
				break;
			}else if(cube[1][0][0]==cube[0][1][1] && cube[0][2][0]!=cube[0][1][1] && cube[0][0][0]!=cube[0][1][1]){ 
				break;
			}else if(count>4){ 
				System.out.println("Cube Step5 count2 Fail....");
			}count++;
			// U
			cube = UpFlip.flip(cube);
		}
		//calling algo8Step
		cube = algo8Step(cube);
		//calling caseSelect
		cube = caseSelect(cube);

		return cube;
	}// end count2 method

	//*****************************************************************************
	static char[][][] count3(char[][][] cube){ 
		int count = 1;
		 while(true){
			if(cube[0][2][0]==cube[0][1][1]){
				break;
			}else if(count>4){ 
				System.out.println("Cube Step5 count3 Fail....");
			}count++;
			// U
			cube = UpFlip.flip(cube);
		}
		//calling algo8Step
		cube = algo8Step(cube);
		//calling caseSelect
		cube = caseSelect(cube);

		return cube;
	}// end count3 method

	//*****************************************************************************
	static char[][][] count4(char[][][] cube){ 
		int count = 1;
		 while(true){
			if(cube[4][0][2]==cube[0][1][1]){
				break;
			}else if(count>4){ 
				System.out.println("Cube Step5 count4 Fail....");
			}count++;
			// U
			cube = UpFlip.flip(cube);
		}
		//calling algo8Step
		cube = algo8Step(cube);
		//calling caseSelect
		cube = caseSelect(cube);

		return cube;
	}// end count4 method

	//*****************************************************************************
	static char[][][] algo8Step(char[][][] cube){ // [RUR'URU2R']

		cube = RightFlip.flip(cube);
		cube = UpFlip.flip(cube);
		cube = RightFlip.flipCounter(cube);
		cube = UpFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = UpFlip.flip(cube);
		cube = UpFlip.flip(cube);
		cube = RightFlip.flipCounter(cube);

		return cube;
	}// end algo8Step method


}//Step5



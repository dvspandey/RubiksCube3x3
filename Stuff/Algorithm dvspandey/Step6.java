/*
* Position The Yellow Corners
  ===========================
	> In this step we match up corner pices withoutcenter pices.  
	Case Study
	==========
	> Diagonal Corners
		> twist the up face and match two corners are in the correct position 
			a>	[x][0][0] == [x][1][1] &&  [x-1][0][2] == [x-1][1][1]  ---->(x can be 1 && 3)
																			(x-1 can be 4 && 2)

			b>	[x][0][0] == [x][1][1] &&  [x-1][0][2] == [x-1][1][1]  ---->(x can be 4 && 2)
																			(x-1 can be 1 && 3)
			------------> R'FR'B2RF'R'B2R2

	> Adjacent Corners
		if([x][0][0] == [x][0][2]) then check
			while(true){
				if([x][0][0] == [x][1][1] &&  [x][0][2] == [x][1][1]){	---->(x can be 1 || 2 || 3 || 4)
					break;
				}else{
					U
				}
			}
		then Rotate 180* U2Mh2D'2
		apply Algo
		else check Digonal Corners
*/



class Step6{
	static char[][][] step6Main(char[][][] cube){  

		cube = caseSelect(cube);
		return cube;
	}// end step6Main method


	static char[][][] caseSelect(char[][][] cube){  
		//Case#1 Check cube is Already ready or Not!
		int z;
		for(z=1;z<=4;z++){
			if(cube[z][0][0] == cube[z][0][2] && (cube[z][0][0] != cube[z][1][1] &&  cube[z][0][2] != cube[z][1][1])){
				//U'
				cube = UpFlip.flipCounter(cube);
				z=1;// check again from scratch
				System.out.println("Great dvs....!");
			}else if(cube[z][0][0] != cube[z][0][2] || cube[z][0][0] != cube[z][1][1] ||  cube[z][0][2] != cube[z][1][1]){
				break;
			}else if(z==4){
				System.out.println("Ready Cube Step6 Success....!");
				return cube;
			}
		}//Case#1
		

		//#Case2[Adjacent Corners] If two tiles have same color of same face on top layer with matching center.
		int x;
		for(x=1;x<=4;x++){
			if(cube[x][0][0] == cube[x][0][2]){
				while(true){
					if(cube[x][0][0] == cube[x][1][1] &&  cube[x][0][2] == cube[x][1][1]){//	---->(x can be 1 || 2 || 3 || 4)
						if(x==1){//rotate cube horizontally 180*
							//U2Mh2D'2
							cube = UpFlip.flip(cube); 
							cube = UpFlip.flip(cube); 
							cube = MiddleFlip.flipH(cube);
							cube = MiddleFlip.flipH(cube);
							cube = DownFlip.flipCounter(cube);
							cube = DownFlip.flipCounter(cube);
							
							//Calling algo12Step
							cube = algo12Step(cube);

							//U'
							cube = UpFlip.flipCounter(cube);
							System.out.println("May1 Success....!"); //Debug code
							return cube;
						}else if(x==2){//rotate cube horizontally -90* (Anti-clockwise) 
							//U'Mh'D
							cube = UpFlip.flipCounter(cube);
							cube = MiddleFlip.flipCounterH(cube);
							cube = DownFlip.flip(cube);
							
							//Calling algo12Step
							cube = algo12Step(cube);

							//U'
							cube = UpFlip.flipCounter(cube);
							System.out.println("May2 Success....!"); //Debug code
							return cube;
						}else if(x==3){//rotate 0* (No rotation)  
							//Calling algo12Step
							cube = algo12Step(cube);

							//U'
							cube = UpFlip.flipCounter(cube);
							System.out.println("May3 Success....!"); //Debug code
							return cube;
						}else if(x==4){//rotate cube horizontally 90* (clockwise)
							//UMhD'
							cube = UpFlip.flip(cube);
							cube = MiddleFlip.flipH(cube);
							cube = DownFlip.flipCounter(cube);
							
							//Calling algo12Step
							cube = algo12Step(cube);

							//U'
							cube = UpFlip.flipCounter(cube);
							System.out.println("May4 Success....!"); //Debug code
							return cube;
						}
						break;
					}
					//U
					cube = UpFlip.flip(cube);
					if(x==1){x=4;}else{x--;}
				}//while
			}
		}//#case2 for loop


		//Case#3 [Diagonal Corners] 
		while(true){
			if(cube[1][0][0] == cube[1][1][1] &&  cube[4][0][2] == cube[4][1][1]){//(x can be 1 && 3)
				//rotate cube horizontally -90* (Anti-clockwise)
				//U'Mh'D
				cube = UpFlip.flipCounter(cube);
				cube = MiddleFlip.flipCounterH(cube);
				cube = DownFlip.flip(cube);

				//Calling algo12Step
				cube = algo12Step(cube);
				
				cube = caseSelect(cube);
				break;
			}else if(cube[2][0][0] == cube[2][1][1] &&  cube[1][0][2] == cube[1][1][1]){
				//Calling algo12Step
				cube = algo12Step(cube);

				cube = caseSelect(cube);
				break;
			}
			//U
			cube = UpFlip.flip(cube);
		}
		
		return cube;
	}//caseSelect


	static char[][][] algo12Step(char[][][] cube){ 
		//R'FR'B2 RF'R'B2R2

		cube = RightFlip.flipCounter(cube);
		cube = FrontFlip.flip(cube); 
		cube = RightFlip.flipCounter(cube);
		cube = BackFlip.flip(cube);
		cube = BackFlip.flip(cube);

		cube = RightFlip.flip(cube);
		cube = FrontFlip.flipCounter(cube);
		cube = RightFlip.flipCounter(cube);
		cube = BackFlip.flip(cube);
		cube = BackFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = RightFlip.flip(cube);

		return cube;
	}//algo12Step

}//Step6
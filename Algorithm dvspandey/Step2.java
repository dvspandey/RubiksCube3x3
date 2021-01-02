/*

Yellow	<---upset to----> White 
Blue	<---upset to----> Green
Red		<---upset to----> Orange

> Center pice have onc color which we can touch with single fingure [Total 4]
> Edge pice have two color and we can touch with two fingures or pinch. [Total 12]
> Corner pice have Three color and we can touch with three fingurs. [Total 8]


	Take White center at Top

		cube = RightFlip.flip(cube);
		cube = RightFlip.flip(cube);
		cube = MiddleFlip.flipV(cube);
		cube = MiddleFlip.flipV(cube);
		cube = LeftFlip.flipCounter(cube);
		cube = LeftFlip.flipCounter(cube);

*/



class Step2{
	static boolean flag = true;
	static char[][][] step2Main(char[][][] cube){ 
		if(flag){
			cube = RightFlip.flip(cube);
			cube = RightFlip.flip(cube);
			cube = MiddleFlip.flipV(cube);
			cube = MiddleFlip.flipV(cube);
			cube = LeftFlip.flipCounter(cube);
			cube = LeftFlip.flipCounter(cube);	
			flag = false;
		}

		if(cube[1][0][1]==cube[1][1][1] && cube[2][0][1]==cube[2][1][1] && cube[3][0][1]==cube[3][1][1] && cube[4][0][1]==cube[4][1][1]){
			if((cube[1][0][0]==cube[1][1][1] && cube[2][0][0]==cube[2][1][1] && cube[3][0][0]==cube[3][1][1] && cube[4][0][0]==cube[4][1][1]) && (cube[1][0][2]==cube[1][1][1] && cube[2][0][2]==cube[2][1][1] && cube[3][0][2]==cube[3][1][1] && cube[4][0][2]==cube[4][1][1])){
				System.out.println("Step2 success......!");
				return cube;
			}else{
				System.out.println("Going for Ready!");
				cube = makeReady(cube);
			}
		}else{
			System.out.println("The Cube has not white cross at top! with another faces correct center pices.");
		}
		
		return cube;
	}// end step2Main

	
	static char[][][] makeReady(char[][][] cube){
		if(cube[1][0][2] != cube[1][1][1]){
			//R'D'R
			if(cube[1][2][0] != cube[1][1][1]){
				char color = cube[1][2][0]; 
				int i;
				for(i=1;i<=4;i++){
					if(color == cube[i][1][1]){
						break;
					}
				}
				// i variable has a value which is very useful

				//Down rotation  --- upto cube[x][2][0] == cube[x][1][1]
				int j = 1; //face(1) so j=1
				while(true){ //it will match cube[1][2][0] color with it's matching color center
					//D'
					cube = DownFlip.flipCounter(cube);
					if(cube[j][2][0] == cube[j][1][1]){
						break;
					}
					if(j==1){
						j=4;
					}else{
						j--;
					}
				}
				
				if(i==1){ // it will give that place cube[i][2][2] where the white pice exist which is also in relation with cube[j][2][0]
					i = 4; // or we can say which was with relation with cube[1][2][0] 
				}else{
					i--;
				}
				
				cube = rigntAlgo(cube, i); // Apply Right Algorithm {D'R'DR}

			}
		}



		return cube;
	} // end makeReady


	static char[][][] rigntAlgo(char[][][] cube,int i){
		
		if(i==1 && cube[1][2][2] == cube[0][1][1]){// Apply Right Algorithm {D'R'DR}
					//D'R'DR
					if(cube[1][0][2] == cube[1][1][1] && cube[2][0][0] == cube[2][1][1]){ //Debug code Blue pos(1) match with Blue center && Orange match pos(3) with Orange center
						System.out.println("Right algo Success...face(1)");
					}
				}else if(i==2 && cube[2][2][2] == cube[0][1][1]){
					//D'B'DB
					if(cube[2][0][2] == cube[2][1][1] ){ //Debug code
						System.out.println("Right algo Success...face(2)");
					}
				}else if(i==3 && cube[3][2][2] == cube[0][1][1]){
					//D'L'DL
					if(cube[3][0][2] == cube[3][1][1]){ //Debug code
						System.out.println("Right algo Success...face(3)");
					}
				}else if(i==4 && cube[4][2][2] == cube[0][1][1]){
					//D'F'DF
					if(cube[4][0][2] == cube[4][1][1]){ //Debug code
						System.out.println("Right algo Success...face(4)");
					}
				}else{
					//Debug code
					System.out.println("Appling Rignt algorithm getting som difficulty! plz Check!");
				}

		return cube;
	}// end rigntAlgo


	static char[][][] leftAlgo(char[][][] cube,int i){
		
		if(i==1 && cube[1][2][0] == cube[0][1][1]){// Apply Left Algorithm {D'R'DR}
					//But first we check 2nd cube[4][2][2] color of edge pice should match with its same color center pice, if it is so good!,
					//else perform Down-Anticlock wise [D'] Rotate upto it takes it correct position.
					if(cube[4][2][2] != cube[4][1][1]){
						//D' upto cube[4][2][2] color touch its correct 'same color' center pice.
						


						//************************************
						char color = cube[4][2][2]; 
						int i;
						for(i=1;i<=4;i++){
							if(color == cube[i][1][1]){
								break;
							}

						}
						// i variable has a value which is very crucial and useful

						int j = 3; //face(3) so j=3
						while(true){ //it will match cube[4][2][2] color with it's matching color center
							//D' Or we can apply D also but fine we go with D' for both right and left algo
							cube = DownFlip.flipCounter(cube);
							
							if(cube[j][2][2] == color){		//Debug Code here always should print
								System.out.println("Good!!"); 
							}else{
								System.out.println("Bad!!");
							}
							
							.if(cube[j][2][2] == cube[j][1][1]){ //color = cube[j][2][2]
								break;
							}
							if(j==1){
								j=4;
							}else{
								j--;
							}
						}
						
						if(i==1){ // it will give that place cube[i][2][2] where the white pice exist which is also in relation with cube[j][2][0]
							i = 4; // or we can say which was with relation with cube[1][2][0] 
						}else{
							i--;
						}



						//************************************
					}
					
					
					
					//D'R'DR
					if(cube[1][0][2] == cube[1][1][1] && cube[2][0][0] == cube[2][1][1]){ //Debug code Blue pos(1) match with Blue center && Orange match pos(3) with Orange center
						System.out.println("Right algo Success...face(1)");
					}
				}else if(i==2 && cube[2][2][0] == cube[0][1][1]){
					//D'B'DB
					if(cube[2][0][2] == cube[2][1][1] ){ //Debug code
						System.out.println("Right algo Success...face(2)");
					}
				}else if(i==3 && cube[3][2][0] == cube[0][1][1]){
					//D'L'DL
					if(cube[3][0][2] == cube[3][1][1]){ //Debug code
						System.out.println("Right algo Success...face(3)");
					}
				}else if(i==4 && cube[4][2][0] == cube[0][1][1]){
					//D'F'DF
					if(cube[4][0][2] == cube[4][1][1]){ //Debug code
						System.out.println("Right algo Success...face(4)");
					}
				}else{
					//Debug code
					System.out.println("Appling Rignt algorithm getting som difficulty! plz Check!");
				}

		return cube;
	}//end leftAlgo

}// end Step2








				|
a b c d e f g h | i j  k  l  m  n  o  p 
1 2 3 4 5 6 7 8 | 9 10 11 12 13 14 15 16
--------------- | ---------------------      most left
      0 |       |  			1					 1
------- |------ |---------- |-----------
   0	|   1	|     0     |     1				 2
--- |-- |-- |-- |-----|---- | --- | --- 
	|	|	|	|	  |		|	  |
	|	|	|	|	  |		|	  |
	|	|	|	|	  |		|	  |
	|		|	|	  |		|	  |

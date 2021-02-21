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

class Step3{
	static char[][][] step3Main(char[][][] cube){ 
		System.out.println("Hello World!");


		return cube;
	}// end step3Main method

	
	//*****************************************************************************	
	static char[][][] noEdgeYellow(char[][][] cube){ 
		if(cube[1][0][1] != cube[0][1][1] && cube[0][2][1] != cube[0][1][1]){
			//turn and match [x][0][1] position color with its correct center.
			//call findMyCenter
			cube = findMyCenter(cube,1)
		}
		
		if(cube[2][0][1] != cube[0][1][1] && cube[0][1][2] != cube[0][1][1]){
			
		}

		if(cube[3][0][1] != cube[0][1][1] && cube[0]][0][1] != cube[0]][1][1]){
			
		}
		
		if(cube[4][0][1] != cube[0][1][1] && cube[0]][1][0] != cube[0]][1][1]){
			
		}


		return cube;
	}// end noEdgeYellow method

	//*****************************************************************************	
	static char[][][] findMyCenter(char[][][] cube, int face){ 
			int j=face;
			while(true){
				if(cube[j][0][1]==cube[j][1][1]){
					break;
				}
				cube = UpFlip.flip(cube);  //U
				if(j==1){j = 4;}else{j--;}
			}
			// now j has face of matching center!
			// then check the 2nd color of that edge, and check where it's center pice is exist Left/Right
			// call findMyCenterLR
			cube = findMyCenterLR(cube,j);

		return cube;
	}// end findMyCenter method

	//*****************************************************************************
	static char[][][] findMyCenterLR(char[][][] cube, int face){
		int j=face;

		if(j==1 && cube[0][2][1]==cube[4][1][1]){		//aply left Algo
			// call left algo method
			cube = leftAlgo(cube, j);
		}else if(j==2 && cube[0][1][2]==cube[1][1][1]){
			
		}else if(j==3 && cube[0][0][1]==cube[2][1][1]){

		}else if(j==4 && cube[0][1][0]==cube[3][1][1]){
			
		}else if(j==1 && cube[0][2][1]==cube[2][1][1]){	//aply right Algo
			//call right Algo method
			cube = rightAlgo(cube, j);
		}else if(j==2 && cube[0][1][2]==cube[3][1][1]){

		}else if(j==3 && cube[0][0][1]==cube[4][1][1]){

		}else if(j==4 && cube[0][1][0]==cube[1][1][1]){
			
		}

		return cube;
	}// end findMyCenterLR method

	//*****************************************************************************
	static char[][][] leftAlgo(char[][][] cube, int face){
		int j = face;
		
		if(j==1){
			//U'L'UL
			//UFU'F'
		}else if(j==2){
			//U'F'UF
			//URU'R'
		}else if(j==3){
			//U'R'UR
			//UBU'B'
		}else if(j==4){
			//U'B'UB
			//ULU'L'
		}
		
		int t = j;
		if(t==1){t = 4;}else{t--;}
		if(cube[j][1][0] == cube[j][1][1] && cube[t][1][2] == cube[t][1][1]){ //Debug code
			System.out.println("leftAlgo -------------------> True");
		}else{
			System.out.println("leftAlgo -------------------> Fales");
		}

		return cube;
	}// end leftAlgo method

	//*****************************************************************************
	static char[][][] rightAlgo(char[][][] cube, int face){
		int j = face;
		
		if(j==1){
			//URU'R' 
			//U'F'UF
		}else if(j==2){
			//UBU'B' 
			//U'R'UR
		}else if(j==3){
			//ULU'L' 
			//U'B'UB
		}else if(j==4){
			//UFU'F' 
			//U'L'UL
		}
		
		int t = j;
		if(t==4){t = 1;}else{t++;}
		if(cube[j][1][2] == cube[j][1][1] && cube[t][1][0] == cube[t][1][1]){ //Debug code
			System.out.println("rightAlgo -------------------> True");
		}else{
			System.out.println("rightAlgo -------------------> Fales");
		}

		return cube;
	}// end rightAlgo method
	
	//##################################################################################
	static char[][][] allEdgeYellow(char[][][] cube){
		//here you check all x01 position really have yellow color if yes! then,
		for(int i==1;i<=4;i++){
			if(cube[i][0][1] != cube[0][1][1]){
				System.out.println("All edge have Not yellow color!");
				return cube;
			}
		}

		//find a edge pice in middle layer which is in wrong place with both color of it with centers. (x12 != x11) && ((x+1)10 != (x+1)11)		
		// Calling findEdge
		cube = findEdge(cube);


		return cube;
	}// end allEdgeYellow method

	//*****************************************************************************
	static char[][][] findEdge(char[][][] cube){
		if(cube[1][1][2] != cube[1][1][1] && cube[2][1][0] != cube[2][1][1]){
			//apply right Algo
			cube = rightAlgo(cube, 1);
			cube = noEdgeYellow(cube);
		}else if(cube[2][1][2] != cube[2][1][1] && cube[3][1][0] != cube[3][1][1]){
		}else if(cube[3][1][2] != cube[3][1][1] && cube[4][1][0] != cube[4][1][1]){
		}else if(cube[4][1][2] != cube[4][1][1] && cube[1][1][0] != cube[1][1][1]){
			
		}

		return cube;
	}// end findEdge method


}// end class Step3

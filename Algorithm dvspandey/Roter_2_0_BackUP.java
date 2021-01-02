class Roter_2_0_BackUP {
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		char cube[][][] = {					// up,front,right,back,left,down
			{								// up [yellow][0]               
				{'Y','Y','Y'},				                                
				{'Y','Y','Y'},				                                
				{'Y','Y','Y'}				                                
     	    },								                                
			{								// front [blue][1]              
				{'B','B','B'},				                                
				{'B','B','B'},				                                
				{'B','B','B'}				                                
			},								                                
			{								// right [red][2]               
				{'R','R','R'},			                                
				{'R','R','R'},			                                
				{'R','R','R'}	 			                                
											                                
			},								                                
			{								// back [green][3]              
				{'G','G','G'},				                                
				{'G','G','G'},				                                
				{'G','G','G'}				                                
			},								                                
			{								// left [Orange][4]             
				{'O','O','O'},				                                
				{'O','O','O'},				                                
				{'O','O','O'}				                                
											                                
			},								                                
			{								// down [white][5]              
				{'W','W','W'},
				{'W','W','W'},
				{'W','W','W'}				
			
			}
		
		
		};
		
		// before  solve
		for(int z=0; z<cube.length; z++){
			System.out.println("\nFace: "+z);
			for(int x=0; x<cube[z].length; x++){
				for(int y=0; y<cube[z][x].length; y++){
					System.out.print(cube[z][x][y]+ "  ");
				}
				System.out.println();
			}
			System.out.println();
		}

	
	//***************************** Flip Logics **************************
			//		 R		L		U	  F 	   D	  B 
			//		 R'		L'		U'	  F'	   D'	  B'
	
	
		//cube = RUR_U_(cube);		// RUR'U'
		//cube = L_U_LU(cube);		// L'U'LU
//		int a = vaidCube(cube);
//		System.out.println("value of a: "+a);


		cube = Step1.middleLayer(cube); 

		//after solve
		for(int z=0; z<cube.length; z++){
			System.out.println("\nFace: "+z);
			for(int x=0; x<cube[z].length; x++){
				for(int y=0; y<cube[z][x].length; y++){
					System.out.print(cube[z][x][y]+ "  ");
				}
				System.out.println();
			}
			System.out.println();
		}

	

			
//------------------------TOP layer-------------------------------------				
//top(000,001,002,010,fix[011],012,020,021,022),(00,01,02)*4 ----> in face(1,2,3,4)
/*
		for(int z=0; z<1; z++){ //top
			for(int x=0; x<cube[z].length; x++){
				for(int y=0; y<cube[z][x].length; y++){
					if(x==1 && y==1){
						continue;
					}
					System.out.print(" cube["+z+"]["+x+"]["+y+"]  :" + cube[z][x][y]);
					
				}
				System.out.println();
			}
			System.out.println();
		}


//(00,01,02)*4 ----> in face(1,2,3,4)

		for(int z=1; z<5; z++){
			for(int x=0; x<1; x++){
				for(int y=0; y<cube[z][x].length; y++){

					System.out.print(" cube["+z+"]["+x+"]["+y+"]  :" + cube[z][x][y]);
				}
				System.out.println();
			}
			System.out.println();
		}

*/



//------------------------Middle layer-------------------------------------				
//middle((10,fix[11],12)*4 ----> in face(1,2,3,4))
//middle
/*
		for(int z=1; z<5; z++){
			for(int x=1; x<2; x++){
				for(int y=0; y<cube[z][x].length; y++){
					if(x==1 && y==1){
						continue;
					}
					System.out.print(" cube["+z+"]["+x+"]["+y+"]  :" + cube[z][x][y]);
				}
				System.out.println();
			}
			System.out.println();
		}

*/
//------------------------Buttom layer-------------------------------------		
//buttom
//buttom(500,501,502,510,fix[511],512,520,521,522),(20,21,22)*4 ----> in face(1,2,3,4)
/*
		for(int z=5; z<cube.length; z++){ 
			for(int x=0; x<cube[z].length; x++){
				for(int y=0; y<cube[z][x].length; y++){
					if(x==1 && y==1){
						continue;
					}
					System.out.print(" cube["+z+"]["+x+"]["+y+"]  :" + cube[z][x][y]);
					
				}
				System.out.println();
			}
			System.out.println();
		}





//(20,21,22)*4 ----> in face(1,2,3,4)

		for(int z=1; z<5; z++){
			for(int x=2; x<3; x++){
				for(int y=0; y<cube[z][x].length; y++){

					System.out.print(" cube["+z+"]["+x+"]["+y+"]  :" + cube[z][x][y]);
				}
				System.out.println();
			}
			System.out.println();
		}


*/



	}//main method close

 
	static char[][][] RUR_U_(char[][][] cube){
		//RUR'U'
		cube = RightFlip.flip(cube);
		cube = UpFlip.flip(cube);
		cube = RightFlip.flipCounter(cube);
		cube = UpFlip.flipCounter(cube);
		
		return cube;
	}

	static char[][][] L_U_LU(char[][][] cube){
		//L_U_LU
		cube = LeftFlip.flipCounter(cube);
		cube = UpFlip.flipCounter(cube);
		cube = LeftFlip.flip(cube);
		cube = UpFlip.flip(cube);

		return cube;
	}

	static int vaidCube(char[][][] cube){
		char ch;
		int y1 = 0;
		int b = 0;
		int r = 0;
		int g = 0;
		int o = 0;
		int	w = 0;

		for(int z=0; z<cube.length; z++){
			for(int x=0; x<cube[z].length; x++){
				for(int y=0; y<cube[z][x].length; y++){
					//System.out.print(cube[z][x][y]+ "  ");
					ch = cube[z][x][y];
					switch(ch){
						case 'Y':
							y1++;
							break;
						case 'B':
							b++;
							break;
						case 'R':
							r++;
							break;
						case 'G':
							g++;
							break;	
						case 'O':
							o++;
							break;
						case 'W':
							w++;	
							break;
						default:
							System.out.println("dvs Error!");
					}
				}
			}
		}
/*
		System.out.println("y: "+y1);
		System.out.println("b: "+b);
		System.out.println("r: "+r);
		System.out.println("g: "+g);
		System.out.println("o: "+o);
		System.out.println("w: "+w);
*/		
		System.out.println(cube[0][1][1]+" <--------> "+cube[5][1][1]);
		System.out.println(cube[1][1][1]+" <--------> "+cube[3][1][1]);
		System.out.println(cube[2][1][1]+" <--------> "+cube[4][1][1]);

		if(y1==b && b==r && r==g && g==o && o==w){
			System.out.println("Cube is Valid! \n\tChecking Success...");
			return 0;
		}else{
			return 1;
		}

	}//end vaidCube

	

}// Roter_2_0_BackUP class end

////////////////////////////////////////////////////////////////////////
/* 




		//cube = RightFlip.flip(cube);
		//cube = RightFlip.flipCounter(cube);
		  
		//cube = LeftFlip.flip(cube);
		//cube = LeftFlip.flipCounter(cube);
		  
		//cube = UpFlip.flip(cube);
		//cube = UpFlip.flipCounter(cube);

		//cube = DownFlip.flip(cube);
		//cube = DownFlip.flipCounter(cube);

		//cube = FrontFlip.flip(cube);
		//cube = FrontFlip.flipCounter(cube);

		//cube = BackFlip.flip(cube);
		//cube = BackFlip.flipCounter(cube);

		//cube[1] = FaceRotor90.faceRotate90(cube[1]);  // Front
		//cube[1] = FaceRotor90.faceRotateCounter90(cube[1]);   // Front 



















Rubik's Cube 3x3 [6 face]
		
		int arr[][][] = {
			{								// up [yellow][0]       
				{000,001,002},				                        
				{010,011,012},				                        
				{020,021,022}				                        
     	    },								                        
			{								// front [blue][1]      
				{100,101,102},				                        
				{110,111,112},				                        
				{120,121,122}				                        
			},								                        
			{								// right [red][2]       
				{200,201,202},				                        
				{210,211,212},				                        
				{220,221,222}				                        
											                        
			}								                        
			{								// back [green][3]      
				{300,301,302},				                        
				{310,311,312},				                        
				{320,321,322}				                        
			},								                        
			{								// left [Orange][4]     
				{400,401,402},				                        
				{410,411,412},				                        
				{420,421,422}				                        
											                        
			},								                        
			{								// down [white][5]      
				{500,501,502},				                   
				{510,511,512},
				{520,521,522}				
			
			}
		
		
		}; 
		
		
		{'B','B','B'},	      {'R','R','R'},	
		{'B','B','B'},		  {'R','R','R'},	
		{'B','B','B'}		  {'R','R','R'}	
		
		

		{'0','1','2'},			{'Y','Y','Y'},	
		{'0','1','2'},			{'Y','Y','Y'},	
		{'0','1','2'}			{'Y','Y','Y'}	



		{'G','G','G'},			
		{'G','G','G'},	
		{'G','G','G'}	









{100,101,102},				{102,112,122}
{110,111,112},	------>   	{101,111,121} 
{120,121,122}				{100,110,120}










		*/





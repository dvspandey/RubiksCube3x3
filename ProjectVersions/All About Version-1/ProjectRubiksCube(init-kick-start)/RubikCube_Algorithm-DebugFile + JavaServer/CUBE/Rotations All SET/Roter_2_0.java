import java.util.Scanner;

class Roter_2_0{

	/*static char cube[][][] = {					// up,front,right,back,left,do        
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
	};*/


	public static void main(String[] args) 
	{	
		System.out.println("Hello World!");
		char cube[][][] = {					// up,front,right,back,left,down
			{								// up [yellow][0]               
				{'B','Y','R'},				                                
				{'O','Y','Y'},				                                
				{'W','R','G'}				                                
     	    },								                                
			{								// front [blue][1]              
				{'R','W','Y'},				                                
				{'B','B','W'},				                                
				{'G','O','G'}				                                
			},								                                
			{								// right [red][2]               
				{'R','R','B'},			                                
				{'G','R','R'},			                                
				{'Y','R','W'}	 			                                
											                                
			},								                                
			{								// back [green][3]              
				{'W','B','Y'},				                                
				{'B','G','W'},				                                
				{'O','G','R'}				                                
			},								                                
			{								// left [Orange][4]             
				{'O','G','G'},				                                
				{'B','O','O'},				                                
				{'B','O','W'}				                                
											                                
			},								                                
			{								// down [white][5]              
				{'B','Y','Y'},
				{'G','W','W'},
				{'O','Y','O'}				
			
			}
		//Note: when fill 5th Face take Back side(green)
		// in front and then Rotate cube for see 5th Face with correct position
 
		
		};
		
   //		Scan by user
/*
		 		Scanner scn = new Scanner(System.in);
		char cubeTemp[][][] = new char[6][3][3];

		for(int z=0; z<cubeTemp.length; z++){
			System.out.println("\nFace: "+z);
			for(int x=0; x<cubeTemp[z].length; x++){
				for(int y=0; y<cubeTemp[z][x].length; y++){
					cubeTemp[z][x][y] = scn.next().charAt(0);
				}
			}
		}


		for(int z=0; z<cubeTemp.length; z++){
			System.out.println("\nFace: "+z);
			for(int x=0; x<cubeTemp[z].length; x++){
				for(int y=0; y<cubeTemp[z][x].length; y++){

					System.out.print(cubeTemp[z][x][y]+ "  ");
				}
				System.out.println();
			}
			System.out.println();
		}


		cube = cubeTemp;
*/

/**/

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
		int a = vaidCube(cube);
		System.out.println("value of a: "+a);
			
		 //																		   cube = Step1.step1Main(cube);
 
// These All functions are Well No need to change All set update 12/25/2020 --dvspandey
		cube = RightFlip.flip(cube);          	                                   
		cube = LeftFlip.flip(cube);           				                                      
		cube = UpFlip.flip(cube);             		      		                                    
		cube = DownFlip.flip(cube);           		 		                                      
		cube = FrontFlip.flip(cube);          		   		                                      
		cube = BackFlip.flip(cube);           
				

		cube = BackFlip.flipCounter(cube);
		cube = FrontFlip.flipCounter(cube);
		cube = DownFlip.flipCounter(cube);
		cube = UpFlip.flipCounter(cube);
		cube = LeftFlip.flipCounter(cube); 
		cube = RightFlip.flipCounter(cube);  
			
		


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
			System.out.println("\nCube is Invalid!");
			return 1;
		}

	}//end vaidCube

	

}//end Router_2.0 class 

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


*/





/*

		char cube[][][] = {					// up,front,right,back,left,do
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
		
Face: 0
W  G  O
B  Y  Y
W  R  Y


Face: 1
B  Y  B
G  B  O
G  O  O


Face: 2
R  B  B
G  R  W
B  O  Y


Face: 3
Y  Y  G
G  G  B
G  O  W


Face: 4
R  R  R
W  O  R
G  W  O


Face: 5
R  W  O
Y  W  R
R  B  Y




*/	                                                                  
import java.util.Scanner;

class Roter_2_0{
/*
	static char cube[][][] = {					// up,front,right,back,left,do        
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

*/
	public static void main(String[] args) 
	{	
		System.out.println("Hello World!");
		char cube[][][] = {					// up,front,right,back,left,down
			{								// up [yellow][0]               
				{'G','B','W'},				                                
				{'R','Y','B'},				                                
				{'W','Y','G'}				                                
     	    },								                                
			{								// front [blue][1]              
				{'G','O','R'},				                                
				{'R','B','G'},				                                
				{'B','Y','R'}				                                
			},								                                
			{								// right [red][2]               
				{'W','Y','B'},			                                
				{'O','R','B'},			                                
				{'W','W','O'}	 			                                
											                                
			},								                                
			{								// back [green][3]              
				{'O','O','O'},				                                
				{'W','G','R'},				                                
				{'Y','O','G'}				                                
			},								                                
			{								// left [Orange][4]             
				{'Y','Y','O'},				                                
				{'B','O','G'},				                                
				{'R','G','R'}				                                
											                                
			},								                                
			{								// down [white][5]              
				{'B','W','Y'},
				{'R','W','W'},
				{'B','G','Y'}				
			
			}
		//Note: when fill 5th Face take Back side(green)
		// in front and then Rotate cube for see 5th Face with correct position
 
		
		};
/*		
   //		Scan by user

		Scanner scn = new Scanner(System.in);
		char cubeTemp[][][] = new char[6][3][3];

		for(int z=0; z<cubeTemp.length; z++){
			System.out.println("\nFace: "+z);
			for(int x=0; x<cubeTemp[z].length; x++){
				for(int y=0; y<cubeTemp[z][x].length; y++) {
					cubeTemp[z][x][y] = scn.next().charAt(0);
				}
			}
		}

		cube = cubeTemp;
*/
	  
		//cube = Roter_2_0.cube;  //class level variable call Or static variable call
/**/	

		// before  solve
		cube = display(cube, 0);


		int a = vaidCube(cube);
		if(a==-1) {
			System.out.println("Cube is Invalid!");
		}else if(a==100) {
			System.out.println("cube is valid and Already solved!");
		}else if(a==0) {
			System.out.println("cube is valid and need Solution!");
		}
		System.out.println("value of a: "+a);

//****************************** Step1 calling Start **************************		  
// SOLVE LAYER ONE – [STEP 1_ Create A Daisy + 3 STEP 2_ Create A White Cross With Matched Edges And Center Pieces]
		  
		  try{
			  if(a==0){
			   cube = Step1.step1Main(cube); //white cross at buttom
			  }
		  }
			 catch(MyException e){
			//System.out.println(e.toString());
			cube = e.cube();
			a=10;
		 }

		//after solve Step1
		cube = display(cube, 1);

//****************************** Step1 calling End ****************************
//****************************** Step2 calling Start **************************		  
// SOLVE LAYER ONE – Step 3_ Solve The White Corners
		
		if(a==10){
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				cube = MiddleFlip.flipV(cube);
				cube = MiddleFlip.flipV(cube);
				cube = LeftFlip.flipCounter(cube);
				cube = LeftFlip.flipCounter(cube);
			   
			   cube = Step2.step2Main(cube);
			   a = 20;
		

			//after solve Step2
			cube = display(cube, 2);
		}

//****************************** Step2 calling End ****************************		 																		  
//****************************** Step3 calling Start **************************		  
// SOLVE THE MIDDLE LAYER
		if(a==20){
				cube = RightFlip.flip(cube);
				cube = RightFlip.flip(cube);
				cube = MiddleFlip.flipV(cube);
				cube = MiddleFlip.flipV(cube);
				cube = LeftFlip.flipCounter(cube);
				cube = LeftFlip.flipCounter(cube);
			   
			   cube = Step3.step3Main(cube);
			   a = 30;
		  

			//after solve Step3
			cube = display(cube, 3);
		}

//****************************** Step3 calling End ****************************	
//****************************** Step4 calling Start **************************		  
// SOLVE FINAL LAYER – Step 1_ Make A Yellow Cross

		if(a==30){
			
			   cube = Step4.step4Main(cube);
			   a = 40;
		  

			//after solve Step4
			cube = display(cube, 4);
		}

//****************************** Step4 calling End ****************************	
//****************************** Step5 calling Start **************************		  
// SOLVE FINAL LAYER – Step 2_ Orient the Corners
		
		if(a==40){
			
			   cube = Step5.step5Main(cube);
			   a = 50;
		  

			//after solve Step5
			cube = display(cube, 5);
		}

//****************************** Step5 calling End ****************************	
//****************************** Step6 calling Start **************************		  
// SOLVE FINAL LAYER – Step 3_ Position The Yellow Corners
		
		if(a==50){
			
			   cube = Step6.step6Main(cube);
			   a = 60;
		  

			//after solve Step6
			cube = display(cube, 6);
		}

//****************************** Step6 calling End ****************************	
//****************************** Step7 calling Start **************************		  
// SOLVE THE FINAL LAYER – Step 4_ Position The Yellow Edges
		if(a==60){
			
			   cube = Step7.step7Main(cube);
			   a = 70;
		  

			//after solve Step7
			cube = display(cube, 7);
		}

//****************************** Step7 calling End ****************************	












	}//main method close





static int vaidCube(char[][][] cube){
		int flag = 100;
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
					if(cube[z][x][y]==cube[z][1][1] && flag == 100) {
						flag=100;// cube already solved
					}else {
						flag=0;// cube need to solution
					}
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
							System.out.println("dvs-Roter_2_0-Invalid Cube Error!");
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
		
		System.out.println(cube[0][1][1]+" <--------> "+cube[5][1][1]);
		System.out.println(cube[1][1][1]+" <--------> "+cube[3][1][1]);
		System.out.println(cube[2][1][1]+" <--------> "+cube[4][1][1]);
*/
		if(y1==b && b==r && r==g && g==o && o==w){
			System.out.println("Cube is Valid! \n\tChecking Success...");
			if(flag==100) {	return 100;}else {return 0;}
		}else{
			System.out.println("\nCube is Invalid!");
			return -1;
		}

	}//end vaidCube	

	static char[][][] display(char[][][] cube, int stepNo){
		//------------------------------------------------------------------
		System.out.println("...........after solve Step"+stepNo+".....");
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



		//------------------------------------------------------------------

		return cube;
	}// end display

}//end Router_2.0 class 



//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Extra Imp code &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

/*________________________________________________________________________________________
// These All functions are Well No need to change All set update 12/25/2020 --dvspandey	  |
		cube = RightFlip.flip(cube);          	                                   		  |
		cube = LeftFlip.flip(cube);           				                              |        
		cube = UpFlip.flip(cube);             		      		                          |          
		cube = DownFlip.flip(cube);           		 		                              |        
		cube = FrontFlip.flip(cube);          		   		                              |        
		cube = BackFlip.flip(cube);           											  |
		cube = MiddleFlip.flipH(cube); //horizontal										  |
		cube = MiddleFlip.flipV(cube); //vertical										  |
																						  |
																						  |
		cube = MiddleFlip.flipCounterV(cube) //vertical 								  |
		cube = MiddleFlip.flipCounterH(cube); //horizontal								  |
		cube = BackFlip.flipCounter(cube);												  |
		cube = FrontFlip.flipCounter(cube);												  |
		cube = DownFlip.flipCounter(cube);												  |
		cube = UpFlip.flipCounter(cube);												  |
		cube = LeftFlip.flipCounter(cube); 												  |
		cube = RightFlip.flipCounter(cube);  											  |
																						  |
*///______________________________________________________________________________________|	

/*_____________________________________________________________________________
			         Flip Cube 180*												|
		Top		--> White			Logic										|
		Buttom	--> Yellow			====== 										|
		Right	--> Orange			2R 2Mv 2L' 									|
		Left	--> Red				2U 2Mh 2D'									|
		Front	--> Green														|
		Back	--> Blue														|
																				|
																				|
*///___________________________________________________________________________	|
																				
/*_____________________________________________________________________________	
																			   |	
	//%%%%%%%%%%%%%%%%%%%%%%%%%% Flip Logics %%%%%%%%%%%%%%%%%%%%%%%%%%%	   |
			//		 R		L		U	  F 	   D	  B					   |
			//		 R'		L'		U'	  F'	   D'	  B'				   |
																			   | 		
			//cube = RUR_U_(cube);		// RUR'U'							   |
			//cube = L_U_LU(cube);		// L'U'LU							   |
																			   |
*///___________________________________________________________________________|

/* _____________________________________________________________________________
																				|
		//cube = RightFlip.flip(cube);											|
		//cube = RightFlip.flipCounter(cube);									|
		  																		|
		//cube = LeftFlip.flip(cube);											|
		//cube = LeftFlip.flipCounter(cube);									|
		  																		|
		//cube = UpFlip.flip(cube);												|
		//cube = UpFlip.flipCounter(cube);										|
																				|
		//cube = DownFlip.flip(cube);											|
		//cube = DownFlip.flipCounter(cube);									|
																				|
		//cube = FrontFlip.flip(cube);											|
		//cube = FrontFlip.flipCounter(cube);									|
																				|
		//cube = BackFlip.flip(cube);											|
		//cube = BackFlip.flipCounter(cube);									|
																			    |
		//cube[1] = FaceRotor90.faceRotate90(cube[1]);  // Front                |  
		//cube[1] = FaceRotor90.faceRotateCounter90(cube[1]);   // Front 		|
																				|
*///___________________________________________________________________________	|



/*___________________________________________________________________________________________________________________________________
																			 Face: 0						Face: 0                 |
		cube = MiddleFlip.flipH(cube); //horizontal							 Y  Y  Y  						Y  Y  Y 				|
		cube = MiddleFlip.flipV(cube); //vertical							 Y  Y  Y  						Y  G  Y 				|
		cube = MiddleFlip.flipCounterH(cube); //horizontal					 Y  Y  Y  						Y  Y  Y 				|
		cube = MiddleFlip.flipCounterV(cube); //vertical					          						        				|
																			         						        				|
																			 Face: 1 						Face: 1 				|
																			 B  B  B 						B  B  B 				|
																			 B  B  B 						B  R  B 				|
																			 B  B  B 						B  B  B 				|
																			         						        				|
																			             Convert into		        				|
																			 Face: 2   --------------> 		Face: 2 				|
																			 R  R  R 						R  R  R 				|
																			 R  R  R 						R  W  R 				|
																			 R  R  R 						R  R  R 				|
																			         						        				|
																			         						        				|
																			 Face: 3 						Face: 3 				|
																			 G  G  G 						G  G  G 				|
																			 G  G  G 						G  O  G 				|
																			 G  G  G 						G  G  G 				|
																			         						        				|
																			         						                        |
																			 Face: 4 						Face: 4 				|
																			 O  O  O 						O  O  O 				|
																			 O  O  O 						O  Y  O 				|
																			 O  O  O 						O  O  O 				|
																			         						        				|
																			         						        				|
																			 Face: 5 						Face: 5 				|
																			 W  W  W 						W  W  W 				|
																			 W  W  W 						W  B  W 				|
																			 W  W  W 						W  W  W 				|
																																	|
																																	|
*///________________________________________________________________________________________________________________________________|
																																	
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&					
																																	
																																	
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
		

*/	                                                                  
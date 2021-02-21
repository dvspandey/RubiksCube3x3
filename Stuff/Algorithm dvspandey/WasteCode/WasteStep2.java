/*
Logic:
======											
												|
			Face-1[Blue]						|									Face-3[Green]       	  
			-------								|					  				-------      
			if(!9){								|					  				if(!9){      
				RUR'U'							|					  				    LUL'U'         
			}else if(!7){						|					  				}else if(!7){
				L'U'LU							|					  				    R'U'RU         
			}									|					  				}            
________________________________________________|___________________________________________________
												|												 
			Face-2[Red]							|						  			Face-4[Orange]       
			-------								|					  				-------      
			if(!9){								|					  				if(!9){      
				BUB'U'							|					  				     FUF'U'        
			}else if(!7){						|					  				}else if(!7){
				F'U'FU							|					  				    B'U'BU         
			}									|					  				}            
												|
												
Note:-											
		> !9  ----> means at 9th position correct element is not there.  [OR]  cube[x][2][2] != cube[x][1][1]
		> !7  ----> means at 7th position correct element is not there.  [OR]  cube[x][2][0] != cube[x][1][1]

*/



class Step2{
	
	static char[][][] step2Main(char[][][] cube){
		if((cube[1][2][2] == cube[1][1][1]) && (cube[2][2][2] == cube[2][1][1]) && (cube[3][2][2] == cube[3][1][1]) && (cube[4][2][2] == cube[4][1][1])){//pos(9)
			if((cube[1][2][0] == cube[1][1][1]) && (cube[2][2][0] == cube[2][1][1]) && (cube[3][2][0] == cube[3][1][1]) && (cube[4][2][0] == cube[4][1][1])){//pos(7)
				if((cube[1][2][1] == cube[1][1][1]) && (cube[2][2][1] == cube[2][1][1]) && (cube[3][2][1] == cube[3][1][1]) && (cube[4][2][1] == cube[4][1][1])){//pos(8)
					System.out.println("Cube ready from Step2!");
					return cube;	
				}
			}else{
				//pos(!7) [OR] fix pos(7)
				cube = fixSeven(cube);
			}
		}else{
			if((cube[1][2][0] == cube[1][1][0]) && (cube[2][2][0] == cube[2][1][1]) && (cube[3][2][0] == cube[3][1][1]) && (cube[4][2][0] == cube[4][1][1])){	
				//pos(!9) [OR] fix pos(9)
				cube = fixNine(cube);
				System.out.println("pos(7) initially Ok!!!");
			}else{
				//pos(!9) [OR] fix pos(9)
				cube = fixNine(cube);
				//pos(!7) [OR] fix pos(7)
				if((cube[1][2][0] == cube[1][1][0]) && (cube[2][2][0] == cube[2][1][1]) && (cube[3][2][0] == cube[3][1][1]) && (cube[4][2][0] == cube[4][1][1])){
					System.out.println("pos(7) Ok!!!");
				}else{
					cube = fixSeven(cube);
				}
			}
		}
		
			
		if((cube[1][2][2] == cube[1][1][1]) && (cube[2][2][2] == cube[2][1][1]) && (cube[3][2][2] == cube[3][1][1]) && (cube[4][2][2] == cube[4][1][1])){//pos(9)
			if((cube[1][2][0] == cube[1][1][1]) && (cube[2][2][0] == cube[2][1][1]) && (cube[3][2][0] == cube[3][1][1]) && (cube[4][2][0] == cube[4][1][1])){//pos(7)
				if((cube[1][2][1] == cube[1][1][1]) && (cube[2][2][1] == cube[2][1][1]) && (cube[3][2][1] == cube[3][1][1]) && (cube[4][2][1] == cube[4][1][1])){//pos(8)
					System.out.println("Cube ready from Step2! Check Success...!");
				}else{
					System.out.println("POS(8) -----------> Miss ");
				//	cube = LeftFlip.flipCounter(cube);
				//	cube = RightFlip.flip(cube);
				//	cube = step2Main(cube);
				}
			}else{
				System.out.println("Cube Step2! Check Fail pos(7)...!");
				cube = step2Main(cube);
			}
		}else{
			System.out.println("Cube Step2! Check Fail pos(9)...!");
			cube = step2Main(cube);
		}


		return cube;
	}//end step2Main

// Supporting Functions following

	static char[][][] fixNine(char[][][] cube){
		//!(22(9)==11(5 fix))
		int temp = 1;
		loop:while(true){
	//------------------------------------------------------------------------------------------------------------------
			// Face-1[Blue'pos-9']
			if(cube[1][2][2] != cube[1][1][1] || temp == 1){ 
				int whileCount = 0;//max=6 then repeat!
				inner:while(true){
					//System.out.println("Face-1[Blue'pos-9'] whileCount("+whileCount+")\tcube[1][2][2] = "+cube[1][2][2]); //Debug_code
					cube = RightFlip.flip(cube);
					System.out.println("R");
					if(cube[1][2][2] == cube[1][1][1]){
						break inner;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[1][2][2] == cube[1][1][1]){
						break inner;
					}

					cube = RightFlip.flipCounter(cube);
					System.out.println("R'");
					if(cube[1][2][2] == cube[1][1][1]){
						break inner;
					}

					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[1][2][2] == cube[1][1][1]){
						break inner;
					}

					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-1[Blue'pos-9'] whileCount("+whileCount+")\tcube[1][2][2] = "+cube[1][2][2]+"---------->Done!"); //Debug_code
				//temp++;
			}
	//------------------------------------------------------------------------------------------------------------------
			// Face-2[Red'pos-9']
			if(cube[2][2][2] != cube[2][1][1] || temp == 2){ 
				int whileCount = 1;//max=6 then repeat!
				inner:while(true){
					//System.out.println("Face-2[Red'pos-9'] whileCount("+whileCount+")\tcube[2][2][2] = "+cube[2][2][2]); //Debug_code
					cube = BackFlip.flip(cube);
					System.out.println("B");
					if(cube[2][2][2] == cube[2][1][1]){
						break inner;
					}
					
					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[2][2][2] == cube[2][1][1]){
						break inner;
					}
					
					cube = BackFlip.flipCounter(cube);
					System.out.println("B'");
					if(cube[2][2][2] == cube[2][1][1]){
						break inner;
					}
					
					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[2][2][2] == cube[2][1][1]){
						break inner;
					}
					
					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-2[Red'pos-9'] whileCount("+whileCount+")\tcube[2][2][2] = "+cube[2][2][2]+"---------->Done!"); //Debug_code
				//temp++;
			}

	//------------------------------------------------------------------------------------------------------------------

			// Face-3[Green'pos-9']
			if(cube[3][2][2] != cube[3][1][1]){ 
				int whileCount = 1;//max=6 then repeat!
				inner:while(true){
					//System.out.println("Face-3[Green'pos-9'] whileCount("+whileCount+")\tcube[3][2][2] = "+cube[3][2][2]); //Debug_code
					cube = LeftFlip.flip(cube); 
					System.out.println("L");
					if(cube[3][2][2] == cube[3][1][1] || temp == 3){
						break inner;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[3][2][2] == cube[3][1][1]){
						break inner;
					}
					
					cube = LeftFlip.flipCounter(cube);
					System.out.println("L'");
					if(cube[3][2][2] == cube[3][1][1]){
						break inner;
					}
					
					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[3][2][2] == cube[3][1][1]){
						break inner;
					}
					
					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-3[Green'pos-9'] whileCount("+whileCount+")\tcube[3][2][2] = "+cube[3][2][2]+"---------->Done!"); //Debug_code
				//temp++;
			}

	//------------------------------------------------------------------------------------------------------------------			
			// Face-4[Orange'pos-9']
			if(cube[4][2][2] != cube[4][1][1] || temp == 4){ 
				int whileCount = 1;//max=6 then repeat!
				inner:while(true){
					//System.out.println("Face-4[Orange'pos-9'] whileCount("+whileCount+")\tcube[4][2][2] = "+cube[4][2][2]); //Debug_code
					cube = FrontFlip.flip(cube);
   					System.out.println("F");
					if(cube[4][2][2] == cube[4][1][1]){
						break inner;
					}
					
					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[4][2][2] == cube[4][1][1]){
						break inner;
					}
					
					cube = FrontFlip.flipCounter(cube);
					System.out.println("F'");
					if(cube[4][2][2] == cube[4][1][1]){
						break inner;
					}
					
					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[4][2][2] == cube[4][1][1]){
						break inner;
					}
					
					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-4[Orange'pos-9'] whileCount("+whileCount+")\tcube[4][2][2] = "+cube[4][2][2]+"---------->Done!"); //Debug_code
				//temp++;
			}
	//------------------------------------------------------------------------------------------------------------------


			if((cube[1][2][2] == cube[1][1][1]) && (cube[2][2][2] == cube[2][1][1]) && (cube[3][2][2] == cube[3][1][1]) && (cube[4][2][2] == cube[4][1][1])){//pos(9)
				if((cube[1][2][1] == cube[1][1][1]) && (cube[2][2][1] == cube[2][1][1]) && (cube[3][2][1] == cube[3][1][1]) && (cube[4][2][1] == cube[4][1][1])){//pos(8)
					System.out.println("POS(9): True");
					System.out.println("POS(8): True");
					break loop;
				}else{
					System.out.println("POS(9): True Only......\t Going for pos(8)");
					temp++;
					if(temp>4)
						temp=1;
				}
			}else{
				cube = UpFlip.flip(cube);// additional 	
				System.out.println("U");
			}
			

		}//end	'loop:while'

				//**************** Display *****************************
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
				
				//*****************************************************			
		
		return cube;

	}//end fixNine

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	static char[][][] fixSeven(char[][][] cube){
		//!(20(7)==11(5 fix))
		
		loop:while(true){
			// Face-1[Blue'pos-7']
			if(cube[1][2][0] != cube[1][1][1]){
				int whileCount = 1;//max=6 then repeat!
				while(true){
					//System.out.println("Face-1[Blue'pos-7'] whileCount("+whileCount+")\tcube[1][2][0] = "+cube[1][2][0]); //Debug_code
					cube = LeftFlip.flipCounter(cube);
					System.out.println("L'");
					if(cube[1][2][0] == cube[1][1][1]){
						break;
					}

					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[1][2][0] == cube[1][1][1]){
						break;
					}

					cube = LeftFlip.flip(cube);
					System.out.println("L");
					if(cube[1][2][0] == cube[1][1][1]){
						break;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[1][2][0] == cube[1][1][1]){
						break;
					}
					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-1[Blue'pos-7'] whileCount("+whileCount+")\tcube[1][2][0] = "+cube[1][2][0]+"---------->Done!"); //Debug_code
			}			
	//------------------------------------------------------------------------------------------------------------------		
			// Face-2[Red'pos-7']
			if(cube[2][2][0] != cube[2][1][1]){
				int whileCount = 1;//max=6 then repeat!
				while(true){
					//System.out.println("Face-2[Red'pos-7'] whileCount("+whileCount+")\tcube[2][2][0] = "+cube[2][2][0]); //Debug_code
					cube = FrontFlip.flipCounter(cube);
					System.out.println("F'");
					if(cube[2][2][0] == cube[2][1][1]){
						break;
					}

					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[2][2][0] == cube[2][1][1]){
						break;
					}

					cube = FrontFlip.flip(cube);
					System.out.println("F");
					if(cube[2][2][0] == cube[2][1][1]){
						break;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[2][2][0] == cube[2][1][1]){
						break;
					}

					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-2[Red'pos-7'] whileCount("+whileCount+")\tcube[2][2][0] = "+cube[2][2][0]+"---------->Done!"); //Debug_code
			}	
	//------------------------------------------------------------------------------------------------------------------
			// Face-3[Green'pos-7']
			if(cube[3][2][0] != cube[3][1][1]){
				int whileCount = 1;//max=6 then repeat!
				while(true){
					//System.out.println("Face-3[Green'pos-7'] whileCount("+whileCount+")\tcube[3][2][0] = "+cube[3][2][0]); //Debug_code
					cube = RightFlip.flipCounter(cube);
					System.out.println("R'");
					if(cube[3][2][0] == cube[3][1][1]){
						break;
					}

					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[3][2][0] == cube[3][1][1]){
						break;
					}

					cube = RightFlip.flip(cube); 
					System.out.println("R");
					if(cube[3][2][0] == cube[3][1][1]){
						break;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[3][2][0] == cube[3][1][1]){
						break;
					}

					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-3[Green'pos-7'] whileCount("+whileCount+")\tcube[3][2][0] = "+cube[3][2][0]+"---------->Done!"); //Debug_code
			}
	//------------------------------------------------------------------------------------------------------------------
			// Face-4[Orange'pos-7']
			if(cube[4][2][0] != cube[4][1][1]){
				int whileCount = 1;//max=6 then repeat!
				while(true){
					//System.out.println("Face-4[Orange'pos-7'] whileCount("+whileCount+")\tcube[4][2][0] = "+cube[4][2][0]); //Debug_code
					cube = BackFlip.flipCounter(cube);
					System.out.println("B'");
					if(cube[4][2][0] == cube[4][1][1]){
						break;
					}

					cube = UpFlip.flipCounter(cube);
					System.out.println("U'");
					if(cube[4][2][0] == cube[4][1][1]){
						break;
					}

					cube = BackFlip.flip(cube);
					System.out.println("B");
					if(cube[4][2][0] == cube[4][1][1]){
						break;
					}

					cube = UpFlip.flip(cube);
					System.out.println("U");
					if(cube[4][2][0] == cube[4][1][1]){
						break;
					}

					whileCount++;
					if(whileCount>6)
						break;
				}
				//System.out.println("Face-4[Orange'pos-7'] whileCount("+whileCount+")\tcube[4][2][0] = "+cube[4][2][0]+"---------->Done!"); //Debug_code
			}
	//------------------------------------------------------------------------------------------------------------------
/* //Debug_code
				//***************** Display ***********************
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
				
				//*****************************************************	
*/



			if((cube[1][2][0] == cube[1][1][1]) && (cube[2][2][0] == cube[2][1][1]) && (cube[3][2][0] == cube[3][1][1]) && (cube[4][2][0] == cube[4][1][1])){//pos(7)
				if((cube[1][2][2] == cube[1][1][1]) && (cube[2][2][2] == cube[2][1][1]) && (cube[3][2][2] == cube[3][1][1]) && (cube[4][2][2] == cube[4][1][1])){//pos(9)
					if((cube[1][2][1] == cube[1][1][1]) && (cube[2][2][1] == cube[2][1][1]) && (cube[3][2][1] == cube[3][1][1]) && (cube[4][2][1] == cube[4][1][1])){//pos(8)
						System.out.println("POS(7): True");
						System.out.println("POS(9): True");
						System.out.println("POS(8): True");
						break loop;	
					}
				}else{
					System.out.println("POS(7): True Only......\t Going for pos(9)");
					cube = UpFlip.flipCounter(cube); // additional
					System.out.println("U'");
					cube = fixNine(cube);
				}				
			}else{
				cube = UpFlip.flip(cube);// additional 	
				System.out.println("U");
			}
			

		}// end 'loop:while'
				//***************** Display ***********************
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
				
				//*****************************************************		

		return cube;
	}//end fixSeven


}//end Step2 class


/* $$$$$$$$$$$$$$$$$$$$$$  Some Refrence Algorithm Logics $$$$$$$$$$$$$$$$$$$$$$$$


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


























*/
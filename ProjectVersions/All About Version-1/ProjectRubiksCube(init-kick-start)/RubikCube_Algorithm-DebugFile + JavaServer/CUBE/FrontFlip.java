//***************************** Flip Logics **************************
		//		 R		L		U	  F 	   D	  B 
		//		 R'		L'		U'	  F'	   D'	  B'


class FrontFlip 
{
				// Front Flip


	static char[][][] flip(char cube[][][]){
		
		// U {  // flip front face Clock-Wise(NOT column) [z][x][y]				  
																					  
		//	 R <-- Y        [2]		  =		 [0]									  
		//				{200,210,220} =  {020,021,022}								  
																					  
		//	 Y <-- O        [0]		  =		 [4]									  
		//				{022,021,020}  =  {402,412,422}								  
																					  
		//	 O <-- W        [4]		  =		 [5] 									  
		//				{422,412,402} =  {520,521,522}								  
																					  
		//	 W <-- R        [5]		  =		 [2]
		//				{520,521,522} =  {200,210,220}		
 
		// }
//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];
		
		char tempArr[] = new char[3];  // for copying column	
		// [2]	= [0]
								
		tempArr[0] = cube[2][0][0]; 																					
		tempArr[1] = cube[2][1][0]; 																					
		tempArr[2] = cube[2][2][0]; 																					
																							
		cube[2][0][0] = cube[0][2][0]; 
		cube[2][1][0] = cube[0][2][1]; 																					
		cube[2][2][0] = cube[0][2][2]; 																					
																							   
	//##########################################################
		// [0]  = [4]
		cube[0][2][2] = cube[4][0][2];
		cube[0][2][1] = cube[4][1][2];
		cube[0][2][0] = cube[4][2][2];
																																		   
	//##########################################################							   
		// [4]  = [5]																		   
		cube[4][2][2] = cube[5][2][0];
		cube[4][1][2] = cube[5][2][1];
		cube[4][0][2] = cube[5][2][2];
																							   
	//##########################################################							   
		// [5]  = [2]																		   
		cube[5][2][0] = tempArr[0]; 
		cube[5][2][1] = tempArr[1]; 																					
		cube[5][2][2] = tempArr[2]; 

	//	System.out.println(tempArr.length);  //for printing
	//	for(int i=0; i<tempArr.length; i++)
	//		System.out.println(tempArr[i]);
//***********************************************************************
		cube[1] = FaceRotor90.faceRotate90(cube[1]);   // front

		return cube;
	
	}// flip method close



//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
				
						// Reverse Front Flip



	static char[][][] flipCounter(char cube[][][]){
		
		// U {  // flip front face AntiClock-Wise(NOT column) [z][x][y]						
																								
		//	 Y <-- R        [0]		  =		 [2]	-------> step1							
		//				{020,021,022} = {200,210,220}  										
																								
		//	 O <-- Y        [4]		  =		 [0]	-------> step4							
		//				{402,412,422} = {022,021,020}  										
																								
		//	 W <-- O        [5]		  =		 [4]	-------> step3							
		//				{520,521,522} = {422,412,402}  										  
																								
		//	 R <-- W        [2]		  =		 [5]	-------> step2							  
		//				{200,210,220} = {520,521,522}  		
 
		// }
//******************************* R *************************************
		// Note: char tempArr[] = new char[3];  // for copying column 
		// Note: For copy 1 Row ------>  char tempArr[] = cube[][];
		
		char tempArr[] = new char[3];  // for copying column	
		// [0]	= [2]
								
		tempArr[0] = cube[0][2][0]; 																					
		tempArr[1] = cube[0][2][1]; 																					
		tempArr[2] = cube[0][2][2]; 																					
																							
		cube[0][2][0] = cube[2][0][0]; 
		cube[0][2][1] = cube[2][1][0]; 																					
		cube[0][2][2] = cube[2][2][0]; 																					
																							   
																							   
	//##########################################################							   
		// [2]  = [5]																		   
		cube[2][0][0] = cube[5][2][0]; 
		cube[2][1][0] = cube[5][2][1]; 																					
		cube[2][2][0] = cube[5][2][2]; 
																																		   
	//##########################################################							   
		// [5]  = [4]																		   
		cube[5][2][0] = cube[4][2][2];
		cube[5][2][1] = cube[4][1][2];
		cube[5][2][2] = cube[4][0][2];

	//##########################################################
		// [4]  = [0]
		cube[4][0][2] = tempArr[2];
		cube[4][1][2] = tempArr[1];
		cube[4][2][2] = tempArr[0];


	//	System.out.println(tempArr.length);  //for printing
	//	for(int i=0; i<tempArr.length; i++)
	//		System.out.println(tempArr[i]);
//***********************************************************************
		cube[1] = FaceRotor90.faceRotateCounter90(cube[1]);  // Front
		
		return cube;
	
	}// flipCounter method close



}// end class FrontFlip


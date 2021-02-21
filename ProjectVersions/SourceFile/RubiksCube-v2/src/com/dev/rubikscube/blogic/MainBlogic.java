//package net.cube.rubikscube.model;
package com.dev.rubikscube.blogic;

import com.dev.rubikscube.blogic.steps.*;

public class MainBlogic { 
	private char[][][] cube;
	@SuppressWarnings("unused")
	private String cubeStatus;
	private Step1 s1; 
	private Step2 s2; 
	private Step3 s3; 
	private Step4 s4;
	private Step5 s5;
	private Step6 s6;
	private Step7 s7;
	@SuppressWarnings("unused")
	private Step8 s8;
	
 
	
	public MainBlogic(char[][][] cube) {
		super();
		this.cube = cube.clone();
		main();
//		display(this.cube, 0);
//		display(s1.getCube(),1);
//		display(s2.getCube(),2);
//		display(s3.getCube(),3);
//		display(s4.getCube(),4);
//		display(s5.getCube(),5);
//		display(s6.getCube(),6);
//		display(s7.getCube(),7);
//		display(s8.getCube(),8);
		System.out.println("dvs: Server Success..");
	}
	
	void main() {
		//#1 a==0 means cube is valid and need Solution!
		//#2 a==100 means cube is valid and Already solved!
		//#3 a==-1 means cube is Invalid  
		char[][][] destCube;
		destCube = copyCube(cube,new char[6][3][3]);
		int a = vaidCube(cube);
		if(a==-1) {
			cubeStatus = "Cube is Invalid!";
		}else if(a==100) {
			cubeStatus = "cube is valid and Already solved!";
		}else if(a==0) {
			cubeStatus = "cube is valid and need Solution!";
			if(a==0) {												// Why we use CLONE() Check Imp Note section below.
				s1 = new Step1(destCube);
//				System.out.println(s1.getMyRotations());
//				display(s1.getCube(),1); 
				a = 10;
			}
			if(a==10) {
				destCube = copyCube(s1.getCube(),new char[6][3][3]);
				s2 = new Step2(destCube);
//				System.out.println(s2.getMyRotations());
//				display(s2.getCube(),2);
				a = 20;
			}
			if(a==20) {
				destCube = copyCube(s2.getCube(),new char[6][3][3]);
				s3 = new Step3(destCube);
//				System.out.println(s3.getMyRotations());
//				display(s3.getCube(),3);
				a=30;
			}
			if(a==30) {
				destCube = copyCube(s3.getCube(),new char[6][3][3]);
				s4 = new Step4(destCube);
//				System.out.println(s4.getMyRotations());
//				display(s4.getCube(),4);
				a=40;
			}
			if(a==40) {
				destCube = copyCube(s4.getCube(),new char[6][3][3]);
				s5 = new Step5(destCube);
//				System.out.println(s5.getMyRotations());
//				display(s5.getCube(),5);
				a=50;
			}
			if(a==50) {
				destCube = copyCube(s5.getCube(),new char[6][3][3]);
				s6 = new Step6(destCube);
//				System.out.println(s6.getMyRotations());
//				display(s6.getCube(),6);
				a=60;
			}
			if(a==60) {
				destCube = copyCube(s6.getCube(),new char[6][3][3]);
				s7 = new Step7(destCube);
//				System.out.println(s7.getMyRotations());
//				display(s7.getCube(),7);
				a=70;
			}
			if(a==70) {
				destCube = copyCube(s7.getCube(),new char[6][3][3]);
				s8 = new Step8(destCube);
//				System.out.println(s8.getMyRotations());
//				display(s8.getCube(),8);
				a=80;
			}
		}
	}// end non-Static main method
	
	
/*	
	
	public static void main(String[] args) {
		RoterSteps_and_Cube r1 = new RoterSteps_and_Cube(cube1);
		//r1.main();
	}
	
	
	static char cube1[][][] = {					// up,front,right,back,left,down
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
			
			}};
	
*/
/*	
	static char[][][] display(char[][][] cube, int stepNo){
		//------------------------------------------------------------------
		System.out.println("...........after solve Step "+stepNo+".....");
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
/**/	
	
	
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
			//System.out.println("Cube is Valid! \n\tChecking Success...");
			if(flag==100) {	return 100;}else {return 0;}
		}else{
			//System.out.println("\nCube is Invalid!");
			return -1;
		}

	}//end vaidCube	
	
	
	static char[][][] copyCube(char[][][] srcCube, char[][][] destCube){
		//------------------------------------------------------------------
		for(int z=0; z<srcCube.length; z++){
			for(int x=0; x<srcCube[z].length; x++){
				for(int y=0; y<srcCube[z][x].length; y++){
					destCube[z][x][y] = srcCube[z][x][y];
				}
			}
		}
		//------------------------------------------------------------------

		return destCube;
	}// end display
	
	
		
}//RoterSteps_and_Cube



/*
IMP Notes
---------

#1>https://www.geeksforgeeks.org/array-copy-in-java/
 * Using clone Method because when we copy char[][][] array by assignment operator 
then if we do any changes of of the array it will reflect to another which cause the data loss.
because both pointing to same char[][][] array reference.
To over come this problem we have Two methods:-
 > Method #1 we can iterate a loop and copy every elements of one array into another array using index.
		which is time lengthy process.
 > Method #2 We can use JAVA CLONE() method which will copy data one array to another but not reference.
 		& it is [Recommended].

*/
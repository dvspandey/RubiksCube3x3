/* //Created 17-JAN-2021
	------> Reduse repeated Rotations into number of rotations.
	There is One Issue, here we get same rotations U U U U U U U.... number of times where i want this into U7-U4 = U3
	This PrepareSteps Class prepare Rotation to easy to read and Understand.
	and convert repeated rotations into valid numbers of steps.
	Ex:
		We should put a function which convert
		U U U U U = U
		U U U U   = -
		U U U     = U3 
		U U       = U2
		U         = U


Converting
===========
 //U U' L L' F F' R R' B B' D D' Mv Mv' Mh Mh'  
//U u  L l  F f  R r  B b  D d  V  v   H  h     

*/


package com.dev.rubikscube.blogic;

public class PrepareSteps {
  
	static int length = 0;
//	public static void main(String[] args) 
//	{
//		System.out.println("Hello World!");
//		String str = "DDDDDDD'";
//		str = setRotations(str);
//		System.out.println("str:"+str);
//	}
 
	public static String setRotations(String rotations){
		char[] ch = new char[rotations.length()];
		String str;
		rotations.getChars(0, rotations.length(), ch, 0); // Convert String to character array.
		

		ch = converterUu(ch,rotations.length());		
		str = "";
 		for(int i=0;i<length;i++){
			str = str + ch[i];
		}//System.out.println("converterUu str: "+str+"\nlength:"+length);
		
		//---------------------------------------------
		ch = giveNumber(ch,length);
		str = "";
 		for(int i=0;i<length;i++){
			str = str + ch[i];
		}//System.out.println("giveNumber str: "+str+"\nlength:"+length);

		return str;
	}//end method setRotations

	static char[] converterUu(char[] ch1,int len){//U u  L l  F f  R r  B b  D d  V  v   H  h
												  //U U' L L' F F' R R' B B' D D' Mv Mv' Mh Mh'
		char[] ch2 = new char[len]; 
		char chr;
		int j = 0;

		for(int i=0;i<len;i++){
			chr = ch1[i];
			switch(chr){ //U U' L L' F F' R R' B B' D D' Mv Mv' Mh Mh'
				case 'U':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ //using ternary operator as precution where we not check more than size as index.
						ch2[j] = 'u'; //small letter means U'
						j++;
						i++;
					}else{
						ch2[j] = 'U'; //capital letter means U
						j++;
					}
					break;
				case 'L':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ 
						ch2[j] = 'l'; 
						j++;
						i++;
					}else{
						ch2[j] = 'L'; 
						j++;
					}
					break;
				case 'F':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ 
						ch2[j] = 'f'; 
						j++;
						i++;
					}else{
						ch2[j] = 'F'; 
						j++;
					}					
					break;
				case 'R':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ 
						ch2[j] = 'r'; 
						j++;
						i++;
					}else{
						ch2[j] = 'R'; 
						j++;
					}					
					break;	
				case 'B':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ 
						ch2[j] = 'b'; 
						j++;
						i++;
					}else{
						ch2[j] = 'B'; 
						j++;
					}						
					break;
				case 'D':
					if(ch1[(i+1 < len) ? i+1 : i]==(char)39){ 
						ch2[j] = 'd'; 
						j++;
						i++;
					}else{
						ch2[j] = 'D'; 
						j++;
					}					
					break;	
				case 'M':
					if(ch1[(i+1 < len) ? i+1 : i]=='h'){//for Mh----> H & Mh' ----> h
						if(ch1[(i+2 < len) ? i+2 : i+1]==(char)39){ 
							ch2[j] = 'h'; 
							j++;
							i=i+2;
						}else{
							ch2[j] = 'H';
							j++; i++; 
						}	
					}else if(ch1[(i+1 < len) ? i+1 : i]=='v'){
						if(ch1[(i+2 < len) ? i+2 : i+1]==(char)39){//for Mv-----> V & Mv'------> v 
							ch2[j] = 'v'; 
							j++;
							i=i+2;
						}else{
							ch2[j] = 'V'; 
							j++; i++;
						}
					}					
					break;
				case ' ':
					; //dummy statement
					break;
				default:
					System.out.println("dvs:method converterUu Error! chr: "+chr);
			}// end switch statement
		}
		length = j;
		return ch2;
	}// end  for


	static char[] giveNumber(char ch[], int len){
		char[] ch2 = new char[len]; 
		char nextChr, prevChr='x';
		int count = 0,j=0;
		for(int i=0;i<len;i++){
			nextChr = ch[i];
			if(nextChr==prevChr && count==3){
				count=0;
			}else if(i==len-1){
				if(nextChr==prevChr){
					count++;
					ch2[j] = prevChr;j++;	
					if(count>1){
						if(count==2){
							ch2[j] = '2';	j++;
						}else if(count==3){
							ch2[j] = '3';	j++;
						}
					}
				}else{
					if(count>0){
						ch2[j] = prevChr;j++;
						if(count>1){
							if(count==2){
								ch2[j] = '2';	j++;
							}else if(count==3){
								ch2[j] = '3';	j++;
							}
						}	
					}
					ch2[j] = nextChr;j++;
				}
				
									
			}else if(nextChr==prevChr){
				count++;
			}else{
				if(count>0){
					ch2[j] = prevChr;j++;
					if(count>1){
						if(count==2){
							ch2[j] = '2';	j++;
						}else if(count==3){
							ch2[j] = '3';	j++;
						}
					}
					
				}count = 1;
			}	
			prevChr = nextChr;
		}//for loop
		length = j;
		return ch2;
	}//giveNumber


//	static char[] converterReverse(char[] ch1,int len){//U u  L l  F f  R r  B b  D d  V  v   H  h  
//													   //U U' L L' F F' R R' B B' D D' Mv Mv' Mh Mh'
//
//		//No need to convert... [i just want to UI side conversion so, no need to convert here]
//		return ch1;									   
//	}//converterReverse

}//End class PrepareSteps

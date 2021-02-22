package com.dev.rubikscube.blogic;

import java.util.Arrays;
import java.util.List;

import com.dev.rubikscube.bean.DataJB;
import com.dev.rubikscube.dao.UserInfoFile;




public class UserService {
	public List<MainBlogic> getSteps(DataJB dataJB){
		if(dataJB.getObj().getName()==null) {
			System.out.println("Seeker Name: GetRequest/[NULL]");// for view who use my App
		}else {
			System.out.println("Seeker Name: "+dataJB.getObj().getName());// for view who use my App
		}
		UserInfoFile.main(dataJB.getObj());// To store UserInfo data into file
		return Arrays.asList(new MainBlogic(dataJB.getCube()));
		//return Arrays.asList(new RoterSteps_and_Cube(cube));
	}
	
	
	
	
	
//	static char cube[][][] = {					// up,front,right,back,left,down
//		{								// up [yellow][0]               
//			{'G','B','W'},				                                
//			{'R','Y','B'},				                                
//			{'W','Y','G'}				                                
// 	    },								                                
//		{								// front [blue][1]              
//			{'G','O','R'},				                                
//			{'R','B','G'},				                                
//			{'B','Y','R'}				                                
//		},								                                
//		{								// right [red][2]               
//			{'W','Y','B'},			                                
//			{'O','R','B'},			                                
//			{'W','W','O'}	 			                                
//										                                
//		},								                                
//		{								// back [green][3]              
//			{'O','O','O'},				                                
//			{'W','G','R'},				                                
//			{'Y','O','G'}				                                
//		},								                                
//		{								// left [Orange][4]             
//			{'Y','Y','O'},				                                
//			{'B','O','G'},				                                
//			{'R','G','R'}				                                
//										                                
//		},								                                
//		{								// down [white][5]              
//			{'B','W','Y'},
//			{'R','W','W'},
//			{'B','G','Y'}				
//		
//		}
//	//Note: when fill 5th Face take Back side(green)
//	// in front and then Rotate cube for see 5th Face with correct position
//
//	
//	};
	
}

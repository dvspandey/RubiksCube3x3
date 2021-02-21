//package net.cube.rubikscube.model;
package com.dev.rubikscube.bean;

public class DataJB {
	private char[][][] cube; 
	private String rotations = " ";
	private Inner obj;  
	
	public DataJB(char[][][] cube) {
		super();
		this.cube = cube;
		obj = new Inner();//Inner/nested class object
	}
	
	public char[][][] getCube() {
		return cube;
	}
	public void setCube(char[][][] cube) {
		this.cube = cube;
	}
	public String getRotations() {
		return rotations;
	}
	public void setRotations(String rotations) {
		this.rotations = rotations;
	}

	public Inner getObj() {
		return obj;
	}
	public void setObj(Inner obj) {
		this.obj = obj;
	}

	//****************************** For UserData File ***************************
	  public class Inner{  
			private String name = null;
			private String date = null;
			private String timeStamp = null;
			private String pcName = null;
			private String ipAddress = null;

			public Inner(){// no use this constructor but if we explicitly create object then this constructor is usefull.			
				name = "Akshay";         
				date = "19-JAN-2021";    
				timeStamp = "7:53";      
				pcName  = "AkshayPC";    
				ipAddress = "172.1.1.1";
			}

			public String getName() {
				return name;
			}

			public String getDate() {
				return date;
			}

			public String getTimeStamp() {
				return timeStamp;
			}

			public String getPcName() {
				return pcName;
			}

			public String getIpAddress() {
				return ipAddress;
			}

			public void setName(String name) {
				this.name = name;
			}

			public void setDate(String date) {
				this.date = date;
			}

			public void setTimeStamp(String timeStamp) {
				this.timeStamp = timeStamp;
			}

			public void setPcName(String pcName) {
				this.pcName = pcName;
			}

			public void setIpAddress(String ipAddress) {
				this.ipAddress = ipAddress;
			} 
			
			@Override
			public String toString() {
				String str = "NAME: ["+name + "] DATE: ["+date + "] TimeStamp: ["+timeStamp + "] PcName: ["+pcName + "] ipAddress: ["+ipAddress+"]"; 
				return str;
			}//toString

		  }//end Inner class
	
	
}// end DataJB class

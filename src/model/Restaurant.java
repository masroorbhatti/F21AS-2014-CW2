package model;

import java.io.FileNotFoundException;

public class Restaurant {

	private ReadFileData rfd = new ReadFileData();
	private boolean opened = false;
 
	
	public Restaurant(){
		
		Initialize();
		
	}
	
	private void Initialize() {
		
		try{
			rfd.readMenuFile();
			rfd.readOrderFile();
		}
		catch (FileNotFoundException fnf){
			System.out.println("Input file(s) are missing");
		}
	}
	public void openRestaurant(){
		

		
	}
	
	public void closeRestaurant(){
		
		
		
	}
		
}
	
	


package model;

import java.io.FileNotFoundException;

public class Restaurant {

	private ReadFileData rfd = new ReadFileData();
	private boolean opened = false;

	private Thread waitergetorder1;
	private Thread waitergetorder2;
	private Thread waitressdeliver1;
	private Thread waitressdeliver2;
	
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
	public boolean isOpened(){
		return opened;
	}
	public void openRestaurant(){
		opened=true;
		Thread waitergetorder1 = new Thread(new ReceiveOrder(2000,this));
		Thread waitergetorder2 = new Thread(new ReceiveOrder(6000,this));
		Thread waitressdeliver1 = new Thread(new DeliverOrder(5000,this));
		Thread waitressdeliver2 = new Thread(new DeliverOrder(8000,this));
		waitergetorder1.start();
		waitergetorder2.start();
		waitressdeliver1.start();
		waitressdeliver2.start();
		
	}
	
	public void closeRestaurant(){
		
		
		
	}
		
}
	
	


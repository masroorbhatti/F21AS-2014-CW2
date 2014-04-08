package model;

import java.io.FileNotFoundException;

public class Restaurant {

	private ReadFileData rfd = new ReadFileData();
	private int totreceiverer;
	private int totdeliverer;
	private Thread[] waitergetorder;
	private Thread[] waitressdeliver;
	
	
	public Restaurant(ReceiveOrder[] receiveorders,DeliverOrder[] deliverorders){
		
		Initialize();
		totreceiverer = receiveorders.length;
		totdeliverer = deliverorders.length;
		waitergetorder = new Thread[totreceiverer];
		waitressdeliver = new Thread[totdeliverer];
		for (int i =0;i < totreceiverer; i++){
			waitergetorder[i] = new Thread(receiveorders[i]);
			waitergetorder[i].setName("Waiter "+i+1);
		}
		for (int i =0;i < totdeliverer; i++){
			waitressdeliver[i] = new Thread(deliverorders[i]);	
			waitressdeliver[i].setName("Waitress "+i+1);
		} 
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
		RestaurantState.getInstance().setState(true);
		
		for (int i =0;i < this.totreceiverer; i++)
			waitergetorder[i].start();	
		
		for (int i =0;i < this.totdeliverer; i++)
			waitressdeliver[i].start();	
		
	}
	
	public void closeRestaurant(){
		
		
		
	}
		
}
	
	


package model;

public class RestaurantState {
	private static RestaurantState instance = new RestaurantState();
	private boolean reststate;
	private RestaurantState(){
		reststate=false;
	}
	
	public static RestaurantState getInstance(){
		return instance;
	}
	public void setState(boolean reststate){
		this.reststate = reststate; 
		
	}
	public boolean isOpened(){
		return reststate;
	}

}

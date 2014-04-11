package model;

/**
 * 
 *This class is used to maintain the state of the restaurant.
 *The state may be open or close (true or false)
 *Singleton approch is used so that the current state could be access from any where
 */
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

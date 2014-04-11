package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Restaurant;
import model.RestaurantState;
import view.RestaurantGUI;

//This class is controlling event of CloseRestaurant
public class CloseRestaurantController {

	
	private Restaurant restaurant;
	private RestaurantGUI restaurantgui;
	
	/**
	 * Constructor
	 * @param restaurantgui
	 * @param restaurant
	 */
	public CloseRestaurantController(RestaurantGUI restaurantgui, Restaurant restaurant){
		this.restaurantgui = restaurantgui;
		this.restaurant = restaurant;
		
		restaurantgui.addCloseRestaurantListener(new SetListener());
	}
	
	//inner class SetListener responds when user sets the time
		public  class SetListener implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				restaurant.closeRestaurant();
			}
		}
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.RestaurantGUI;
import model.Restaurant;

public class OpenRestaurantController {

	private Restaurant restaurant;
	private RestaurantGUI restaurantgui;
	
	
	public OpenRestaurantController(RestaurantGUI restaurantgui, Restaurant restaurant){
		this.restaurantgui = restaurantgui;
		this.restaurant = restaurant;
		
		restaurantgui.addOpenRestaurantListener(new SetListener());
	}
	
	//inner class SetListener responds when user sets the time
		public  class SetListener implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				restaurant.openRestaurant();
			}
		}
	
}

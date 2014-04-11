package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import view.RestaurantGUI;

//This class is controlling event of ExitApplication
public class ExitApplicationController {

	private RestaurantGUI restaurantgui;
	
	/**
	 * Constructor
	 * @param restaurantgui
	 */
	public ExitApplicationController(RestaurantGUI restaurantgui){
		this.restaurantgui = restaurantgui;

		
		restaurantgui.addExitApplicationListener(new SetListener());
	}
	
	//inner class SetListener responds when user sets the time
		public  class SetListener implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
			}
		}
}

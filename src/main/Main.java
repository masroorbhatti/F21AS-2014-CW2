package main;

import controller.*;
import view.RestaurantGUI;
import model.DeliverOrder;
import model.ReadFileData;
import model.ReceiveOrder;
import model.Restaurant;
public class Main
{
    public static void main (String arg[]) {

    	ReceiveOrder receiveorders[] = {new ReceiveOrder(7000),new ReceiveOrder(8000)} ;
    	DeliverOrder deliverorders[] = {new DeliverOrder(13000),new DeliverOrder(14000),new DeliverOrder(11000)} ;
    	Restaurant restaurant = new Restaurant(receiveorders,deliverorders);

		RestaurantGUI restaurantgui = new RestaurantGUI(receiveorders,deliverorders,restaurant);
		restaurantgui.setVisible(true);

		OpenRestaurantController openrestaurant = new OpenRestaurantController(restaurantgui,restaurant);
		CloseRestaurantController closerestaurant = new CloseRestaurantController(restaurantgui,restaurant);
		ExitApplicationController exitapplication = new ExitApplicationController(restaurantgui);
    	
 
    }


}

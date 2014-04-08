package main;

import view.RestaurantFullView;
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

		RestaurantFullView frame = new RestaurantFullView(receiveorders,deliverorders,restaurant);
		frame.setVisible(true);
		restaurant.openRestaurant();
    	
 
    }


}

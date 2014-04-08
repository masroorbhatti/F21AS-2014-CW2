package main;

import view.RestaurantFullView;
import model.DeliverOrder;
import model.ReadFileData;
import model.ReceiveOrder;
import model.Restaurant;
public class Main
{
    public static void main (String arg[]) {

    	ReceiveOrder receiveorders[] = {new ReceiveOrder(1000),new ReceiveOrder(5000)} ;
    	DeliverOrder deliverorders[] = {new DeliverOrder(2000), new DeliverOrder(2000)} ;
    	Restaurant restaurant = new Restaurant(receiveorders,deliverorders);

		RestaurantFullView frame = new RestaurantFullView(receiveorders,deliverorders,restaurant);
		frame.setVisible(true);
		restaurant.openRestaurant();
    	

    }


}

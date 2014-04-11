package main;

import controller.*;
import view.RestaurantGUI;
import model.DeliverOrder;
import model.ReceiveOrder;
import model.Restaurant;
public class Main
{
    public static void main (String arg[]) {

    	//Threads 
    	ReceiveOrder receiveorders[] = {new ReceiveOrder(7000),new ReceiveOrder(8000)} ;
    	DeliverOrder deliverorders[] = {new DeliverOrder(13000),new DeliverOrder(14000),new DeliverOrder(11000)} ;
    	Restaurant restaurant = new Restaurant(receiveorders,deliverorders);

    	//Main GUI initialization
		RestaurantGUI restaurantgui = new RestaurantGUI(receiveorders,deliverorders,restaurant);
		restaurantgui.setVisible(true);

		//Controller Initialization
		OpenRestaurantController openrestaurant = new OpenRestaurantController(restaurantgui,restaurant);
		CloseRestaurantController closerestaurant = new CloseRestaurantController(restaurantgui,restaurant);
		ExitApplicationController exitapplication = new ExitApplicationController(restaurantgui);
		ShowBill1Controller showreport1 = new ShowBill1Controller(restaurantgui,1);
		ShowBill2Controller showreport2 = new ShowBill2Controller(restaurantgui,2);
		ShowBill3Controller showreport3 = new ShowBill3Controller(restaurantgui,3);
		GenerateReport generatereport = new GenerateReport(restaurantgui);
		StatusReportController statusreport = new StatusReportController(restaurantgui);

 
    }


}

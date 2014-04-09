package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AllTables;
import model.Table;
import view.RestaurantGUI;


public class ShowBill1Controller {

	
	private Table table;
	private RestaurantGUI restaurantgui;
	
	
	public ShowBill1Controller(RestaurantGUI restaurantgui, int tableno){
		this.restaurantgui = restaurantgui;
		this.table = table;
		
		restaurantgui.addShowBill1Listener(new SetListener());
	}
	
	//inner class SetListener responds when user sets the time
		public  class SetListener implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				restaurantgui.getReportViewer().setReportData(AllTables.getInstance().getTable(1).getOrderdItemDetails());
				
			}
		}
}

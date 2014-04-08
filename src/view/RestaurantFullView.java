package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DeliverOrder;
import model.ReceiveOrder;
import model.Restaurant;

public class RestaurantFullView extends JFrame {

	/**
	 * Create the frame.
	 */
	public RestaurantFullView(ReceiveOrder[] receiveorders, DeliverOrder[] deliverorders, Restaurant restaurant) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 640);
		
		OrderListView orderListView = new OrderListView(receiveorders,deliverorders);
		orderListView.setBounds(0, 0, 230, 260);
		
		TableListView tableListView1 = new TableListView(deliverorders,1);
		tableListView1.setBounds(0, 300, 230, 260);
		
		TableListView tableListView2 = new TableListView(deliverorders,2);
		tableListView2.setBounds(250, 300, 230, 260);

		
		TableListView tableListView3 = new TableListView(deliverorders,3);
		tableListView3.setBounds(500, 300, 230, 260);


		getContentPane().add(orderListView);
		getContentPane().add(tableListView1);
		getContentPane().add(tableListView2);
		getContentPane().add(tableListView3);
		setVisible(true);
		
		
	}

}

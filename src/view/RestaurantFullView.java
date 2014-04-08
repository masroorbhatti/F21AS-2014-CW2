package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DeliverOrder;
import model.ReceiveOrder;
import model.Restaurant;

public class RestaurantFullView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RestaurantFullView(ReceiveOrder[] receiveorders, DeliverOrder[] deliverorders, Restaurant restaurant) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 600);
		OrderListView orderListView = new OrderListView(receiveorders);
		orderListView.setBounds(0, 0, 230, 260);
		getContentPane().add(orderListView);
		setVisible(true);
		
		
	}

}

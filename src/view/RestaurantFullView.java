package view;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DeliverOrder;
import model.ReceiveOrder;
import model.Restaurant;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class RestaurantFullView extends JFrame {

	/**
	 * Create the frame.
	 */
	public RestaurantFullView(ReceiveOrder[] receiveorders, DeliverOrder[] deliverorders, Restaurant restaurant) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 640);
		
		OrderListView orderListView = new OrderListView(receiveorders);
		orderListView.setBounds(500, 13, 230, 260);
		
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRestaurantOperations = new JMenu("Operations");
		mnRestaurantOperations.setMnemonic(KeyEvent.VK_O);
		mnRestaurantOperations.setDisplayedMnemonicIndex(0);
		menuBar.add(mnRestaurantOperations);
		
		JMenuItem mntmOpenRestaurant = new JMenuItem("Open Restaurant");
		mnRestaurantOperations.add(mntmOpenRestaurant);
		
		JMenuItem mntmCloseRestaurant = new JMenuItem("Close Restaurant");
		mnRestaurantOperations.add(mntmCloseRestaurant);
		
		JSeparator separator = new JSeparator();
		mnRestaurantOperations.add(separator);
		
		JMenuItem mntmExitApplication = new JMenuItem("Exit Application");
		mnRestaurantOperations.add(mntmExitApplication);
		
		JMenu mnreports = new JMenu("Reports");
		mnreports.setMnemonic(KeyEvent.VK_R);
		mnreports.setDisplayedMnemonicIndex(0);
		menuBar.add(mnreports);
		
		JMenuItem mntmStatusReport = new JMenuItem("Status Report");
		mnreports.add(mntmStatusReport);
		
		JMenuItem mntmShowBill = new JMenuItem("Show Bill - Table 1");
		mnreports.add(mntmShowBill);
		
		JMenuItem mntmShowBill_1 = new JMenuItem("Show Bill - Table 2");
		mnreports.add(mntmShowBill_1);
		
		JMenuItem mntmShowBill_2 = new JMenuItem("Show Bill - Table 3");
		mnreports.add(mntmShowBill_2);
		
		JSeparator separator_1 = new JSeparator();
		mnreports.add(separator_1);
		
		JMenuItem mntmGenerateReport = new JMenuItem("Generate Report");
		mnreports.add(mntmGenerateReport);
		setVisible(true);
		
		
	}
}

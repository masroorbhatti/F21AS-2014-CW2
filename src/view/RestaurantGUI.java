package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import model.DeliverOrder;
import model.ReceiveOrder;
import model.Restaurant;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class RestaurantGUI extends JFrame {

	private ReportDisplayView reportdisplayview = new ReportDisplayView();
	
	JMenuBar menuBar = new JMenuBar();
	
	JSeparator separator;
	JSeparator separator_1;
	
	JMenuItem mntmOpenRestaurant;
	JMenuItem mntmCloseRestaurant;
	JMenuItem mntmExitApplication;
	
	JMenuItem mntmStatusReport;
	JMenuItem mntmShowBill_1;
	JMenuItem mntmShowBill_2;
	JMenuItem mntmShowBill_3;
	JMenuItem mntmGenerateReport;
	
	/**
	 * Create the frame.
	 */
	public RestaurantGUI(ReceiveOrder[] receiveorders, DeliverOrder[] deliverorders, Restaurant restaurant) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 640);
		
		LogListView logListView = new LogListView(receiveorders,deliverorders );
		logListView.setBounds(10, 13, 230, 260);

		reportdisplayview.setBounds(260, 13, 230, 260);
		
		OrderListView orderListView = new OrderListView(receiveorders,deliverorders );
		orderListView.setBounds(510, 13, 230, 260);
		
		TableListView tableListView1 = new TableListView(deliverorders,1);
		tableListView1.setBounds(10, 300, 230, 260);
		
		TableListView tableListView2 = new TableListView(deliverorders,2);
		tableListView2.setBounds(260, 300, 230, 260);

		
		TableListView tableListView3 = new TableListView(deliverorders,3);
		tableListView3.setBounds(510, 300, 230, 260);


		getContentPane().add(logListView);
		getContentPane().add(reportdisplayview);
		getContentPane().add(orderListView);
		getContentPane().add(tableListView1);
		getContentPane().add(tableListView2);
		getContentPane().add(tableListView3);
		
		setMenuBar();
		setJMenuBar(menuBar);
		setVisible(true);
	
		
	}
	
	
	private void setMenuBar(){
		menuBar = new JMenuBar();
		
		JMenu mnRestaurantOperations = new JMenu("Operations");
		mnRestaurantOperations.setMnemonic(KeyEvent.VK_O);
		mnRestaurantOperations.setDisplayedMnemonicIndex(0);
		menuBar.add(mnRestaurantOperations);
		
		mntmOpenRestaurant = new JMenuItem("Open Restaurant");
		mnRestaurantOperations.add(mntmOpenRestaurant);
		
		mntmCloseRestaurant = new JMenuItem("Close Restaurant");
		mnRestaurantOperations.add(mntmCloseRestaurant);
		
		JSeparator separator = new JSeparator();
		mnRestaurantOperations.add(separator);
		
		mntmExitApplication = new JMenuItem("Exit Application");
		mnRestaurantOperations.add(mntmExitApplication);
		
		JMenu mnreports = new JMenu("Reports");
		mnreports.setMnemonic(KeyEvent.VK_R);
		mnreports.setDisplayedMnemonicIndex(0);
		menuBar.add(mnreports);
		
		mntmStatusReport = new JMenuItem("Status Report");
		mnreports.add(mntmStatusReport);
		
		mntmShowBill_1 = new JMenuItem("Show Bill - Table 1");
		mnreports.add(mntmShowBill_1);
		
		mntmShowBill_2 = new JMenuItem("Show Bill - Table 2");
		mnreports.add(mntmShowBill_2);
		
		mntmShowBill_3 = new JMenuItem("Show Bill - Table 3");
		mnreports.add(mntmShowBill_3);
		
		JSeparator separator_1 = new JSeparator();
		mnreports.add(separator_1);
		
		 mntmGenerateReport = new JMenuItem("Generate Report");
		mnreports.add(mntmGenerateReport);

	}

	public ReportDisplayView getReportViewer(){
		return reportdisplayview;
	}
	
	public void addOpenRestaurantListener (ActionListener al) {
		mntmOpenRestaurant.addActionListener(al);
	}

	public void addCloseRestaurantListener (ActionListener al) {
		mntmCloseRestaurant.addActionListener(al);
	}
	
	public void addExitApplicationListener (ActionListener al) {
		mntmExitApplication.addActionListener(al);
	}
	
	public void addShowBill1Listener (ActionListener al) {
		mntmShowBill_1.addActionListener(al);
	}
	
	
	
	
}

package view;

import java.util.ArrayList;

import interfaces.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ActivityLog;
import model.AllOrders;
import model.DeliverOrder;
import model.Order;
import model.ReceiveOrder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JList;

import java.awt.GridLayout;

import javax.swing.JLabel;

public class LogListView extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	private JList<String> orderlist;
	private DefaultListModel<String> model;
	/**
	 * @wbp.nonvisual location=103,284
	 */

	private JLabel labeltotorder = new JLabel("Total Orders: ");
	private JLabel labeltitle = new JLabel("A c t i v i t y      L o g");

	public LogListView(ReceiveOrder[] receiveorders,DeliverOrder[] deliverorders) {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		add(scrollPane, BorderLayout.CENTER);
		
		orderlist = new JList<String>();
		scrollPane.setViewportView(orderlist);
		add(labeltitle,BorderLayout.NORTH);
		add(labeltotorder,BorderLayout.SOUTH);


		for (int i=0; i < receiveorders.length; i++){
			receiveorders[i].registerObserver(this);
		}
		
		for (int i=0; i < deliverorders.length; i++){
			deliverorders[i].registerObserver(this);
		}

	}


	@Override
	public  synchronized void update(ArrayList<Order> activeorders,ArrayList<Order> deliveredorders) {
		model = new DefaultListModel<String>();

	    for(String item: ActivityLog.getInstance().getLogArray()){
	         model.addElement(item);

	    }    
	    
	    orderlist.setModel(model);     
	    orderlist.setSelectedIndex(0);
	    labeltotorder.setText("Total Orders: " + (activeorders.size() + deliveredorders.size()) );
		
	}
}

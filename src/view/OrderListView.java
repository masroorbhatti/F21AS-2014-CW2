package view;

import java.util.ArrayList;

import interfaces.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

public class OrderListView extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	private JList<String> orderlist;
	private DefaultListModel<String> model;
	/**
	 * @wbp.nonvisual location=103,284
	 */

	private JLabel labelpend = new JLabel("Pending Orders: ");
	private JLabel labeltitle = new JLabel("C e n t r a l   K i t c h e n");

	public OrderListView(ReceiveOrder[] receiveorders) {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		add(scrollPane, BorderLayout.CENTER);
		
		orderlist = new JList<String>();
		scrollPane.setViewportView(orderlist);
		add(labeltitle,BorderLayout.NORTH);
		add(labelpend,BorderLayout.SOUTH);


		for (int i=0; i < receiveorders.length; i++){
			receiveorders[i].registerObserver(this);
		}
		update(new ArrayList<Order>());
	
	}

	private String addHeader(){
		String header="";
		header += String.format("%-5s", "ID");
		header+= String.format("%-37s", "Item");
		header+= String.format("%-5s", "Qty");
		header+= String.format("%-7s", "TableNo");
		return (header);
	}
	@Override
	public  synchronized void update(ArrayList<Order> activeorders) {
		model = new DefaultListModel<String>();
		int countorder=0;
		model.addElement(addHeader());
	    for(Order ord: activeorders){
	         model.addElement(ord.getOrderData());
	         countorder++;
	    }    
	    
	    orderlist.setModel(model);     
	    orderlist.setSelectedIndex(0);
	    labelpend.setText("Pending Orders: " + countorder);
		
	}
}

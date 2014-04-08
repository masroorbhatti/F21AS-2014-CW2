package view;

import java.util.ArrayList;

import interfaces.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DeliverOrder;
import model.Order;
import model.ReceiveOrder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JList;

import java.awt.GridLayout;

public class OrderListView extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	private JList<String> orderlist;
	private DefaultListModel<String> model;
	
	public OrderListView(ReceiveOrder[] receiveorders, DeliverOrder[] deliverorder) {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		orderlist = new JList<String>();
		scrollPane.setViewportView(orderlist);

		for (int i=0; i < receiveorders.length; i++){
			receiveorders[i].registerObserver(this);
		}
		
//		for (int i=0; i < deliverorder.length; i++){
//			deliverorder[i].registerObserver(this);
//		}

		
	
	}

	private String addHeader(){
		return ("ID   Item            Qty   Table");
	}
	@Override
	public  synchronized void update(ArrayList<Order> activeorders) {
		model = new DefaultListModel<String>();
		model.addElement(addHeader());
	    for(Order ord: activeorders){
	         model.addElement(ord.getOrderData());
	    }    
	    
	    orderlist.setModel(model);     
	    orderlist.setSelectedIndex(0);

		
	}
}

package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import interfaces.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DeliverOrder;
import model.Order;

public class TableListView extends JPanel  implements Observer{

	
	private JList deliverlist;
	private DefaultListModel<String> model;
	private int tableno;
	/**
	 * Create the panel.
	 */
	public TableListView(DeliverOrder[] deliverorders, int tableno) {
		this.tableno=tableno;
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		deliverlist = new JList();
		scrollPane.setViewportView(deliverlist);
		
		for (int i=0; i < deliverorders.length; i++){
			deliverorders[i].registerObserver(this);
		}
	}
	
	private String addHeader(){
		return ("Table " + tableno);
	}
	
	
	@Override
	public synchronized  void update(ArrayList<Order> orders) {
		model = new DefaultListModel<String>();
		model.addElement(addHeader());
	    for(Order ord: orders){
	        if (ord.getTable().getTableno() == tableno) { 
	        	model.addElement(ord.getOrderData());
	        }
	    }    
	    
	    deliverlist.setModel(model);     
	    deliverlist.setSelectedIndex(0);
		
	}

}

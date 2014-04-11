package model;

import java.util.ArrayList;

public class AllOrders {
	private static final  AllOrders instance = new AllOrders();
	private ArrayList<Order> orders;
	private ArrayList<Order> activeorders;
	private ArrayList<Order> deliveredorders;
	private int ordernumber;
	
	private AllOrders(){
		orders = new ArrayList<Order>();
		activeorders = new ArrayList<Order>();
		deliveredorders = new ArrayList<Order>();
		ordernumber=0;
	}
	
	/**
	 * Getting singleton based object reference
	 * @return AllOrders object 
	 */
	public static AllOrders getInstance() {
		return instance;
	}
	
	/**
	 * To get total size of the orders
	 * @return
	 */
	public int getSize(){
		return orders.size();
	}
	
	/**
	 * It is used to get Next Order from file and if the file is finished it 
	 * generates random order number
	 * @return
	 */
	public synchronized Order getNextOrder(){
		Order ord=null;
		if (ordernumber < orders.size()){
			++ordernumber;
			ord=orders.get(ordernumber);
			activeorders.add(ord);
		}
		else {
			ord = new Order();
			orders.add(ord);
			activeorders.add(ord);
			ordernumber++;
		}
		return ord;
	}

	/**
	 * Used to delever order table and remove the entry from oderlist
	 * @return
	 */
	public synchronized Order deliverOrderToTable(){
		Order ord = null;
		if (activeorders.size() > 0){
			ord = activeorders.get(0);
			activeorders.remove(0);
			deliveredorders.add(ord);
		}
		return ord;
		
	}
	
	/**
	 * Used to add order
	 * @param ord
	 */
	public void addOrder(Order ord){
		orders.add(ord);
	}
	
	/**
	 * To get Active Orders list
	 * @return
	 */
	public ArrayList<Order> getActiveOrders(){
		return activeorders;
	}

	/**
	 * To get Delivered Order List
	 * @return
	 */
	public ArrayList<Order> getDeliveredOrders(){
		return deliveredorders;
	}

}
package model;

import java.util.ArrayList;

public class AllOrders {
	private static final  AllOrders instance = new AllOrders();
	private ArrayList<Order> orders;
	private ArrayList<Order> activeorders;
	private int ordernumber;
	
	private AllOrders(){
		orders = new ArrayList<Order>();
		ordernumber=0;
	}
	
	/**
	 * Getting singleton based object reference
	 * @return AllOrders object 
	 */
	public static AllOrders getInstance() {
		return instance;
	}
	
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

	public synchronized Order removeOrder(){
		Order ord = null;
		if (activeorders.size() > 0){
			ord = activeorders.get(0);
			activeorders.remove(0);
		}
		return ord;
		
	}
	
	public void addOrder(Order ord){
		orders.add(ord);
	}
	


}
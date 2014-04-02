package model;


/***
 * implementing OrederNumber based on Eager initialization
 * Thread safe implementation, as object is created in the start
 * Good approach to use eager initialization, as order number will not have a heavy 
 * object regarding resources, eager can be best option
 */

public class OrderNumber {

	private static final OrderNumber instance = new OrderNumber();
	private int ordernumber;
	
	private OrderNumber() {
		ordernumber=0;
	}
	public static OrderNumber getInstance() {
		return instance;
	}	
	
	public int getNextOrderNumber(){
		return ++ordernumber;
	}
}

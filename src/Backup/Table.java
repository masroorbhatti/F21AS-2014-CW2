package Backup;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Table {
	private int tableno;
	private boolean isReserved;
	private Set<Order> orders ;
	private double discount;
	private Iterator<Order> orderIterator;

	/**
	 *  Public default constructor for Table class
	 */
	public Table(){
		setTableno(Global.getNewTableNo());
		setReserved(false);
		orders = new TreeSet<Order>();
		setDiscount(0);
	}

	/**
	 * @return the tableno
	 */
	public int getTableno() {
		return tableno;
	}

	/**
	 * @param tableno the tableno to set
	 */
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}

	/**
	 * @return the isReserved
	 */
	public boolean isReserved() {
		return isReserved;
	}

	/**
	 * @param isReserved the isReserved to set
	 */
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 *  public method to add new order in current table
	 * @param order to be added to orders treeset
	 */
	public void addOrder(Order order){
		orders.add(order);
		discount += order.getOrderDiscount();

	}
	
	/**
	 *  public method to cancel order from current table 
	 * @param order to be canceled from orders treeset
	 */
	public void cancelOrder(Order order){
		orders.remove(order);
	}
	
	/**
	 * Update table isReserved status
	 */
	public void toggleTableStatus(){
		if (isReserved == true) 
			isReserved = false;
		else
			isReserved=true;
	}
	
	/**
	 * Public method to return total ordered for current table
	 * @return int value for total orders
	 */
	public int getTotalOrders(){
		return (orders.size());
		
	}
	
	/**
	 *  Public method to return total generated bill for table
	 * @return double value  for bill amount
	 */
	public double getTotalBill(){
		double billamount = 0.0;
		orderIterator = orders.iterator();
		while (orderIterator.hasNext()){
			
			billamount += orderIterator.next().getOrderPrice();
			
		}
		return billamount;
	}
	
	
	/**
	 *  Public method to return total generated bill with discount for table
	 * @return double value  for bill amount
	 */
	public double getTotalDiscountedBill(){
		double billamount = 0.0;
		orderIterator = orders.iterator();
		while (orderIterator.hasNext()){
			
			billamount += orderIterator.next().getOrderPriceWithDiscount();
			
		}
		return billamount;
	}
	/**
	 *  Public method to return Orders For Current Table
	 * @return double value  for bill amount
	 */
	public String getOrderdItemDetails(){
		String report = "";
		String tablestatus  = "";
		if(this.isReserved == false){
			report = "No Customer found on this Table";
		}
		else{
			tablestatus = "Occupied";
		
			report += "Table "+this.getTableno()+ "(" + tablestatus+ ")\n";
			report += "-------\n";
			for(Order or : orders){
				report += String.format("%-25s",or.getItem().getItemName() +"");
				report += String.format("%-5s",or.getQty());
				report += String.format("%-5s","  *   ");
				report += String.format("%-15s",or.getItem().getPrice() + "(" + Global.discountlistgl.get(or.getItem().getCategory()) + "%)");
				report += String.format("%-5s","  =  ");
				report += String.format("%-1s",or.getOrderPriceWithDiscount());
				report += "\n";
	
			}
			report += String.format("%-56s","");
			report += "-----\n";
			report += String.format("%-56s","Total For This Table");
			report += String.format("%-1s",this.getTotalBill() + "\n");
			report += String.format("%-56s","Discount");
			report += String.format("%-1s", this.getDiscount() + "\n");
			report += String.format("%-56s","Discounted Total");
			report += String.format("%-1s",this.getTotalDiscountedBill() + "\n\n");
		}
		
		return report;
	}


	/**
	 *  Public method to return total bill payable (after discount)
	 * @return double value  for bill amount payable
	 */
	
	public double getTotalBillPayable(){
		double billamount = 0.0;
		billamount = getTotalBill();
		billamount-=getDiscount();
		return billamount;
	}

}

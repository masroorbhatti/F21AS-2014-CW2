package model;

import java.util.Random;


public class Order implements Comparable<Order>  {

	private int ordernumber;
	private Table table;
	private Item item;
	private int qty;

	
	/***
	 * Public constructor for Order class 
	 */	
	public Order(Table table, Item item, int qty){
		
		if(table == null || item == null || qty == 0){
			throw new IllegalArgumentException("Paramerters cannot be null in class Order");
		}
		setOrdernumber(OrderNumber.getInstance().getNextOrderNumber());
		setTable(table); 
		setItem(item);
		setQty(qty);
		AllTables.getInstance().getTable(table.getTableno()).addOrder(this);
	}
	public Order(){
		setOrdernumber(OrderNumber.getInstance().getNextOrderNumber());
		setTable(getRandomTable()); 
		setItem(getRandomItem());
		setQty(new Random().nextInt(5));
		AllTables.getInstance().getTable(table.getTableno()).addOrder(this);
	}

	private Item getRandomItem(){
		Random generator = new Random();
		int randitemno = generator.nextInt(AllItems.getInstance().getSize());
		return (AllItems.getInstance().getItem(randitemno));
	}
	
	private Table getRandomTable(){
		Random generator = new Random();
		int randtableno = generator.nextInt(AllTables.getInstance().getSize());
		return (AllTables.getInstance().getTable(randtableno) );
	}	
	
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @return the ordernumber
	 */
	public int getOrdernumber() {
		return ordernumber;
	}

	/**
	 * @param ordernumber the ordernumber to set
	 */
	private void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	/***
	 * implementing compare to function.
	 */
	@Override
	public int compareTo(Order order) {
		
		int retval=0;
		if (this.ordernumber > order.ordernumber) retval=1;
		else if (this.ordernumber < order.ordernumber) retval=-1;
		else retval=0;
		
        return retval;
	}
	
	/**
	 * Public method to return price of order
	 * @return double value for order price
	 */
	public double getOrderPrice(){
		double orderprice=0.0;
		orderprice = item.getPrice() * qty;
		return orderprice;
	}

}


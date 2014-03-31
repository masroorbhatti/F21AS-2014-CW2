package model;

public class Order implements Comparable<Order>  {

	private int ordernumber;
	private Table table;
	private Item item;
	private int qty;
	private IOClass io;
	
	/***
	 * Public constructor for Order class 
	 */	
	public Order(Table table, Item item, int qty , IOClass io){
		
		if(table == null || item == null || qty == 0){
			throw new IllegalArgumentException("Paramerters cannot be null in class Order");
		}
		setOrdernumber(Global.getNewOrderNo());
		setTable(table); 
		setItem(item);
		setQty(qty);
		this.io = io;
		//io.updateView(getOrdernumber());
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
	 * 
	 * @param itemname for creating item
	 * @param categorydetail for creating item
	 * @param price for creating item
	 */
	public void setItem(String itemname, String categorydetail, double price) {
		this.item = new Item(itemname, categorydetail, price);
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
	
	/**
	 * Public method to return price of order
	 * @return double value for order price
	 */
	public double getOrderPriceWithDiscount(){
		double orderprice=0.0;
		orderprice = item.geDiscountedtPrice() * qty;
		return orderprice;
	}
	
	/**
	 * method to return the discount on current order
	 * @return double value for discount
	 */
	public double getOrderDiscount(){
		double ordprice = getOrderPrice();
		double orddiscount = ordprice * Global.discountlistgl.get(this.item.getCategory()) / 100;
		return orddiscount;	
		}
	
	

}

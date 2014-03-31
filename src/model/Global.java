package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Global {

	/**
	 * Public static variables to maintain value throughout all objects 
	 */
	public static int tableno;
	public static int orderno;
	public static HashMap <String,Double> discountlistgl = new HashMap <String,Double>();
	public static AllTables resttables = new AllTables();
	public static Allitems al;  

	/**
	 * static method to return last table no
	 * @return last tableno
	 */
	public static int getTableNo(){
		return tableno;
	}

	/**
	 * static method to return last order no
	 * @return last orderno
	 */
	public static int getOrderNo(){
		return orderno;
	}

	/**
	 * public method to get new order no, used in order constructor
	 * @return int value for new orderno
	 */
	public static int getNewOrderNo(){
		return orderno++;
	}

	/**
	 * public method to get new table no, used in table constructor
	 * @return int value for new tableno
	 */
	public static int getNewTableNo(){
		return tableno++;
	}

}
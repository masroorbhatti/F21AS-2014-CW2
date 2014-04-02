package Backup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class AllTables {
	
	private final int TOT_TABLES = 10;				// constant for total number of tables
	private HashMap <Integer, Table> alltables;		// HashMap for storing all tables
	Iterator<Integer> tablesIterator;				// Iterator to iterate through HashMap 

	/**
	 * Default constructor for table class
	 */
	public AllTables(){
		
		alltables = new HashMap<Integer,Table>();
		initializeTables();
		
	}
	
	/**
	 *  private method to initialize HashMap for tables
	 */
	private void initializeTables(){
		for (int i = 0; i < TOT_TABLES; i++){
			Table tmptable = new Table();
			alltables.put(tmptable.getTableno(), tmptable);
		}
	}
	
	/**
	 *  Public method returns total occupied tables (reserved)
	 * @return int value
	 */
	public int getTotalOccupiedTables(){
		int retval=0;
		 tablesIterator = alltables.keySet().iterator();
		while (tablesIterator.hasNext()){
			Integer tableno = tablesIterator.next();
			if (alltables.get(tableno).isReserved() == true){
				retval++ ;
			}
		}
		return retval;
	}
	
	/**
	 *  Public method returns total empty tables (not reserved)
	 * @return int val
	 */
	public int getTotalEmptyTables(){
		int retval=0;
		 tablesIterator = alltables.keySet().iterator();
		while (tablesIterator.hasNext()){
			Integer tableno = tablesIterator.next();
			if (alltables.get(tableno).isReserved() == false){
				retval++ ;
			}
		}
		return retval;
	}
	
	/**
	 * Public method to get list of all occupied tables
	 * @return ArrayList of Table class
	 */
	public ArrayList<Table> getOccupiedTableList(){
		ArrayList<Table> retlist = new ArrayList<Table>();
		 tablesIterator = alltables.keySet().iterator();
		 Integer tableno;
		 Table tmptable;
		while (tablesIterator.hasNext()){
			tableno = tablesIterator.next();
			tmptable = alltables.get(tableno);
			
			if (tmptable.isReserved() == true){
				retlist.add(tmptable) ;
			}
		}
		return retlist;
	}
	
	/**
	 * Public method to get table object with highest number of orders
	 * @return Table class object
	 */
	public Table getTableWithMostOrders(){
		Table rettable = null;
		 tablesIterator = alltables.keySet().iterator();	// Iterator to iterate map
		 Integer tableno;
		 Table tmptable;
		 int tmporderqty=0;				//maintaining max orders to used in comparison 
		 int tmptableno=0;				//maintaining table no of highest orders  
		while (tablesIterator.hasNext()){
			tableno = tablesIterator.next();
			tmptable = alltables.get(tableno);		
			if (tmptable.getTotalOrders() > tmporderqty)
				tmptableno = tableno;
		}
		if (tmptableno != 0)
			rettable = alltables.get(tmptableno);
		
		
		return rettable;
	}
	
	/**
	 * Method to get report of table with most orders
	 * @return String report
	 */
	public String getReportOfTableWithMostOrders(){
		Table tb = this.getTableWithMostOrders();
		String report = "===============_Details of Table With Most Orders_============\n\n";
		report += tb.getOrderdItemDetails();
		report += "\n=============================_END_============================\n\n\n\n";
		return report;
		
	}
	
	/**
	 * Public method to get table object with highest number of orders
	 * @return Table class object
	 */
	public String getOccupiedTableRecords(){
		String report = "";
		 tablesIterator = alltables.keySet().iterator();	// Iterator to iterate map
		 Integer tableno;
		 Table tmptable;
		 int tmporderqty=0;				//maintaining max orders to used in comparison 
		 int tmptableno=0;				//maintaining table no of highest orders  
		 report += "===============_Cost Of ALL Order For Table Used_================\n\n";
		while (tablesIterator.hasNext()){
			tableno = tablesIterator.next();
			tmptable = alltables.get(tableno);	
			if (tmptable.isReserved() == true){
				report += tmptable.getOrderdItemDetails();
			}
		}
		report += "==============================_END_================================\n\n\n\n";
		return report;
	}
	
	
	/**
	 * Public method to get table object with highest bill
	 * @return Table class object
	 */
	public Table getTableWithHighestBill(){
		Table rettable = null;
		 tablesIterator = alltables.keySet().iterator();	// Iterator to iterate map
		 Integer tableno;
		 Table tmptable;
		 double tmpbillamount=0;				//maintaining highest bill amount to used in comparison 
		 int tmptableno=0;					//maintaining table no of highest bill  
		while (tablesIterator.hasNext()){
			tableno = tablesIterator.next();
			tmptable = alltables.get(tableno);		
			if (tmptable.getTotalBill() > tmpbillamount)
				tmptableno = tableno;
		}
		if (tmptableno != 0)
			rettable = alltables.get(tmptableno);
		
		return rettable;
	}
	
	/**
	 * Public method to get report of table with highest bill
	 * @return String report
	 */
	public String getReportOfTableWithHighestBill(){
		Table tb = this.getTableWithHighestBill();
		String report = "==============_Details of Table With Highest Bill_============ \n\n";
		report += tb.getOrderdItemDetails();
		report += "\n==============================_END_===========================\n\n\n\n";

		return report;
		
	}
	
	/**
	 * Public method to return total bill for table based on table no
	 * @param tableno 
	 * @return double bill amount
	 */
	public double getTotalBill(int tableno){
		
		return (alltables.get(tableno).getTotalBill());
	}
	
	/**
	 * Public method to return total bill payable for table based on table no
	 * @param tableno 
	 * @return double bill amount
	 */
	public double getTotalBillPayable(int tableno){
		
		return (alltables.get(tableno).getTotalBillPayable());
	}
	
	/**
	 * Public method to return total number of tables
	 * @return int value for total tables
	 */
	public int getTotalNumberOfTables(){	
	
		return (alltables.size());		 // same Global.tableno;
	}
	
	/**
	 * Public method to search table based on  table no
	 * @param tableno 
	 * @return Table object
	 */
	public Table getTable(int tableno){
		
		return (alltables.get(tableno));
	}
}

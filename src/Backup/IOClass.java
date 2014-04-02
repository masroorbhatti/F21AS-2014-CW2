package Backup;
//import pl.AllTables;
//import pl.Allitems;
//import pl.Global;
//import pl.Order;
//import pl.Item;
//import pl.Table;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.TreeSet;

import model.Item;
import model.Table;

public class IOClass  extends Observable implements  Runnable{

	private ArrayList<Order> orderList;
	private ArrayList<Item> itemList;
	private ArrayList<Table> tableList;
	private AllOrders ao; 
	private Thread [] orderThreads;
	/**
	 * Constructor initializing orderlist and itemlist
	 * @throws FileNotFoundException 
	 */
	public IOClass(){
		orderList = new ArrayList<Order>();
		itemList = new ArrayList<Item>();
		tableList = new ArrayList<Table>();
		

	}

	/**
	 * Method used to read data from TableData and MenuData file
	 * @param filename
	 * @param filetype
	 * @throws FileNotFoundException
	 */
	public void inputFromFile(String filename,String filetype) throws FileNotFoundException	{
		try {
			File f = new File(filename);
			Scanner fr = new Scanner(f);
			while (fr.hasNextLine()) {

				//read first line and process it
				String inputLine = fr.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					if(filetype == "order")
						processOrder(inputLine);
					else if(filetype == "discount")
						processDiscount(inputLine);
					else
						processMenu(inputLine);
				}
			}

		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			throw fnf ;
		}
	}

	/**
	 * Method used to process each line from TabeData file
	 * @param line
	 */
	private void processOrder(String line) {
		try {
			String parts [] = line.split(",");
			int itemexist = 0;

			int tableno = Integer.parseInt(parts[0]);
			String itemname = parts[1];
			int quantity = Integer.parseInt(parts[2]);

			//Searching if item exist in menu file
			TreeSet<Item> al = Global.al.getAllItems();
			for(Item it : al){
				if(it.getItemName().equals(itemname)){
					itemexist = 1;
				}
			}

			Allitems ai = Global.al;

			Table tmptable = Global.resttables.getTable(tableno);
			//tmptable.setDiscount(discount);
			tmptable.setReserved(true);

			if(!(itemname.isEmpty()) && itemexist != 0){
//				tmptable.addOrder(new Order(tmptable, ai.getItemFromName(itemname),quantity,this));
				Order or = new Order(tmptable,ai.getItemFromName(itemname),quantity,this);
				this.addOrder(or);
				//this.updateView(or.getOrdernumber());
				ArrayList<Order> orderlistgl = getOrderList();
				ao = new AllOrders(orderlistgl);
				Global.al.setOrderedItemList(ao);
				//update view display
				this.updateView();
				
			}
			else{
				System.out.println("Item Name was not provided in TableData.csv");
			}

		}

		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}

	/**
	 * Method used to prcoess each line from MenuData file
	 * @param line
	 */
	private void processMenu(String line) {
		try {
			String parts [] = line.split(",");
			String dishname = parts[0];
			double price = Double.parseDouble(parts[1]);
			String category = parts[2];



			if(!dishname.isEmpty() || !category.isEmpty()){
				Item it = new Item(dishname, category ,price);
				this.addItem(it);
			}
			else{
				System.out.println("Record Ignored as Item Name / Category was not provided in MenuData.csv");
			}

		}


		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}

	/**
	 * Method used to prcoess each line from MenuData file
	 * @param line
	 */
	private void processDiscount(String line) {
		try {
			String parts [] = line.split(",");
			String itemcategory = parts[0];
			double discount = Double.parseDouble(parts[1]);
			if(discount > 100){
				System.out.println("Discount Cannot be greater then 100%");
			}
			else
				Global.discountlistgl.put(itemcategory, discount);	

		}


		//this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}


	}

	/**
	 * Method used to write report to a file named as Output.txt
	 * @param report
	 * @param append
	 */
	public void writeToFile(String report,boolean append)
	{
		String filename = "Output.txt";
		FileWriter fw;
		try{
			fw = new FileWriter(filename,append);
			fw.write(report);
			fw.close();
		}
		catch (FileNotFoundException fnf)
		{
			 System.out.println(filename + " not found ");
			 System.exit(0);
		}
		catch (Exception ioe)
		{
			ioe.printStackTrace();
		    System.exit(1);
		}		

	}

	/**
	 * Method used to add order object into an Arraylist named as orderList 
	 * @param or
	 */
	public void addOrder( Order or){
		orderList.add(or);		
	}

	/**
	 * Method used to add item object into an Arraylist name as itemList 
	 * @param it
	 */
	public void addItem( Item it){
		itemList.add(it);	
	}

	/**
	 * Method used to add item object into an Arraylist name as itemList 
	 * @param it
	 */
	public void addTable( Table tb){
		tableList.add(tb);	
	}

	/**
	 * Method used to return itemlist
	 * @return
	 */
	public ArrayList<Item> getItemList(){
		return this.itemList;
	}

	/**
	 * Method used to return orderlist
	 * @return
	 */
	public ArrayList<Order> getOrderList(){
		return this.orderList;
	}
	/**
	 * Method used to return tableList
	 * @return
	 */
	public ArrayList<Table> getTableList(){
		return this.tableList;
	}

	public void run() {
		
		//getting item details from menu file
				try {
					inputFromFile("MenuData.csv", "menu");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Item> itemlistgl = getItemList();
				Global.al = new Allitems(itemlistgl);
			
				try {
					inputFromFile("Discount.csv", "discount");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//getting order details from table data file
				try {
					inputFromFile("TableData.csv", "order");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
	}
	public String getTestOutput(){
		
    	return ao.getallOrders();
	
	}


	public synchronized void updateView(){
		setChanged();
		notifyObservers();
		clearChanged();
	}


}
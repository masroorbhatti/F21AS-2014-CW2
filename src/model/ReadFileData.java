package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class ReadFileData {

	private final String orderfilename = "orderdata.csv";
	private final String menufilename = "menudata.csv";
	private File file;
	public ReadFileData(){

	}

	
	public void readMenuFile() throws FileNotFoundException	{
		try {
			file = new File(menufilename);
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
						processMenu(inputLine);
				}
				read.close();
			}
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			throw fnf ;
		}
	}
	
	/**
	 * Method used to process each line from MenuData file
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
				AllItems.getInstance().addItem(it);
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
	
	
	public void readOrderFile() throws FileNotFoundException	{
		try {
			file = new File(orderfilename);
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				//read first line and process it
				String inputLine = read.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
						processOrder(inputLine);
				}
				read.close();
			}
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			throw fnf ;
		}
	}
	private void processOrder(String line) {
		try {
			String parts [] = line.split(",");
			int itemexist = 0;

			int tableno = Integer.parseInt(parts[0]);
			String itemname = parts[1];
			int quantity = Integer.parseInt(parts[2]);

			//Searching if item exist in menu file
			TreeSet<Item> al = AllItems.getInstance().getAllItems();
			for(Item it : al){
				if(it.getItemName().equals(itemname)){
					itemexist = 1;
					break;
				}
			}


			Table tmptable = AllTables.getInstance().getTable(tableno);
			tmptable.setReserved(true);

			if(!(itemname.isEmpty()) && itemexist != 0){

				Order or = new Order(tmptable,AllItems.getInstance().getItemFromName(itemname),quantity);
				tmptable.addOrder(or);				
				AllOrders.getInstance().addOrder(or);
			}
			else{
				System.out.println("Item Name was not provided in OrderData.csv");
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
	

}

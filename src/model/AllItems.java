package model;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;

import exceptions.EmptyValue;
import model.Item;

public class AllItems  {
	private static final AllItems instance = new AllItems();
	private TreeSet <Item> allitems;    	// TreeSet for storing allitems
	Iterator<Item> itemsIterator;			//Iterator used to iterator through the treeset 

	/***
	 * Constructor 
	 */

	private AllItems() {		
		
		allitems = new TreeSet<Item>();
		itemsIterator = allitems.iterator();
	}  

	public void addItem(Item item){
		allitems.add(item);
	}
	
	
	public static AllItems getInstance() {
		return instance;
	}
	
	public TreeSet<Item> getAllItems(){
		return allitems;
	}

	/***
	 * 	Method to getItemList			
	 * @return
	 */
	public String getItemListByCategory(String category){

		String report="";	
		report += ("----------------------------MAIN MENU--------------------------");

		report += String.format("%-1s",category + "\n");
		report += String.format("%-19s","-----------------------------\n");
		for(Item it : allitems){
			if(category.equals(it.getCategory())){
				report += System.lineSeparator();
				report += String.format("%-5s"," ");
				report += String.format("%-19s",it.getItemName());
				report += String.format("%-19s",it.getPrice());
			}
		}

		report += "\n\n";
		return report;

	}

	/***
	 * 	Method to getItemList			
	 * @return
	 */
	public String getAllItemListAccToCat(){

		String[] categorylist = this.getCategoryList();
		String report="";	
		report += ("============_MENU_===========\n\n");
		for(int i=0;i<categorylist.length;i++){
			report += this.getItemListByCategory(categorylist[i]);
		}
		report += "\n===========_END_============";
		report += "\n\n\n\n";
		return report;

	}

	/***
	 * 	Method to getItemList			
	 * @return
	 */
	public String[] getCategoryList(){

		String[] categorylist = new String[20];

		int count=0;
		int repeat = 0;
		while(itemsIterator.hasNext()){
			Item o1 =  (Item) ((Iterator<Item>) itemsIterator).next();
				for(int i=0;i<categorylist.length;i++){
					if(categorylist[i] != null)
					{
						if(categorylist[i].equals(o1.getCategory())){
							repeat = 1;
						}
					}
				}
				if(repeat == 0){
					categorylist[count] = o1.getCategory();
					count++;
				}else{
					repeat = 0;
				}
		}
		ArrayList<String> list = new ArrayList<String>();

	    for(String s : categorylist) {
	       if(s != null && s.length() > 0) {
	          list.add(s);
	       }
	    }

	    categorylist = list.toArray(new String[list.size()]);
		return categorylist;

	}

	/***
	 *  Method to get Item With Highest Price
	 * @return
	 */
	public double getItemwithHighestPrice(){
		double max = Double.MIN_VALUE;
		for (Item it : allitems) {
			double os = it.getPrice();
			if (os> max) 
			{
				max= os;

			}
		}	
		return max ;

	}
	
	/***
	 * 			
	 * public Method to fetch item by itemname
	 * @return
	 */
	public Item getItemFromName(String itemname){
		 try
		 {
		     if(itemname.isEmpty())
		     {
		          throw new EmptyValue("Item Name is Empty in getItemFromName");
		     }
		 }
		 catch(EmptyValue ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		Item retitem = null;
		for(Item it : allitems){
			if(it.getItemName().equals(itemname)){
				retitem=it;

			}
		}
		return retitem;

	}
	

}
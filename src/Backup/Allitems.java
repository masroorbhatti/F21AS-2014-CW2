package Backup;
import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

//import pl.Order;
//import pl.AllOrders;
//import pl.EmptyValue;;
public class Allitems  {
	private TreeSet <Item> allitems;    	// TreeSet for storing allitems
	Iterator<Item> itemsIterator;			//Iterator used to iterator through the treeset 
	private Item selectedItem;
	private HashSet<Item> unorderditems;	// HashSet Storing value of unordered items
	private HashSet<Item> orderditems;	// HashSet Storing value of ordered Items



	/***
	 * Constructor 
	 */

	public Allitems(ArrayList<Item> itemlistgl) {		

		allitems = new TreeSet<Item>(itemlistgl);
		itemsIterator = allitems.iterator();

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
	//	report += ("----------------------------MAIN MENU--------------------------");

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

		String report="";	
		int count=0;
		int repeat = 0;
		report += ("---------------------------- MENU --------------------------");
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

	/***
	 * 	public method to return ordered items
	 * @return
	 */
	public void setOrderedItemList( AllOrders ord){
		orderditems = new HashSet<Item>();
		unorderditems = new HashSet<Item>();
		
		for(Item it : Global.al.getAllItems()){
			unorderditems.add(it);
		}
		
		Iterator<Integer> itorder = ord.getOrders().keySet().iterator();

		while (itorder.hasNext()){

			Integer orderno = itorder.next();
			Item itm = ord.getOrders().get(orderno).getItem();
			orderditems.add(itm);
			unorderditems.remove(itm);

		}

	}
	public String getOrderedItemList(){
		String report = "";
		report +=("------Ordered Items---------\n");

		for(Item vl : orderditems){
			report +=(vl.getItemName() + "\n");

		}

		return report;

	}
	/***
	 * 	Public method to return unordered items
	 * @return
	 */
	public String getUnorderedItemList(){
		String report="";
		report +=("======_DISHES NOT ORDERED_======\n\n");
		
		for(Item vl : unorderditems){
			report +=(vl.getItemName() + "\n");

		}
		report +=("\n==============_END_=============\n\n\n\n");
		return report;

	}


}
package Backup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import model.Item;
public class AllOrders {
	private TreeMap<Integer ,Order> allorders;		// TreeMap For storing All Orders
	private HashSet<String> maxItems;		
	private TreeMap<String ,Integer> maxorders;
	private Iterator<Integer> ordersIterator;				// Iterator to iterate  
	/***
	 * Constructor
	 */
	public AllOrders(ArrayList<Order> orderlistgl){
		allorders = new TreeMap<Integer,Order>();
		int c=1;
		for(Order o : orderlistgl){
			allorders.put(c, o);
			c++;
		}
	}
	public TreeMap<Integer,Order> getOrders(){
		return allorders;
	}
	/**
	 *  Public method returning  list of allorders
	 * @return String value
	 */
	public String getallOrders(){
		String report="";
		report += ("-------------------------------All Ordered Items--------------------------\n");
		report += ("Table No 	Item Name		Quantity	   Price	\n");
		ordersIterator = allorders.keySet().iterator();
		while (ordersIterator.hasNext()){
			
			Integer orderno = ordersIterator.next();

			report += String.format("%5s",allorders.get(orderno).getTable().getTableno()+"");
			report += String.format("%20s",allorders.get(orderno).getItem().getItemName()+"");
			report += String.format("%20s",allorders.get(orderno).getQty()+ "");
			report += String.format("%20s",allorders.get(orderno).getOrderPrice()+"");
			report += ("\n");
		}

		return report;
	}
	/***
	 * 	public method to get maxiumum ordered item
	 * @return
	 */


	public String getFrequency(){
		TreeMap<String,Integer> itemfreq = new TreeMap<String,Integer>();
		Iterator<Integer> oriterator = allorders.keySet().iterator();
		Integer val=0;
		String report = "";
		report +=("===========_Frequency Report_==========\n\n");
		while (oriterator.hasNext()){
			Integer orderno = oriterator.next();
			Item itm = allorders.get(orderno).getItem();
			if (itemfreq.get(itm.getItemName()) == null)
				itemfreq.put(itm.getItemName(), 1);
			else{
				val = itemfreq.get(itm.getItemName());
				itemfreq.put(itm.getItemName(), ++val);
			}
		}
//		return itemfreq;
		report += "Item Name                   Order Count\n";
		report += "---------                   -----------\n\n";
		for (Entry<String, Integer> entry : itemfreq.entrySet()) {
			 report += String.format("%-33s",entry.getKey());
			 report += String.format("%-15s",entry.getValue());
		     report += "\n";
		}
		report +=("\n================_END_==================\n\n\n\n");

		return report;

	}
	
	/*
	public String getMaxOrderedItem(){






		maxorders = new TreeMap<String,Integer>();

		int repeat = 0;
		String report="";
		report +=("===========_Max Ordered Item_==========\n\n");
		maxItems = new HashSet<String>();
		for(Order or : Global.orderlistgl){	
			for(Item it : Global.itemlistgl){
				if(or.getItem().getItemName().equals(it.getItemName())){
					for(String s : maxorders.keySet()){
						if(it.getItemName() == s){
							int currentcount = maxorders.get(it.getItemName());
							int newcount = currentcount + 1;
							maxorders.put(it.getItemName(), newcount);
							repeat = 1;
						}
					}
					if(repeat == 0){
						maxorders.put(it.getItemName(), 1);

					}
					else
						repeat = 0;

				}
			}
		}
		report += "Item Name                   Order Count\n";
		report += "---------                   -----------\n\n";
		for (Entry<String, Integer> entry : maxorders.entrySet()) {
			 report += String.format("%-33s",entry.getKey());
			 report += String.format("%-15s",entry.getValue());
		     report += "\n";
		}
		report +=("\n================_END_==================\n\n\n\n");

		return report;
	}
	
	*/
	public String getCostOfOrderForEachTable(int tbno){
		String report="";
		int count=0;
		double totalbill = 0.0;
		ordersIterator = allorders.keySet().iterator();
		while (ordersIterator.hasNext()){
			Integer orderno = ordersIterator.next();
			if(allorders.get(orderno).getTable().getTableno() == tbno){
			if(count == 0){
				report += ("-------------------------------Table No "+ allorders.get(orderno).getTable().getTableno() + " --------------------------\n");
				count = 1;
			}

			report += String.format("%20s",allorders.get(orderno).getItem().getItemName()+"");
			report += String.format("%20s",allorders.get(orderno).getQty()+ "  *   " + allorders.get(orderno).getItem().getPrice() +"  =  ");
			report += String.format("%20s",allorders.get(orderno).getOrderPrice());
			totalbill += allorders.get(orderno).getOrderPrice();
			report += ("\n");
			}
		}



		return report;
	}

}

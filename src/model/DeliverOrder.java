package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.Observer;
import interfaces.Subject;

public class DeliverOrder implements Subject,Runnable  {
	private long waittime=0;
	private Order ord=null;
	Restaurant restaurant;
	
	public DeliverOrder(long waittime,Restaurant restaurant){
		this.waittime=waittime;
		this.restaurant=restaurant;
	}

	public void run() {
		while ( (restaurant.isOpened()) || (AllOrders.getInstance().getActiveOrders().size() > 0) ){
			try{
				Thread.sleep(waittime);
				if (AllOrders.getInstance().getActiveOrders().size() > 0) {
					ord = AllOrders.getInstance().deliverOrderToTable();
					ActivityLog.getInstance().addLogRecord("Delivered order #: " + ord.getOrdernumber() + " to table #: " + ord.getTable().getTableno());
					AllTables.getInstance().getTable(ord.getTable().getTableno()).addOrder(ord);
					notifyObservers();		
				}
			}

			catch (InterruptedException e) {
				System.out.println(ord.getOrdernumber() + "  Interrupted");
			}
			catch (Exception e) {
				System.out.println("Order exc" + e.getStackTrace());
				System.out.println(ord.getOrdernumber() );
			}
		}
	}
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	//methods to register, remove and notify observers
	public void registerObserver( Observer obs)
	{
		registeredObservers.add( obs);
	}
	
	public void removeObserver( Observer obs)
	{
		registeredObservers.remove( obs);
	}
	
	public void notifyObservers()
	{
		for( Observer obs : registeredObservers)
			obs.update(AllOrders.getInstance().getDeliveredOrders());
	}
}

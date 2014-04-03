package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.Observer;
import interfaces.Subject;

public class ReceiveOrder implements Subject,Runnable {
	private long waittime;
	private Order ord=null;
	Restaurant restaurant;
	
	public ReceiveOrder(long waittime,Restaurant restaurant){
		this.waittime=waittime;
		this.restaurant=restaurant;
	}
	
	public void run() {
		while (restaurant.isOpened()){
			try {
				Thread.sleep(waittime);
				ord = AllOrders.getInstance().getNextOrder();
				ActivityLog.getInstance().addLogRecord("Recieved new order #: " + ord.getOrdernumber());
				notifyObservers();
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
			obs.update(AllOrders.getInstance().getActiveOrders());
	}


}

package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.Observer;
import interfaces.Subject;

public class ReceiveOrder implements Subject,Runnable {
	private long waittime;
	private Order ord=null;

	
	public ReceiveOrder(long waittime){
		this.waittime=waittime;

	}
	
	public void run() {
		while (RestaurantState.getInstance().isOpened()){
			try {
				Thread.sleep(waittime);
				ord = AllOrders.getInstance().getNextOrder();
				String strlog = "Recieved new order #: " + ord.getOrdernumber()+ " by " + Thread.currentThread().getName() ;
				ActivityLog.getInstance().addLogRecord(strlog);
				System.out.println(strlog);
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
		ActivityLog.getInstance().addLogRecord("Closing reataurant - New orders stoped");
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
			obs.update(AllOrders.getInstance().getActiveOrders(),AllOrders.getInstance().getDeliveredOrders());
	}


}

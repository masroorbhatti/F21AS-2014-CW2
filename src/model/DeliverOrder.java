package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.Observer;
import interfaces.Subject;

public class DeliverOrder implements Subject,Runnable  {
	private long waittime=0;
	private Order ord=null;
	
	public DeliverOrder(long waittime){
		this.waittime=waittime;
	}

	public void run() {
		while ( (RestaurantState.getInstance().isOpened()) || (AllOrders.getInstance().getActiveOrders().size() > 0) ){
			try{
				Thread.sleep(waittime);
				if (AllOrders.getInstance().getActiveOrders().size() > 0) {
					ord = AllOrders.getInstance().deliverOrderToTable();
					String strlog = "Delivered order #: " + ord.getOrdernumber() + " to table #: " + ord.getTable().getTableno() + " by " + Thread.currentThread().getName();
					ActivityLog.getInstance().addLogRecord(strlog);
					System.out.println(strlog);
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
	
	public void removeObserver(Observer obs)
	{
		registeredObservers.remove( obs);
	}
	
	public synchronized void notifyObservers()
	{
		for( Observer obs : registeredObservers)
			obs.update(AllOrders.getInstance().getDeliveredOrders());
	}
}

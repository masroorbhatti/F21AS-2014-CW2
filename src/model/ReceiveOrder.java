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
	
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		Thread.sleep(waittime);
		ord = AllOrders.getInstance().getNextOrder();
		notifyObservers();
		}
		catch (Exception e){
			
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
			obs.update(ord);
	}


}

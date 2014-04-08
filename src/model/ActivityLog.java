package model;

import java.util.ArrayList;
import java.util.Calendar;


/***
 * implementing Activity log based on lazy initialization
 * implementing 2-phase synchronization for avoiding racing condition.
 * Thread safe implementation
 */

public class ActivityLog {

	private static volatile ActivityLog instance = null;
	private ArrayList<String> log;
	
	private ActivityLog() {
		log = new ArrayList<String>();
		
	}
	public static ActivityLog getInstance() {
		if (instance == null) {
			synchronized (ActivityLog.class) {
				if (instance == null) {
					instance = new ActivityLog();
				}
			}
		}
		return instance;
	}
	public void addLogRecord(String status){
		log.add(status + " at " + Calendar.getInstance().getTime() );
	}
	
	public ArrayList<String> getLogArray(){
		return new ArrayList<String>(log);
	}
	
}

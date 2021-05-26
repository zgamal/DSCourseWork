import java.util.Random;

public class Customer implements Event {
	
	private int id; 
	private int arrival;
	private int service; 
	private int start; 
	private int wait; 
	
	public Customer(int id, int arrival, int service, boolean rep) {
		this.id = id;
		Random r = new Random();
		if(!rep) {
			this.arrival = r.nextInt(arrival) + 1;
			this.service = r.nextInt(service) + 1;
		} else {
			this.arrival = arrival;
			this.service = service;
		}
		this.start = -1;
		this.wait = -1;	
	}
	
	// Returns Event name
	public String getEvent() {
		return "Customer";
	}
	
	// Returns Customer id
	public int getId() {
		return this.id;
	}
	
	// Returns arrival time
	public int getTime() {
		return this.arrival;
	}
	
	// Returns service time
	public int getService() {
		return this.service;
	}
	
	// Returns wait time
	public int getWait() {
		return this.wait;
	}
	
	// Sets start time
	public void start(int start) {
		this.start = start;
	}
	
	// Sets wait time
	public void setWait(int wait) {
		this.wait = wait;
	}
	
	// Reps Customer
	public Customer clone() {
		return new Customer(this.id, this.arrival, this.service, true);
	}
	
	// Retuns String version of Event
	public String toString() {
		String result = "Customer " + id + "     Arrival time: " + arrival + "      Service time: " + service;
		if (start > -1) {
			result += "     Start time: " + start;
		}
		if(wait > -1) {
			result += "     Wait time: " + wait;
		}
		return result;
	}
	
	
}

import java.util.LinkedList;
import java.util.Queue;

public class Teller implements Event {
	
	private Queue<Customer> customers;
	private int id; 
	private int time; 
	private boolean running;
	
	public Teller(int id) {
		this.customers = new LinkedList<Customer>();
		this.id = id;
		this.time = 0; 
		this.running = false;
	}
	
	// Returns Event name
	public String getEvent() {
		return "Teller";
	}
	
	// Returns Customer
	public Customer peek() {
		return this.customers.peek();
	}
	
	// Returns Queue size
	public int getSize() {
		return this.customers.size();
	}
	
	// Returns Teller id
	public int getId() {
		return this.id;
	}
	
	
	// Returns Teller total time
	public int getTime() {
		return this.time;
	}
	
	// Returns true if working, false otherwise
	public boolean isRunning() {
		return this.running;
	}
	
	// Updates running
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	// Serves top Customer in queue
	public int serve(int time) {
		int wait = 0;
		if (!this.customers.isEmpty()) {
			Customer customer = (Customer) this.customers.remove();
			wait = time - customer.getTime();
			this.time += customer.getService();
		}
		return wait;
	}
	
	// Adds Customer to queue
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	// Removes and serves top Customer in queue
	public void removeCustomer() {
		this.customers.remove();
	}
	
	public void serve() {
		this.customers.remove();
	}
	
	
	// Retuns String version of Event
	public String toString() {
		String result = "Teller: ";
		Queue<Customer> queue = new LinkedList<Customer>();
		while (!customers.isEmpty()) {
			Customer customer = (Customer) customers.remove();
			queue.add(customer);
			result += customer + "; ";
		}
		
		while(!queue.isEmpty()) {
			customers.add(queue.remove());
		}
		result += "Teller time: " + time;
		return result;
	}
	
	
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultiQueue {
	
	private EventQueue<Event> events;
	private Teller[] tellers; 
	private int customers;
	private Queue<Customer> waitLine; 
	private ArrayList<Integer> waits; 
	
	public MultiQueue(int numCustomers, int numTellers) {
		this.events = new EventQueue<Event>(new EventComparator());
		this.tellers = new Teller[numTellers];
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
		this.customers = numCustomers;
		for(int i = 0; i < numCustomers; i++) {
			events.add(new Customer(i, 100, 20, false));
		}
		this.waitLine = new LinkedList<Customer>();
		this.waits = new ArrayList<Integer>();
	}
	
	// Customer queue as SingleQueue
	public MultiQueue(EventQueue<Event> c, int numTellers) {
		this.events = c;
		this.tellers = new Teller[numTellers];
		this.customers = c.getSize();
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
		this.waitLine = new LinkedList<Customer>();
		this.waits = new ArrayList<Integer>();
	}
	
	// Simulates MultiQueue
	public void run() {
		int time = 0; 
		while(!events.isEmpty()) {
			Event e = events.remove();
			time = e.getTime();			
			if(e.getEvent().equals("Customer")) {	
				Customer c = (Customer) e;
				int min = tellers[0].getSize();
				int index = 0;
				for(int i = 1; i < tellers.length; i++) {
					if(tellers[i].getSize() < min) {
						min = tellers[i].getSize();
						index = i;
					}
				}
				tellers[index].addCustomer(c);
				if(tellers[index].getSize() == 1) {
					Process p = new Process(time, c.getService(), index, c.getId());
					events.add(p);
				}
			}
			if(e.getEvent().equals("Process")) {
				Process p = (Process) e;
				events.add(new TellerEnd(p.getEnd(), p.getTeller(), p.getCustomer()));
			}
			if(e.getEvent().equals("End")) {
				TellerEnd end = (TellerEnd) e;
				tellers[end.getTeller()].serve();
				if(tellers[end.getTeller()].getSize() > 0) {
					Customer c = tellers[end.getTeller()].peek();
					Process p = new Process(time, c.getService(), end.getTeller(), c.getId());
					events.add(p);	
					waits.add(time - c.getTime());
				}
			}	
		}		
		double average = 0;
		for(int i = 0; i < waits.size(); i++) {
			average += waits.get(i);
		}
		average /= customers;
		System.out.println("Average wait time: " + average);

		System.out.println("Time it took: " + time);
	}
	
	// Prints Customers
	public void getCustomers() {
		//Queue for re-adding back into customers queue
		System.out.println("Customers in line:");
		Queue<Customer> q = new LinkedList<Customer>(); 
		while(!events.isEmpty()) {
			Customer c = (Customer) events.remove(); 
			q.add(c);
			System.out.println(c);
		}
		
		while(!q.isEmpty()) events.add(q.remove());
	}
	
	// Prints Tellers
	public void getTellers() {
		for(int i = 0; i < tellers.length; i++) {
			System.out.println(tellers[i]);
		}
	}
	
}

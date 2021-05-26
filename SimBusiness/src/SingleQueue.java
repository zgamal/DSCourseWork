import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SingleQueue {
		
	private EventQueue<Event> events;
	private Teller[] tellers; 
	private int customers;
	private Queue<Customer> waitLine; 
	private ArrayList<Integer> waits; 
	
	
	public SingleQueue(int customers, int tellersNum) {
		this.events = new EventQueue<Event>(new EventComparator());
		this.tellers = new Teller[tellersNum];
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
		this.customers = customers;
		for(int i = 0; i < customers; i++) {
			events.add(new Customer(i, 100, 20, false));
		}
		this.waitLine = new LinkedList<Customer>();
		this.waits = new ArrayList<Integer>();
	}
	
	// Customer queue as MultiQueue
	public SingleQueue(EventQueue<Event> c, int numTellers) {
		this.events = c;
		this.tellers = new Teller[numTellers];
		for(int i = 0; i < tellers.length; i++) {
			tellers[i] = new Teller(i);
		}
		this.customers = c.getSize();
		this.waitLine = new LinkedList<Customer>();
		this.waits = new ArrayList<Integer>();
	}
	
	// Simulates SingleQueue
	public void run() {
		int time = 0;
		while(!events.isEmpty()) {
			Event e = events.remove();
			time = e.getTime();			
			if(e.getEvent().equals("Customer")) {	
				Customer c = (Customer) e;
				boolean tellerAvailable = false;
			    Process p = null;
				for(int i = 0; i < tellers.length; i++) {
					Teller t = tellers[i];
					if(!t.isRunning()) {
						tellerAvailable = true;
						t.addCustomer((Customer) c);
						t.setRunning(true);
						p = new Process(time, c.getService(), i, c.getId());
						break;
					}
				}
				if(tellerAvailable) events.add(p);
				else waitLine.add(c);
			}
			if(e.getEvent().equals("Process")) {
				Process p = (Process) e;
				tellers[p.getTeller()].setRunning(true);
				events.add(new TellerEnd(p.getEnd(), p.getTeller(), p.getCustomer()));
			}
			if(e.getEvent().equals("End")) {
				TellerEnd end = (TellerEnd) e;
				tellers[end.getTeller()].setRunning(false);
				tellers[end.getTeller()].serve();
				if(!waitLine.isEmpty()) {
					Customer c = waitLine.remove();
					for(int i = 0; i < tellers.length; i++) {
						if(!tellers[i].isRunning()) {
							tellers[i].addCustomer(c);
							tellers[i].setRunning(true);
							break;
						}
					}
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
	public void customers() {
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
	public void printTellers() {
		for(int i = 0; i < tellers.length; i++) {
			System.out.println(tellers[i]);
		}
	}
	
}

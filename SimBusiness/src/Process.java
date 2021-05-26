
public class Process implements Event {
	
	protected int time;
	protected int service;
	protected int tid;
	protected int cid;
	
	public Process(int time, int service, int tid, int cid) {
		this.time = time;
		this.tid = tid;
		this.cid = cid;
		this.service = service;
	}
	
	// Returns Event name
	public String getEvent() {
		return "Process";
	}
	
	// Returns Event time
	public int getTime() {
		return time;
	}
	
	// Returns Teller id
	public int getTeller() {
		return tid;
	}
	
	// Returns Customer id
	public int getCustomer() {
		return cid;
	}
	
	// Returns End time
	public int getEnd() {
		return time + service;
	}

	// Retuns String version of Event
	public String toString() {
		return "Process      Start: " + time + "       Service: " + 
	service + 	"       Teller: " + tid + "       Customer: " + cid;
	}
	
}

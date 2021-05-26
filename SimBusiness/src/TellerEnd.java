
public class TellerEnd implements Event {
	
	private int time;
	private int tid; // Teller id
	private int cid; // Customer id
	
	public TellerEnd(int time, int tid, int cid) {
		this.time = time;
		this.tid = tid;
		this.cid = cid;
	}
	
	// Returns Event name
	public String getEvent() {
		return "End";
	}
	
	// Returns TellerEnd time
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
	
	// Retuns String version of Event
	public String toString() {
		return "End: " + time + "     Teller: " + tid + "     Customer: " + cid;
	}

	

	
}

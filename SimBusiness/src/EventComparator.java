import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

	// Sorts event times. (First priority given to End (Teller End) events, second to Process, and third to Customer (Customer Arrival) events).
	public int compare(Event event1, Event event2) {
		if (event1.getTime() - event2.getTime() == 0) {
			if(event1.getEvent().equals("End")) {
				return -2;
			}
			if (event2.getEvent().equals("End")) {
				return 2;
			}
			if (event1.getEvent().equals("Process")) {
				return -1;
			}
			if (event2.getEvent().equals("Process")) {
				return 1;
			}
		}
		return event1.getTime() - event2.getTime();
	}

}

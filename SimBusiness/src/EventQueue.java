import java.util.Comparator;
import java.util.PriorityQueue;

public class EventQueue<E> {
	
	private PriorityQueue<Event> events;
	private Comparator<Event> eventComparator;
	
	public EventQueue(Comparator<Event> eventComparator) {
		this.events = new PriorityQueue<Event>(eventComparator);
		this.eventComparator = eventComparator;
	}
	
	// Returns EventQueue size
	public int getSize() {
		return events.size();
	}
	
	// Returns true if EventQueue is empty, false otherwise
	public boolean isEmpty() {
		return events.isEmpty();
	}
	
	// Adds event to EventQueue
	public void add(Event event) {
		events.add(event);
	}
	
	// Removes event from EventQueue
	public Event remove() {
		return events.remove();
	}
	
	// Peeks event from EventQueue
	public Event peek() {
		return events.peek();
	}
	
}

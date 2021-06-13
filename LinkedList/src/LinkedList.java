
public class LinkedList<E> {
	
	protected Node<E> head; // head node
	protected Node<E> tail; // tail node
	
	// Default Constructor
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	// Keeps tracking of tail, head pointers, and updates, and interates over elements until i is reached and sets it approperiately
	public void add(int i, E data) {
		Node prev = null;
		Node next = head;
		int j = 0;
		while (j < i) {
			prev = next;
			next = next.getNext();
		}
		Node<E> n = new Node(data, next);
		prev.setNext(n);
		if (i == 0) {
			head = n;
		} 
		if (tail == prev) {
			tail = n;
		}
	}

}

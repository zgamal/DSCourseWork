public class Node<E> {
	
	protected E data; // data
	protected Node<E> next; // reference to next node
	
	// Construct node with data toStore and a reference to the next node
	public Node(E toStore, Node<E> next) {
		this.data = toStore;
		this.next = next;
	}
	
	// Construct node with data no reference to anything
	public Node(E toStore) {
		this(toStore, null);
	}
	
	// Return next node
	public Node<E> getNext() {
		return next;
	}
	
	// Return data
	public E getData() {
		return data;
	}
	
	// Sets next to newNext and returns oldNext
	public Node<E> setNext(Node<E> newNext) {
		Node<E> oldNext = next;
		next = newNext;
		return oldNext;
	}
		
}

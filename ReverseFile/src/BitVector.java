import java.util.ArrayList;
import java.util.Vector;

public class BitVector {
	
	// Instance Variable for Vector with only Boolean elements 
	Vector<Boolean> BitVector;
	
	// Initializes a Boolean Vector with initial capacity 10
	public BitVector() {
		BitVector = new Vector<Boolean>(10);
	}
	
	// Initializes a Boolean Vector with initial capacity given
	public BitVector(int initialCapacity) {
		if (initialCapacity >= 0) {
			BitVector = new Vector<Boolean>(initialCapacity);
		}
	}
	
	// Add Boolean element bit to Boolean Vector
	public void add(boolean bit) {
		BitVector.add(bit);
	}
	
	// Remove Boolean element bit from Boolean Vector if it exists and return status
	public boolean remove(boolean bit) {
		if (BitVector.indexOf(bit) >= 0) {
			BitVector.remove(bit);
			return true;
		} else {
			return false;
		}
	}
	
	// Return Boolean element bit at index given
	public boolean get(int index) {
		return BitVector.get(index);
	}
	
	// Add Boolean element bit to Boolean Vector at given index
	public void add(int index, boolean bit) {
		BitVector.add(index, bit);
	}
	
	// Return true if Boolean Vector is empty, false otherwise
	public boolean isEmpty() {
		return BitVector.isEmpty();
	}
	
	// Remove Boolean element bit from Boolean Vector at given index and return element removed
	public boolean remove(int where) {
		Boolean b = BitVector.get(where);
		BitVector.remove(where);
		return b;
	}
	
	// Set Boolen element bit at given index to Boolean element givem
	public boolean set(int index, boolean bit) {
		BitVector.set(index, bit);
		return bit;
	}
	
	// Return Boolean Vector Size
	public int size() {
		return BitVector.size();
	}

}

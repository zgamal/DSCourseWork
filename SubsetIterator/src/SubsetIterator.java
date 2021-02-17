/*
 * THOUGHT QUESTIONS
 * 
 * 1. Best solution: 4, 5, 6, 10, 11, 12, 13.
 * 
 * 2. I would use a random number generator to both randomly determine the length of the subset, in order to examine a variety of lengths, but also 
 *    generate random numbers given the length. Thus, when the second has elapsed and the interrupt/time out is called, I'll find the best subset out
 *    of subsets of random length and random indices. For example, one way to do this is, in the get method, use the random number generator to 
 *    determine the counter.
 *    
*/

import structure5.*;

public class SubsetIterator extends AbstractIterator {
	
	private Vector<Double> vector = new Vector<Double>(); // vector
	private long counter; // counter
	
	// Creates Iterator
	public SubsetIterator(Vector<Double> input) {
		vector = input;
		reset();
	}
	
	// Resets Counter to zero
	public void reset() {
		counter = 0;
	}
	
	// Generates a subset
	public Vector<Double> get() {
		Vector<Double> subset = new Vector<Double>();
		for (int i = 0; i < vector.size(); i++) {
			long mask = 1 << i;
			if ((counter & mask) > 0) {
				subset.add(vector.get(i));
			}
		}
		return subset;
	}
	
	// Returns true if counter value is approperiate
	public boolean hasNext() {
		return counter < Math.pow(2, vector.size())-1;
	}
	
	// Generates subset and increments counter
	public Vector<Double> next() {
		Vector<Double> temp = get();
		counter++;
		return temp;
	}
	
	// Prints best solution from n given by user 
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Vector<Double> vector = new Vector<Double>();
		Vector<Double> subsets = new Vector<Double>();
		Vector<Double> temp = new Vector<Double>();
		double max_vector = 0;
		for (int i = 1; i <= n; i++) {
			vector.add(Math.sqrt(i));
			max_vector += Math.sqrt(i);
		}
		double close = max_vector;
		SubsetIterator it = new SubsetIterator(vector);
		while (it.hasNext()) {
			double max_subset = 0;
			temp = it.get();
			for (double subset : temp) {
				max_subset += subset;
			}
			if (max_subset <= max_vector/2 && max_vector/2 - max_subset < close) {
				close = max_vector/2 - max_subset; 
				subsets = temp;
			}
			it.next();
		}
		for (int x = 0; x < subsets.size(); x++) {
			System.out.print(((int) (Math.pow(subsets.get(x), 2)+0.1)) + "   \n");
		}
	}
	
}
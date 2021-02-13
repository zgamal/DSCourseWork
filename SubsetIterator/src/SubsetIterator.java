/*
 * THOUGHT QUESTIONS
 * 
*/

import structure5.*; 

public class SubsetIterator extends AbstractIterator {
	
	private Vector<Integer> vector = new Vector<Integer>();	
	private long counter;
	
	public SubsetIterator(Vector<Integer> input) {
		vector = input;
		reset();
	}
	
	public void reset() {
		counter = 0;
	}
	
	public Vector<Integer> get() {
		Vector<Integer> subset = new Vector<Integer>();
		for (int i = 0; i < vector.size(); i++) {
			long mask = 1 << i;
			if ((counter & mask) > 0) {
				subset.add(i);
			}
		}
		return subset;
	}
	
	public boolean hasNext() {
		return counter < Math.pow(2, vector.size() - 1);
	}
	
	public Vector<Integer> next() {
		Vector<Integer> temp = get();
		counter++;
		return temp;
	}
	
	public static void main(String[] args) {
		int n = 15;
		Vector<Integer> vector = new Vector<Integer>();
		Vector<Integer> subsets = new Vector<Integer>();
		Vector<Integer> temp = new Vector<Integer>();
		double max_vector = 0;
		for (int i = 1; i <= n; i++) {
			vector.add(i);
			max_vector += Math.sqrt(i);
		}
		double difference = max_vector;
		SubsetIterator it = new SubsetIterator(vector);
		while (it.hasNext()) {
			double max_subset = 0;
			temp = it.next();
			for (int subset : temp) {
				max_subset += Math.sqrt(vector.get(subset));
			}
			if (max_subset < max_vector/2 && max_vector/2 - max_subset < difference) {
				difference = max_vector/2 - max_subset; 
				subsets = temp;
			}
		}
		System.out.println(subsets);
	}
}

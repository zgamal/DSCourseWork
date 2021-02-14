/*
 * THOUGHT QUESTIONS
 * 
 * 1. 4 5 6 10 11 12 13.
 * 
 * 2. I would use a random number generator to both randomly determine the length of the subset (so that subsets of a variety
 * of lengths are examined) but also generate random numbers given the length. Thus, when the second has elapsed and the interrupt/time
 * out is called, I'll find the best subset out of subsets of random length and random indices.
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
			for (int index : temp) {
				max_subset += Math.sqrt(index);
			}
			if (max_subset < max_vector/2 && max_vector/2 - max_subset < difference) {
				difference = max_vector/2 - max_subset; 
				subsets = temp;
			}
		}
		System.out.println(subsets);
	}
}


//import structure5.*; 
//public class SubsetIterator extends AbstractIterator {
//	
//	private Vector<Double> vector = new Vector<Double>();	
//	private long counter;
//	
//	public SubsetIterator(Vector<Double> input) {
//		vector = input;
//		reset();
//	}
//	
//	public void reset() {
//		counter = 0;
//	}
//	
//	public Vector<Double> get() {
//		Vector<Double> subset = new Vector<Double>();
//		for (int i = 0; i < vector.size(); i++) {
//			long mask = 1 << i;
//			if ((counter & mask) > 0) {
//				subset.add((double) i);
//			}
//		}
//		return subset;
//	}
//	
//	public boolean hasNext() {
//		return counter < Math.pow(2, vector.size() - 1);
//	}
//	
//	public Vector<Double> next() {
//		Vector<Double> temp = get();
//		counter++;
//		return temp;
//	}
//	
//	public static void main(String[] args) {
//		int n = 15;
//		Vector<Double> vector = new Vector<Double>();
//		Vector<Double> subsets = new Vector<Double>();
//		Vector<Double> temp = new Vector<Double>();
//		double max_vector = 0;
//		for (int i = 1; i <= n; i++) {
//			vector.add(Math.sqrt(i));
//			max_vector += Math.sqrt(i);
//		}
//		double close = max_vector;
//		SubsetIterator it = new SubsetIterator(vector);
//		while (it.hasNext()) {
//			double max_subset = 0;
//			temp = it.get();
//			for (double subset : temp) {
//				max_subset += Math.sqrt(subset);
//			}
//			if (max_subset < max_vector/2 && max_vector/2 - max_subset < close) {
//				close = max_vector/2 - max_subset; 
//				subsets = temp;
//			}
//			it.next();
//		}
//		System.out.println(subsets);
//	}
//}
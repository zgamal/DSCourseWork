/*
 * Thought Questions
 * 
 * 1. The single-queue seems to process customers at least as fast as the multiqueue technique. This is because multi-queue customers may not enter
 *    the shortest line (assuming it finishes the earliest); when these customers do, the multi-queue takes the same time as a single-queue.
 * 
 * 2. Similar to the previous question, the average wait time for the multi-queue is the same time or longer than the single-queue average wait time.
 *    In the multi-queue, there are times when the tellers are not busy (which the single-queue avoids), thus increasing the average wait for the
 *    multi-queue.
 * 
 * 3. This implementation would not change the underlying structure: to do this one would iterate through the multi-queue tellers, moving customers
 *    from one line to another when one line has more than 2 customers than the other. 
 *    
 * 4. This method of “express lines” would most likely only reduce average wait times for customers in the “express lane.” Customers in lines with 
 *    longer individual times will be waiting much longer as the line will be much longer. In other words, the express lane will quickly run out of 
 *    customers (while the longer lines still process customers with long wait times) and it is obvious that an empty teller is inefficient. Overall,
 *    the average wait time of the “express lane” approach will be longer than that of the multi-queue, where customer times are spread out relatively
 *    equally among the teller lanes.
 */

import java.util.Scanner;

public class SimBusiness {
	
	public static void main(String[] args) {
		
		EventQueue<Event> c1 = new EventQueue<Event>(new EventComparator());
		EventQueue<Event> c2 = new EventQueue<Event>(new EventComparator());
		
		Scanner s = new Scanner(System.in);
		System.out.print("Customers: ");
		int numCustomers = s.nextInt();
		System.out.print("Tellers: ");
		int numTellers = s.nextInt();
		System.out.print("Arrival range: ");
		int arrival = s.nextInt();
		System.out.print("Service range: ");
		int service = s.nextInt();		
		System.out.println("Customers In Line:");
		for(int i = 0; i < numCustomers; i++) {
			Customer c = new Customer(i, arrival, service, false);
			Customer d = c.clone();
			c1.add(c);
			c2.add(d);
		}		
		SingleQueue sq = new SingleQueue(c1, numTellers);
		MultiQueue mq = new MultiQueue(c2, numTellers);
		System.out.println("Single-queue simulation:");
		sq.run();
		System.out.println();
		System.out.println("Multi-queue simulation:");
		mq.run();

	}
	
}

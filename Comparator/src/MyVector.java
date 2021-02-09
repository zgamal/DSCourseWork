/*
 * THOUGHT QUESTIONS
 * 
 * 1. The RevComparator Comparator class is basically the inverse of IntegerComparator. While IntegerComparator.compareTo(int a, int b) returns a 
 * 	  positive integer if int a is larger than int b, zero if they are equal, and negative otherwise, RevComparator.compareTo(int a, int b) returns 
 *    the opposite of that. Thus, because the IntegerComparator class would return whether the first integer was greater than the second, by being 
 *    inputted into the RevComparator class, any first integer that is greater than the second will be placed in an opposite position that it otherwise
 *    would have been had there not been the code segment return -base.compare(a,b). Therefore, when the code provdided is constructed, the integer
 *    values in MyVector v are sorted in a descending  order.
 * 
 * 2. import structure5.*;
 *	  import java.util.Iterator;
 *    import java.util.Comparator;
 *    import java.util.Scanner;
 *    
 *    public class RevComparator<T> implements Comparator<T> {
 *    
 * 	  	  protected Comparator<T> base;
 * 		  protected int order = 1;
 * 
 * 		  public RevComparator(Comparator<T> baseCompare) {
 * 		      base = baseCompare;
 * 		  }
 *  
 *        public void ascending() {
 * 	          order = 1;
 * 	      }
 *  
 * 	      public void descending() {
 * 		      order = -1;
 * 	      }
 *  
 * 	      public int compare(T a, T b) {
 * 		      return order * base.compare(a,b);
 * 	      }
 * 
 *    }
 *    
 *    The code above parallels RevComparator and the same concept explained in question 1. The protected integer order allows for sorting to occur in a 
 *    variety of different ways. The methods ascending and descending change order to 1, in the case of ascending order, and -1, in the case of 
 *    descending order. When the compare method is called, the value that will be returned will be multiplied by order thus allowing data to be sorted
 *    in the appropriate order.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import structure5.*;

public class MyVector extends Vector<Student> {
		
	MyVector() {
		super();
	}
	
	public static void main(String[] args) {
		MyVector students = new MyVector(); 
		ArrayList<String> sorted_students = new ArrayList<String>();

		// Scans file specified and stores data in Student vector.
		Scanner s = new Scanner(System.in); 
		if (args.length > 0) {
			File data = new File(args[0]); 
			try {
				s = new Scanner(data);
			} catch (FileNotFoundException e) {
				System.out.println("No such file!");
				System.exit(1);
			}		
		} while (s.hasNextLine()) {
			String[] line = s.nextLine().split(",");
			students.add(new Student (line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2])));
		} 

		// Initializes different Student name, age, and weight Comparators.
		Comparator<Student> comparatorByName = new ComparatorByName();
		Comparator<Student> comparatorByAge = new ComparatorByAge();
		Comparator<Student> comparatorByWeight = new ComparatorByWeight();

		// Sorts students name, lexicographically, and prints result,
		students.sort(comparatorByName);
		System.out.println("Sorted lexicographically by name: ");
		for(Student student : students) {
			System.out.println(student.getName());
		}
		System.out.println("\n");

		
		// Sorts students by age, ascendingly, and prints result,
		students.sort(comparatorByAge);
		System.out.println("Sorted ascendingly by age: ");
		for(Student student : students) {
			System.out.println(student.getName());
		}
		System.out.println("\n");

		// Sorts students by weight, ascendingly, and prints result,
		students.sort(comparatorByWeight);
		System.out.println("Sorted ascendingly by weight: ");
		for(Student student : students) {
			System.out.println(student.getName());
		}
		System.out.println("\n");

	}

	// Sorts Student elements in Vector by selection sort using the Comparator specified.
	public void sort(Comparator<Student> c) {
		for (int j = 0; j < size() - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < size(); k++) {
				if (c.compare(get(k), get(minIndex)) < 0) {
					minIndex = k;
				}
			}
			if (j != minIndex) {
				Student temp = get(j);
				set(j, get(minIndex));
				set(minIndex, temp);
			}
		}
	}

}


/*
 * THOUGHT QUESTIONS
 * 
 * 3.4. The method setSize(int newSize) is used to explicitly set the size of the Vector. If newSize given is smaller, then the current
 *    size of the Vector then all leftover components at and after newSize index are removed, and if newSize is larger, then new null 
 *    componenets are added at the end of the Vector to reach newSize. This is useful because it allows the user easily to limit the 
 *    size of the Vector to a certain int and discard all leftover components. It is also useful if the user's intention is to add a 
 *    certain number of null components at the end of the Vector.
 *    
 * 3.6.  
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseFile {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in); // scanner
		ArrayList <String> list = new ArrayList <String>(); // stores all raw lines
		ArrayList <String> rlist = new ArrayList <String>(); // stores all reveresed lines
		
		if (args.length > 0) {
			File data = new File(args[0]); // stores input file
			try {
				
				// scans file
				s = new Scanner(data);
			} catch (FileNotFoundException e) {
				
				// if file unfound, prints error message and exits 
				System.out.println("No such file");
				System.exit(1);
			}		
		} while (s.hasNextLine()) {
			
			// creates ArrayList of lines
			list.add(s.nextLine());
		} for (int i = 0; i < list.size(); i++) {
			
			// reverses order of lines
			rlist.add(0, list.get(i));
		} for (int i = 0; i < rlist.size(); i++) {	
			
			// Splits lines into words and prints in reverse order
			String[] words = rlist.get(i).split(" ");
			for (int j = words.length - 1; j >= 0; j--) {
				System.out.print(words[j] + " ");
			}
			System.out.println("");
		}
	}
}

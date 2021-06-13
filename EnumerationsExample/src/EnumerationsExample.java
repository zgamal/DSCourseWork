import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationsExample {

	public static void main(String[] args) {
		// ... Code Exists
		//Enumeration<E> e;
		//while (e.hasMoreElement()) {
			//E element = e.nextElement();
		//}
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(5);
		a.add(7);
		a.add(3);
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
		for (Integer j : a) {
			System.out.println(j);
		}
		
		Iterator<Integer> it = a.iterator();
		while (it.hasNext()) {
			Integer k = it.next();
			System.out.println(k);
		}
		
		
	}

}

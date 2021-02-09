import java.util.Comparator;

public class ComparatorByWeight implements Comparator<Student> {
	
	// Returns a positive integer if Student a's weight is bigger than b's, zero if equal, and negative if smaller. 
	public int compare(Student a, Student b) {
		return a.getWeight() - b.getWeight();
	}
	
}

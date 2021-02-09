import java.util.Comparator;

public class ComparatorByName implements Comparator<Student> {

	// Returns a positive integer if Student a's name is bigger than Student b's (case insensitive), zero if they are equal, and negative if smaller.
	public int compare(Student a, Student b) {
		return a.getName().compareToIgnoreCase(b.getName());
	}
	
}

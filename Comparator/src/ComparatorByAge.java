import java.util.Comparator;

public class ComparatorByAge implements Comparator<Student> {

	// Returns a positive integer if Student a's age is bigger than b's, zero if equal, and negative if smaller. 
	public int compare(Student a, Student b) {
		return a.getAge() - b.getAge();
	}
	
}

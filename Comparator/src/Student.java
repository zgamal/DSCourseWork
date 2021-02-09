public class Student {
	
	private String name; // Student name
	private int age; // Student age
	private int weight; // Student weight
	
	public Student(String name, int age, int weight) {
		this.name = name;
		this.age = age;
		this.weight = weight;
	}
	
	// Returns Student's name.
	public String getName() {
		return this.name;
	}
	
	// Returns Student's age.
	public int getAge() {
		return this.age;
	}
	
	// Returns Student's weight.
	public int getWeight() {
		return this.weight;
	}

}

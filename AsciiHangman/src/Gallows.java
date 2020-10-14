import java.util.Arrays;

public class Gallows {
	
	protected Man man; // stores man object
	private int stage; // int to store stage of gallows

	// initializes gallows at first stage with man object 
	public Gallows() {
		man = new Man();
		stage = 0;
	}
	
	// function that returns the string value of gallows given its stage
	public static String build(int s) {
		if (s == 0) { 
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |      \n" + 
					"  |      \n" + 
					"  |      \n" + 
					"__|__    ");
		} else if (s == 1) {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |      \n" + 
					"  |      \n" + 
					"__|__    ");
		} else if (s == 2) {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |    | \n" + 
					"  |      \n" + 
					"__|__    ");
		} else if (s == 3) {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |   \\| \n" + 
					"  |      \n" + 
					"__|__    ");
		} else if (s == 4) {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |   \\|/\n" + 
					"  |      \n" + 
					"__|__    ");
		} else if (s == 5) {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |   \\|/\n" + 
					"  |   /  \n" + 
					"__|__    ");
		} else {
			return ("\n" + 
					"   ____  \n" + 
					"  |    | \n" + 
					"  |    O \n" + 
					"  |   \\|/\n" + 
					"  |   / \\\n" + 
					"__|__    ");
		}
	}
	
	// increments gallows stage and hangs man
	public void hang() {
		man.hang();
		stage++;
	}
	
	// returns state of man
	public boolean isAlive() {
		return man.isAlive();
	}

	// returns string value of gallows at current stage
	public String toString() {
		return build(stage);
	}
	
	// check for debugging
	public static void main(String[] args) {
		Gallows g = new Gallows();
		System.out.println(g);
		for(int i=0; i< Man.MAX_INCORRECT; i++) {
			g.hang();
			System.out.println(g);
		}
	}
}
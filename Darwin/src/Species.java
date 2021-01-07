import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data is recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * pointer as part of its internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within this
 * abstraction, this provides a constructor that will read a file containing
 * the name of the creature and its program, as described in the earlier part
 * of this assignment. To make the folder structure more manageable, the
 * species files for each creature are stored in a subfolder named Creatures.
 * Thus, creating the Species for the file Hop.txt will causes the program to
 * read in "Creatures/Hop.txt".
 * 
 * <p>
 * 
 * Note: The instruction addresses start at one, not zero.
 */

public class Species {
	
	private ArrayList<Instruction> program = new ArrayList<Instruction>();
	private ArrayList<String> sprogram = new ArrayList<String>();
	private String name;
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @pre fileName exists in the Creature subdirectory.
	 */
	public Species(String fileName) {	
		try {
			File f = new File("Creatures/" + fileName);
		    Scanner s = new Scanner(f);
		    while (s.hasNextLine()) {
		    	String line = s.nextLine(); 
		    	sprogram.add(line);
		    	String[] words = line.split(" ");
	    		if (line.isEmpty()) {
	    			break;
	    		} else if (words[0].equals("hop")) {
	        		Instruction instruction = new Instruction(Instruction.HOP, 0);
	        		program.add(instruction);
	    		} else if (words[0].equals("hopleft")) {
	        		Instruction instruction = new Instruction(Instruction.HOPLEFT, 0);
	        		program.add(instruction);
	    		} else if (words[0].equals("hopright")) {
	        		Instruction instruction = new Instruction(Instruction.HOPRIGHT, 0);
	        		program.add(instruction);
	        	} else if (words[0].equals("left")) {
	        		Instruction instruction = new Instruction(Instruction.LEFT, 0);
	        		program.add(instruction);
	        	} else if (words[0].equals("right")) {
	        		Instruction instruction = new Instruction(Instruction.RIGHT, 0);
	        		program.add(instruction);
	        	} else if (words[0].equals("infect")) {
	        		int address = 0;
	        		if (words.length > 1) {
		        		address = Integer.parseInt(words[1]);
	        		} 
	        		Instruction instruction = new Instruction(Instruction.INFECT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("infectleft")) {
	        		int address = 0;
	        		if (words.length > 1) {
		        		address = Integer.parseInt(words[1]);
	        		} 
	        		Instruction instruction = new Instruction(Instruction.INFECTLEFT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("infectright")) {
	        		int address = 0;
	        		if (words.length > 1) {
		        		address = Integer.parseInt(words[1]);
	        		} 
	        		Instruction instruction = new Instruction(Instruction.INFECTRIGHT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifempty")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFEMPTY, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifwall")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFWALL, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifsame")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFSAME, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifsameleft")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFSAMELEFT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifsameright")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFSAMERIGHT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifenemy")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFENEMY, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("if2enemy")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFTWOENEMY, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifenemyleft")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFENEMYLEFT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifenemyright")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFENEMYRIGHT, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifrandom")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.IFRANDOM, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("set")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.SET, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("inc")) {
		        	Instruction instruction = new Instruction(Instruction.INC, 0);
		        	program.add(instruction);
	        	} else if (words[0].equals("dec")) {
	        		Instruction instruction = new Instruction(Instruction.DEC, 0);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifeq")) {
	        		int number = Integer.parseInt(words[1]);
	        		int address = Integer.parseInt(words[2]);
	        		Instruction instruction = new Instruction(Instruction.IFEQ, number, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("ifmemeq")) {
	        		int number = Integer.parseInt(words[1]);
	        		int address = Integer.parseInt(words[2]);
	        		Instruction instruction = new Instruction(Instruction.IFMEMEQ, number, address);
	        		program.add(instruction);
	        	} else if (words[0].equals("copymem")) {
	        		Instruction instruction = new Instruction(Instruction.COPYMEM, 0);
	        		program.add(instruction);
		        } else if (words[0].equals("go")) {
	        		int address = Integer.parseInt(words[1]);
	        		Instruction instruction = new Instruction(Instruction.GO, address);
	        		program.add(instruction);
	        	} 
		    }
		    s.close();
		} catch (FileNotFoundException e) {
		    	System.out.println("An error occurred.");
		    	e.printStackTrace();
		}
	}    
	
	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return sprogram.get(0);
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return sprogram.get(1);
	}

	/**
  	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		return program.size();
	}

	/**	
	 * Return an instruction from the program. 
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		return program.get(i-1);
	}
	
	/**
	 * Return a String representation of the program.
	 */
	public String programToString() {
		String program = "";
		for (int i = 0; i < sprogram.size(); i++) {
			program += (sprogram.get(i) + "\n");
		}
		return program;
	}
	
	public static void main(String[] args) {
		Species species = new Species("Rover.txt");
		assert species.getName().equals("Rover") : "getName error";
		assert species.getColor().equals("red") : "getColor error";
		assert species.programSize() == 12 : "programSize error";
		assert species.programStep(1).toString().equals("ifenemy 11") : "programStep error";
		assert species.programStep(species.programSize()).toString().equals("go 1") : "programStep error";
	}

}

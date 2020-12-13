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
	
	ArrayList<Instruction> program = new ArrayList<Instruction>();
	ArrayList<String> sprogram = new ArrayList<String>();
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @pre fileName exists in the Creature subdirectory.
	 */
	public Species(String fileName) {	
		try {
			File f = new File(fileName);
		    Scanner s = new Scanner(f);
		    while (s.hasNextLine()) {
		    	String l = s.nextLine(); 
		    	sprogram.add(l);
		    		if (l.isEmpty()) {
		    			break;
		    		} else if (l.contains("hop")) {
		        		Instruction instruction = new Instruction(Instruction.HOP, 0);
		        		program.add(instruction);
		        	} else if (l.contains("left")) {
		        		Instruction instruction = new Instruction(Instruction.LEFT, 0);
		        		program.add(instruction);
		        	} else if (l.contains("right")) {
		        		Instruction instruction = new Instruction(Instruction.RIGHT, 0);
		        		program.add(instruction);
		        	} else if (l.contains("infect")) {
		        		Instruction instruction = new Instruction(Instruction.INFECT, 0);
		        		program.add(instruction);
		        	} else if (l.contains("ifempty")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
		        		Instruction instruction = new Instruction(Instruction.IFEMPTY, address);
		        		program.add(instruction);
		        	} else if (l.contains("ifwall")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
		        		Instruction instruction = new Instruction(Instruction.IFWALL, address);
		        		program.add(instruction);
		        	} else if (l.contains("ifsame")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
		        		Instruction instruction = new Instruction(Instruction.IFSAME, address);
		        		program.add(instruction);
		        	} else if (l.contains("ifenemy")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
		        		Instruction instruction = new Instruction(Instruction.IFENEMY, address);
		        		program.add(instruction);
		        	} else if (l.contains("ifrandom")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
		        		Instruction instruction = new Instruction(Instruction.IFRANDOM, address);
		        		program.add(instruction);
		        	} else if (l.contains("go")) {
		        		String[] line = l.split(" ");
		        		int address = Integer.parseInt(line[1]);
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
	

}

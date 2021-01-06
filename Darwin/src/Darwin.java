/**
 * THOUGHT QUESTIONS
 * 
 * 1. A program can be written to run the game a substantial number of times, 1000 for example, and keep track of how many times the Rover and the 
 *	  Flytrap won. This can be done by changing the code in Darwin.java’s main method to restart the game when any of the two species wins and keeps track
 * 	  of their individual number of victories. The game will keep restarting until a number of specified turns are played. After that, the number of
 *    victories of each of the species can be produced. If the number of turns played is large enough, the resulting number of wins for each of the
 *    species should reflect their respective statistical chances of winning. Though this method of accurately determining which species wins more 
 *    often could end up taking hours, it is the most practical one, as in the case of the number of turns played is substantial enough, the result is
 *    which species wins more often. The program, however, is not guaranteed to produce an accurate answer, because, if the chances of winning for both
 *    species are very close to each other, for example, ~49%-~51%, then the species with the larger winning percentage is not necessarily the one 
 *    which wins more often, as if the game is played for longer turns the odds could change. 
 *    
 * 2. Essentially, the just-in-time compiler is just a compiler of java byte code to machine code instructions. Instead of interpreting the byte code 
 * 	  when a method is invoked before executing it like a usual interpreter, the JIT will translate the byte code into machine code at run-time rather 
 *	  than before execution, and then executes this directly. 
 *
 * 3. I disagree with him. Personally, the go-to statement caused me a lot of trouble when I was designing my own species. The fact that all of the 
 *	  instructions are above each other line by line and the individual chunks of code and instructions are not separated is challenging in terms of 
 *    scaling the sophistication of the program’s design up. Nonetheless, for a low-level programming language, I think the go-to statement is an 
 *    ingenious way to support branching while at the same time keeping things simple and neat. If for example, an if-else method of branching was used
 *    instead, while the individual branches and chunks of code and instructions will be separated, the program itself will be harder to scale up given
 *    how more complicated it will become. Thus, I think the go-to statement method for branching, while potentially problematic and messy, is the best
 *    option for implementing branching using a low-level programming language.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

class Darwin {
	protected static World<Creature> world;
	private static ArrayList<Creature> creatures;
	private static ArrayList<Species> species;
	private static Random r;
	
	private static final int MAP_SIZE = 15;
	private static final int ROUNDS = 1000;

	/**
	 * The array passed into main will include the arguments that 
	 * appeared on the command line. For example, running "java 
	 * Darwin Hop.txt Rover.txt" will call the main method with s 
	 * being an array of two strings: "Hop.txt" and "Rover.txt". 
	 */
	public static void main(String s[]) {
		world = new World<Creature> (MAP_SIZE,MAP_SIZE);
		creatures = new ArrayList<Creature>();
		r = new Random();
		WorldMap.createWorldMap(MAP_SIZE, MAP_SIZE);


		/**
		 * Creates list of species given by user
		 */
		species = new ArrayList<Species>();
		for (int i = 0; i < s.length; i++) {
			species.add(new Species(s[i]));			
		}
		
		/**
		 * Spawns creatures in the correct places and updates display and stuff accoridngly 
		 */
		for (int i = 0; i < species.size(); i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				boolean notset = true;
				while (notset) {
					Position spawn = new Position(r.nextInt(MAP_SIZE), r.nextInt(MAP_SIZE));
					if (world.get(spawn) == null) {
						Creature c = new Creature (species.get(i), world, spawn, r.nextInt(4));
						creatures.add(c);
						world.set(spawn, c);
						WorldMap.displaySquare(spawn, species.get(i).getName().charAt(0), c.direction(), species.get(i).getColor());
						notset = false;
					}
				}
			}
		}
		simulate();
	}
	
	/**
	 * Stimulates the game by giving each creature one turn for given number of rounds
	 */
	public static void simulate() {
		for (int i = 0; i < ROUNDS; i++) {
			giveEachCreatureOneTurn();
			WorldMap.pause(100);
		}
	}
	
	/**
	 * Gives each creatures one turn
	 */
	private static void giveEachCreatureOneTurn() {
		for (int i = 0; i < creatures.size(); i++) {
			creatures.get(i).takeOneTurn();
		}
	}
	
}
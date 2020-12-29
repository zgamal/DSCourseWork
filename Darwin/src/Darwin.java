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
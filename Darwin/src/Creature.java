import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents one creature on the board.
 * Each creature remembers its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature remembers the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also repsonsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class. 
 */

public class Creature {

	/**
	 * Create a creature of the given species, with the indicated
	 * position and direction.  Note that we also pass in the
	 * world-- remember this world, so that you can check what
	 * is in front of the creature and to update the board
	 * when the creature moves.
	 * @param species The species of the creature
	 * @param world The world in which the creature lives
	 * @param pos The position of the creature
	 * @param dir The direction the creature is facing
	 * @pre species, world, and pos must be non-null
	 * @pre pos must be within the bounds of world
	 * @pre dir must be one of: Position.NORTH, Position.SOUTH, Position.EAST
	 *                          or Positon.WEST
	 * @pre the world map must have been created
	 * @post creates a creature of species species in world world at position
	 *       pos facing direction dir
	 * @post initializes instance variables so that the creature knows what
	 *		 instruction to begin executing
	 * @post displays the creature on the world map
	 */

	private Species species;
	private World<Creature> world;
	private Position pos;
	private int dir;
	private char c;
	private int step;
	private int m;

	public Creature(Species species, World<Creature> world, Position pos, int dir) {
		this.species = species;
		this.world = world;
		this.pos = pos;
		this.dir = dir;
		this.c = species.getName().charAt(0);
		step = 1;
		m = 0;
		WorldMap.displaySquare(pos, c, dir, species.getColor());
	}

	/**
	 * Return the species of the creature.
	 */
	public Species species() { 
		return this.species;
	}

	/**
	 * Return the current direction of the creature.
	 */
	public int direction() {
		return this.dir;
	}

	/**
	 * Return the position of the creature.
	 */
	public Position position() {
		return this.pos;
	}
	
	public int mem() {
		return this.m;
	}

	/**
	 * Updates the species, c and step of the creature when infected.
	 */
	private void invaded(Species s) {
		this.species = s;
		this.c = s.getName().charAt(0);
		this.step = 1;
	}

	/**
	 * Execute steps from the Creature's program until 
	 * a hop, left, right, or infect instruction is executed.
	 * @post Creature takes one turn's worth of instructions
	 * @post display is updated to reflect movement of this creature
	 *
	 */
	public void takeOneTurn() {
		boolean done = false;
		while (!done && step > 0 && step <= species.programSize()) {
			Position adj = pos.getAdjacent(dir);
			int ldir = dir;
			if (ldir == Position.NORTH) ldir = Position.WEST;
			else ldir--;
			int rdir = dir;
			if (rdir == Position.WEST) rdir = Position.NORTH;
			else rdir++;
			Position left = pos.getAdjacent(ldir);
			Position right = pos.getAdjacent(rdir);

			
			switch (species.programStep(step).getOpcode()) {
						
				case Instruction.HOP :
					if (world.inRange(adj) && world.get(adj) == null) {
						world.set(pos, null);
						world.set(adj, this);
						WorldMap.displaySquare(pos,' ', dir, species.getColor());
						WorldMap.displaySquare(adj, c, dir, species.getColor());
						pos = adj;
					}
					done = true;
					step++;
					break;
				
				case Instruction.HOPLEFT :
					if (world.inRange(left) && world.get(left) == null) {
						world.set(pos, null);
						world.set(left, this);
						WorldMap.displaySquare(pos,' ', dir, species.getColor());
						WorldMap.displaySquare(left, c, dir, species.getColor());
						pos = left;
					}
					done = true;
					step++;
					break;
				
				case Instruction.HOPRIGHT :
					if (world.inRange(right) && world.get(right) == null) {
						world.set(pos, null);
						world.set(right, this);
						WorldMap.displaySquare(pos,' ', dir, species.getColor());
						WorldMap.displaySquare(right, c, dir, species.getColor());
						pos = right;
					}
					done = true;
					step++;
					break;
	
				case Instruction.LEFT :
					dir = ldir;
					WorldMap.displaySquare(pos, c, dir, species.getColor());
					done = true;
					step++;
					break;
	
				case Instruction.RIGHT :
					dir = rdir;
					WorldMap.displaySquare(pos, c, dir, species.getColor());
					done = true;
					step++;
					break;
				
				case Instruction.INFECT :
                    if (world.inRange(adj) && world.get(adj) != null && world.get(adj).species() != species) {
                        world.get(adj).invaded(species);
                        WorldMap.displaySquare(adj, c, world.get(adj).direction(), species.getColor());
                        if (species.programStep(step).getAddress() == 0) {
                        	step = 1;
                        } else {
                        	step = species.programStep(step).getAddress();
                        }
                    } else {
                    	step++;
                    }
                    done = true;
                    break;
                
				case Instruction.INFECTLEFT :
                    if (world.inRange(left) && world.get(left) != null && world.get(left).species() != species) {
                        world.get(left).invaded(species);
                        WorldMap.displaySquare(left, c, world.get(left).direction(), species.getColor());
                        if (species.programStep(step).getAddress() == 0) {
                        	step = 1;
                        } else {
                        	step = species.programStep(step).getAddress();
                        }
                    } else {
                    	step++;
                    }
                    done = true;
                    break;
                
				case Instruction.INFECTRIGHT :
                    if (world.inRange(right) && world.get(right) != null && world.get(right).species() != species) {
                        world.get(right).invaded(species);
                        WorldMap.displaySquare(right, c, world.get(right).direction(), species.getColor());
                        if (species.programStep(step).getAddress() == 0) {
                        	step = 1;
                        } else {
                        	step = species.programStep(step).getAddress();
                        }
                    } else {
                    	step++;
                    }
                    done = true;
                    break;
                
				case Instruction.IFTWOENEMY : 
					if (world.inRange(adj.getAdjacent(dir)) && world.get(adj.getAdjacent(dir)) != null && world.get(adj.getAdjacent(dir)).species() != species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
					
				case Instruction.SET :
					m = species.programStep(step).getAddress();
					step++;
					break;
					
				case Instruction.INC :
					m++;
					step++;
					break;
				
				case Instruction.DEC :
					m--;
					step++;
					break;
	
				case Instruction.IFEQ :
					if (species.programStep(step).getTestCase() == m) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
					
				case Instruction.IFEMPTY :
					if (world.inRange(adj) && world.get(adj) == null) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
				
				case Instruction.IFMEMEQ :
					if (world.inRange(adj) && world.get(adj) != null && world.get(adj).mem() == species.programStep(step).getTestCase()) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
				
				case Instruction.COPYMEM :
					if (world.inRange(adj) && world.get(adj) != null) {
						m = world.get(adj).mem();
					} 
					step++;
					break;	
	
				case Instruction.IFWALL :
					if (!world.inRange(adj)) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
	
				case Instruction.IFSAME :
					if (world.inRange(adj) && world.get(adj) != null && world.get(adj).species() == species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
					
				case Instruction.IFSAMELEFT :
					if (world.inRange(left) && world.get(left) != null && world.get(left).species() == species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
				
				case Instruction.IFSAMERIGHT :
					if (world.inRange(right) && world.get(right) != null && world.get(right).species() == species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
	
				case Instruction.IFENEMY :
					if (world.inRange(adj) && world.get(adj) != null && world.get(adj).species() != species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
				
				case Instruction.IFENEMYLEFT :
					if (world.inRange(left) && world.get(left) != null && world.get(left).species() != species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
				
				case Instruction.IFENEMYRIGHT :
					if (world.inRange(right) && world.get(right) != null && world.get(right).species() != species) {
						step = species.programStep(step).getAddress();
					} else step++;
					break;
	
				case Instruction.IFRANDOM :
					int random = (int) (2 * Math.random()) + 1;
					if (random == 1) step = species.programStep(step).getAddress();
					else step++;
					break;
					
				case Instruction.GO :
					step = species.programStep(step).getAddress();
					break;
			}
		}
	}

	public static void main(String s[]) {
		// tests done visually on the grid instruction by instruction
	}

}	
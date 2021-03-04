/* THOUGHT QUESTIONS
 * 
 * https://docs.google.com/document/d/154oJG52MoM4it3di3DRm517rrtNVYVLFQ_V9tG6h7z4/edit?usp=sharing
 * 
 */

import objectdraw.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TowersOfHanoi extends WindowController implements KeyListener {

	// constant variables for game
	private static final int FIRST_POSTX = 100;
	private static final int LAST_POSTX = 685;
	private static final int POSTY = 225;

	// variables for game
	private Location mouse;
	private Disk dragged_disk;
	private Post selected_post;
	private FilledRect background;
	private Stack<ArrayList<Integer>> moves = new Stack<ArrayList<Integer>>();
	private Queue<ArrayList<Integer>> game = new LinkedList<ArrayList<Integer>>();
	private Text undo_text;
	private FramedRect undo_frame;
	private Text reset_text;
	private FramedRect reset_frame;
	private Text moves_text;
	private Text instructions_text;
	private Text notifications_text;
	private static Timer timer;
	private Text save_text;
	private FramedRect save_frame;
	private String loadPath;
	private boolean running = true;
	private Text victory_message;

	// variables for Posts
	private ArrayList<Post> posts = new ArrayList<Post>();
	private int num_posts = 3;

	// variables for Disks
	private int num_disks = 3;

	// Constructs TowersOfHanoi with loadPath 
	public TowersOfHanoi(String loadPath) {
		this.loadPath = loadPath;
	}

	public void begin() {
		// Sets up background
		background = new FilledRect(0, 0, canvas.getWidth(), canvas.getHeight(), canvas);
		background.setColor(new Color(101,180,130));

		// Sets up buttons
		undo_text = new Text("UNDO", canvas.getWidth() - 150, 50, canvas);
		undo_text.setFontSize(16);	
		undo_text.setColor(Color.ORANGE);

		undo_frame = new FramedRect(undo_text.getX() - 5, undo_text.getY() - 5, undo_text.getWidth() + 10, undo_text.getHeight() + 10, canvas);
		undo_frame.setColor(Color.ORANGE);

		reset_text = new Text("RESET", canvas.getWidth() - 225, 50, canvas);
		reset_text.setFontSize(16);
		reset_text.setColor(Color.ORANGE);

		reset_frame = new FramedRect(reset_text.getX() - 5, reset_text.getY() - 5, reset_text.getWidth() + 10, reset_text.getHeight() + 10, canvas);
		reset_frame.setColor(Color.ORANGE);

		save_text = new Text("SAVE", canvas.getWidth() - 75, 50, canvas);
		save_text.setFontSize(16);
		save_text.setColor(Color.ORANGE);

		save_frame = new FramedRect(save_text.getX() - 5, save_text.getY() - 5, save_text.getWidth() + 10, save_text.getHeight() + 10, canvas);
		save_frame.setColor(Color.ORANGE);

		reset_frame = new FramedRect(reset_text.getX() - 5, reset_text.getY() - 5, reset_text.getWidth() + 10, reset_text.getHeight() + 10, canvas);
		reset_frame.setColor(Color.ORANGE);

		moves_text = new Text("Number of Moves: " + 0, 50, 50, canvas);
		moves_text.setFontSize(20);
		moves_text.setColor(Color.ORANGE);

		instructions_text = new Text("Type 3-7 to change the number of disks and CONTROL + 3-5 to change number of posts.", 100, canvas.getHeight() - 50, canvas);
		instructions_text.setFontSize(14);
		instructions_text.setColor(Color.ORANGE);

		notifications_text = new Text("", canvas.getWidth()/2, 50, canvas);
		notifications_text.move(-notifications_text.getWidth()/2,0);
		notifications_text.setFontSize(14);
		notifications_text.setColor(Color.ORANGE);

		victory_message = new Text("", (canvas.getWidth() / 2), (canvas.getHeight() / 8 * 7), canvas);
		victory_message.setFontSize(25);
		victory_message.setColor(Color.ORANGE);
		victory_message.hide();

		// Sets up timer
		timer = new Timer(canvas);

		// Sets up the KeyListeners
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);

		if (loadPath != null) {
			loadGame();
		} else {
			build();
		}

	}

	// Builds num_posts Posts with num_disks Disks, approperiately placed
	public void build() {
		running = true;
		victory_message.hide();
		for (int i = posts.size() - 1; i >= 0; i--) {
			posts.get(i).getPost().removeFromCanvas();
			posts.get(i).clear();
			posts.remove(i);
		}
		posts.add(new Post(FIRST_POSTX, POSTY, canvas, num_disks, true));
		double gap = (LAST_POSTX - FIRST_POSTX)/(num_posts - 1);
		for (int i = 1; i < num_posts - 1; i++) {
			Post p = new Post(FIRST_POSTX + i*gap, POSTY, canvas, 0, false);
			p.getPost().sendToBack();;
			posts.add(i, p);
		}
		Post p = new Post(LAST_POSTX, POSTY, canvas, 0, false);
		p.getPost().sendToBack();
		
		posts.add(num_posts - 1, p);
		for (Post z : posts) {
			z.getPost().setColor(Post.COLORS[num_disks]);
		}
		moves_text.setColor(Post.COLORS[num_disks]);
		undo_text.setColor(Post.COLORS[num_disks]);
		undo_frame.setColor(Post.COLORS[num_disks]);
		save_text.setColor(Post.COLORS[num_disks]);
		save_frame.setColor(Post.COLORS[num_disks]);
		reset_text.setColor(Post.COLORS[num_disks]);
		reset_frame.setColor(Post.COLORS[num_disks]);
		instructions_text.setColor(Post.COLORS[num_disks]);
		victory_message.setColor(Post.COLORS[num_disks]);
		timer.getTimer().setColor(Post.COLORS[num_disks]);
		background.sendToBack();
		moves.clear();
		moves_text.setText("Number of Moves: " + moves.size());
		timer.reset();
	}

	// Loads game 
	public void loadGame() {
		try {
			Scanner scanner = new Scanner(new File(loadPath));
			String[] game = scanner.nextLine().split(",");
			num_disks = Integer.parseInt(game[0]);
			num_posts = Integer.parseInt(game[1]);
			for (int i = 2; i < game.length; i+= 2) {
				ArrayList<Integer> move = new ArrayList<Integer>();
				move.add(Integer.parseInt(game[i]));
				move.add(Integer.parseInt(game[i+1]));
				this.game.add(move);
			}
			build();
		} catch (Exception exception) {
			num_disks = 3;
			num_posts = 3;
			build();
			notifications_text.setText("Error loading Game!");
			notifications_text.setColor(Color.RED);
		}
		while(!game.isEmpty()) {
			ArrayList<Integer> move = game.remove();
			moves.push(move);
			Post old_post = posts.get(move.get(0));
			Post new_post = posts.get(move.get(1));
			Disk disk = old_post.getDisks().pop();
			old_post.getDisks_Arr().remove(old_post.getDisks_Arr().size() - 1);
			disk.setPost(new_post);
			disk.getDisk().moveTo(new_post.getNextLocation(disk.getDisk().getWidth(), disk.getDisk().getHeight()));
			new_post.getDisks().push(disk);
			new_post.getDisks_Arr().add(disk);
			moves_text.setText("Number of Moves: " + moves.size());
		}
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	/* If Control down and key typed is between 3 and 5, updates num_posts. If Control isn't down and key typed is between 3 and 7, updates num_disks.\
	 * Restes if space is typed.
	 */
	public void keyTyped(KeyEvent e) {
		if (e.isControlDown() && Character.getNumericValue(e.getKeyChar()) >= 3 && Character.getNumericValue(e.getKeyChar()) <= 5) {
			num_posts = Character.getNumericValue(e.getKeyChar());
			build();
		}
		if (!e.isControlDown() && Character.getNumericValue(e.getKeyChar()) >= 3 && Character.getNumericValue(e.getKeyChar()) <= 7) {
			num_disks = Character.getNumericValue(e.getKeyChar());
			build();
		}
		if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE && !running) {
			running = true;
			victory_message.hide();
			posts.get(0).setDirection(1);
			posts.get(posts.size()-1).setDirection(-1);
			Post temp = posts.get(posts.size() - 1);
			posts.set(posts.size() - 1, posts.get(0));
			posts.set(0, temp);
			posts.get(0).setFirst(true);
			posts.get(posts.size() - 1).setFirst(false);
			moves.clear();
			moves_text.setText("Number of Moves: " + moves.size());
			timer.reset();
		}
	}

	// Updates mouse location when pressed
	public void onMousePress(Location point) {
		if (running) {
			mouse = point;
			for (int i = 0; i < posts.size(); i++) {
				if (posts.get(i).getDisks().size() != 0 && posts.get(i).getDisks().peek().getDisk().contains(point)) {
					dragged_disk = posts.get(i).getDisks().peek();
				}
			}
		}
	}

	// Funtionality for buttons
	public void onMouseClick(Location point) {
		if (running && (undo_frame.contains(point) || undo_text.contains(point)) && !moves.isEmpty()) {
			ArrayList<Integer> last_move = moves.pop();
			Post old_post = posts.get(last_move.get(0));
			Post new_post = posts.get(last_move.get(1));
			Disk disk = new_post.getDisks().pop();
			new_post.getDisks_Arr().remove(new_post.getDisks_Arr().size() - 1);
			disk.setPost(old_post);
			disk.getDisk().moveTo(old_post.getNextLocation(disk.getDisk().getWidth(), disk.getDisk().getHeight()));
			old_post.getDisks_Arr().add(disk);
			old_post.getDisks().push(disk);
			moves_text.setText("Number of Moves: " + moves.size());
		}
		if (running && reset_frame.contains(point) || reset_text.contains(point)) {
			build();
		}	
		if (save_frame.contains(point) || save_text.contains(point) && !moves.isEmpty()) {
			try {
				saveGame();
				notifications_text.setText("Game saved!");
				notifications_text.setColor(Color.GREEN);
			} catch (IOException e) {
				notifications_text.setText("Error saving Game!");
				notifications_text.setColor(Color.RED);
			}
		}
	}

	// Drags dragged_disk
	public void onMouseDrag(Location point) {
		if (dragged_disk != null) {
			dragged_disk.getDisk().move(point.getX() - mouse.getX(), point.getY() - mouse.getY());
			mouse = point;                
		}
	}

	// Places dragged_disk on selected_post
	public void onMouseRelease(Location point) {
		if (dragged_disk != null) {
			for (int i = 0; i < posts.size(); i++) {
				if (posts.get(i).getPost().overlaps(dragged_disk.getDisk())) {
					selected_post = posts.get(i);
				}
			}
			if (selected_post != null && (selected_post.getDisks().size() == 0 || dragged_disk.getDisk().getWidth() < selected_post.getDisks().peek().getDisk().getWidth())) {
				dragged_disk.getDisk().moveTo(selected_post.getNextLocation(dragged_disk.getDisk().getWidth(), dragged_disk.getDisk().getHeight()));
				ArrayList<Integer> move = new ArrayList<Integer>();
				move.add(posts.indexOf(dragged_disk.getPost()));
				move.add(posts.indexOf(selected_post));
				moves.push(move);
				dragged_disk.getPost().getDisks().pop();
				dragged_disk.getPost().getDisks_Arr().remove(dragged_disk.getPost().getDisks_Arr().size() - 1);
				dragged_disk.setPost(selected_post);
				selected_post.getDisks().push(dragged_disk);
				selected_post.getDisks_Arr().add(dragged_disk);
				moves_text.setText("Number of Moves: " + moves.size());
				if (posts.get(posts.size()-1).getDisks().size() == num_disks) {
					winGame();
				}
			} else { 
				dragged_disk.getPost().getDisks().pop();
				dragged_disk.getPost().getDisks_Arr().remove(dragged_disk.getPost().getDisks_Arr().size() - 1);
				dragged_disk.getDisk().moveTo(dragged_disk.getPost().getNextLocation(dragged_disk.getDisk().getWidth(), dragged_disk.getDisk().getHeight()));
				dragged_disk.getPost().getDisks().push(dragged_disk); 
				dragged_disk.getPost().getDisks_Arr().add(dragged_disk);
			}
			dragged_disk = null;
			selected_post = null;
		}	
	}

	// Saves Game as csv file in format that starts with number of disks, then number of posts, then moves in choronological order in (from, to) form
	public void saveGame() throws IOException {
		FileWriter fileWriter = new FileWriter("currentGame.csv");
		fileWriter.write(num_disks + "," + num_posts);
		Queue<ArrayList<Integer>> moves_queue = new LinkedList<ArrayList<Integer>>();
		Queue<ArrayList<Integer>> temp = new LinkedList<ArrayList<Integer>>();
		while (!moves.isEmpty()) {
			moves_queue.add(moves.pop());
		}
		int size = moves_queue.size();
		for (int i = 0; i < size; i++) {
			moves.push(moves_queue.peek());
			moves_queue.add(moves_queue.remove());
		}
		while (!moves.isEmpty()) {
			temp.add(moves.pop());
		}
		while (!temp.isEmpty()) {
			ArrayList<Integer> move = temp.peek(); 
			moves.push(move);
			moves_queue.remove();
			moves_queue.add(temp.remove());
		}
		while (!moves_queue.isEmpty()) {
			ArrayList<Integer> move = moves_queue.remove();
			fileWriter.write("," + move.get(0) + "," + move.get(1));
		}
		fileWriter.flush();
		fileWriter.close();
	}

	// Wins the game
	public void winGame() {
		timer.toggle();
		running = false;
		victory_message.setText(getVictoryMessage(num_disks, num_posts, moves.size(), false));
		victory_message.moveTo((canvas.getWidth() / 2) - victory_message.getWidth() / 2, (canvas.getHeight() / 8 * 7) - victory_message.getHeight() / 2);
		victory_message.show();
	}
	
	// Returns custom victory message based on number of moves
	public static String getVictoryMessage(int disks, int posts, int moves, boolean cli) {
		double optimal_score = Math.pow(2, disks) - 1;
		String message = "You won!";
		if (posts == 3) {
			if (moves == optimal_score) {
				message = "You won with the best possible score!";
			} else if (moves > optimal_score && moves  <= optimal_score*1.2) {
				message = "You won with an almost optimum score!";
			}
		}
		if (!cli) {
			message += " Press SPACE to restart.";
		}
		return message;
	}

	public static void main(String[] args) { 
		String game = null;
		boolean cli = false;
		for (String arg : args) {
			if (arg.equals("--cli")) {
				cli = true;
			} else {
				game = arg;
			} 
		} 
		if (cli) {
			ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
			int moves = 0;
			boolean running = true;
			Scanner s = new Scanner(System.in);
			System.out.println("Insert Number of Disks (3-7): ");
			int numdisks = s.nextInt();
			System.out.println("Insert Number of Posts (3-5): ");
			int numposts = s.nextInt();
			for (int i = 0; i < numposts; i++) {
				stacks.add(new Stack<Integer>());
			}
			if (numdisks <= 7 && numdisks >= 3){
				for (int i = numdisks; i > 0; i--) {
					stacks.get(0).push(i);
				}
			}
			while (running) {
				for (int i = 1; i <= numposts; i++) {
					System.out.println("Post " + i + ": " + stacks.get(i-1));
				}
				System.out.println("Move top disk from post number ");
				int from = s.nextInt() - 1;
				System.out.println("to Post number ");
				int to = s.nextInt() - 1;
				if (stacks.get(to).empty() || stacks.get(to).peek() > stacks.get(from).peek()) {
					stacks.get(to).add(stacks.get(from).pop());
					moves++;
				} 
				if(stacks.get(stacks.size()-1).size() == numdisks) {
					System.out.println(getVictoryMessage(numdisks, stacks.size(), moves, true));
					running = false;
				}
			}
		} else {
			TowersOfHanoi towersOfHanoi = new TowersOfHanoi(game);
			towersOfHanoi.startController(800, 800); 
		}
	}

}


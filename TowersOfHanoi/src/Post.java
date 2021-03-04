import objectdraw.*;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.Color;

public class Post extends ActiveObject {
	
	// constant variables for Post
	private static final int POST_WIDTH = 15;
	private static final int POST_HEIGHT = 350;
	private static final int FIRST_POSTX = 100;
	private static final int LAST_POSTX = 685;
	private static final int BDISK_WIDTH = 100;
	public static final Color[] COLORS = {new Color(237,84,40), new Color(238,109,58), new Color(240,135,80), new Color(243,162,103), new Color(246,189,127), new Color(250,217,153), new Color(254, 245, 180), new Color(255,254,205)};
	
	// variables for Post
	private FilledRect post;
	private Stack<Disk> disks = new Stack<Disk>();
	private ArrayList<Disk> disks_arr= new ArrayList<Disk>();
	private int direction;
	private boolean first;
	
	// Constructs Post FilledRect and Disks
	public Post(double posX, double posY, DrawingCanvas canvas, int size, boolean first) {
		post = new FilledRect(posX, posY, POST_WIDTH, POST_HEIGHT, canvas);
		post.setColor(Color.WHITE);
		if (size > 0) {
			double height = (POST_HEIGHT - 50)/size;
			for (int i = 0; i < size; i++) {
				double width = BDISK_WIDTH * Math.pow(0.8, disks.size());
				Disk disk = new Disk(getNextLocation(width, height), width, height, canvas, COLORS[i], this);
				disks.push(disk);
				disks_arr.add(disk);
			}
		}
		direction = 0;
		this.first = first;
		start();
	}
	
	public void run() {
		if (first) {
			while (post.getX() <= LAST_POSTX-POST_WIDTH) {
				pause(100);
				post.move(direction*15, 0);
				for (int i = 0; i < disks_arr.size(); i++) {
					disks_arr.get(i).getDisk().move(direction*15, 0);
				}
			}
			direction = 0;
		} else {
			while (post.getX() >= FIRST_POSTX+POST_WIDTH) {
				pause(100);
				post.move(direction*15, 0);
				for (int i = 0; i < disks_arr.size(); i++) {
					disks_arr.get(i).getDisk().move(direction*15, 0);
				}
			}
			direction = 0;
		}
	}
	
	// Clears all Disks on Post
	public void clear() {
		while (!disks.isEmpty()) {
			disks.pop().getDisk().removeFromCanvas();
		}
		disks_arr.clear();
	}
	
	// Returns Post FilledRect
	public FilledRect getPost() {
		return post;
	}
	
	// Returns Disks stack
	public Stack<Disk> getDisks() {
		return disks;
	}
	
	// Returns Disks ArrayList
	public ArrayList<Disk> getDisks_Arr() {
		return disks_arr;
	}
	
	// Sets Post direction
	public void setDirection(int direction) {
		this.direction = direction;
	}
	 
	public void setFirst(boolean newf) {
		this.first = newf;
	}
	
	// Returns Location for next Disk
	public Location getNextLocation(double width, double height) {
		return new Location(post.getX() - width/2 + POST_WIDTH/2, post.getY() + POST_HEIGHT - (disks.size() + 1)*height);
	}
	
}

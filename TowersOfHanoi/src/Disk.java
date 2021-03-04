import java.awt.Color;
import objectdraw.*;

public class Disk extends ActiveObject {
	
	// variables for Disk
	private FilledRect disk;
	private Post post;
	private int direction;
	
	// Constucts Disk FilledRect
	public Disk(Location location, double width, double height, DrawingCanvas canvas, Color color, Post post) {
		disk = new FilledRect(location, width, height, canvas);
		disk.setColor(color);
		this.post = post;
		direction = 0;
	}

	public void run() {
		while (true) {
			pause(100);
			disk.move(direction*15, 0);
		}
	}
	
	// Returns Disk FilledRect
	public FilledRect getDisk() {
		return disk;
	}
		
	// Returns Disk's Post
	public Post getPost() {
		return post;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	// Sets Disk's Post
	public void setPost(Post new_post) {
		post = new_post;
	}
	
}

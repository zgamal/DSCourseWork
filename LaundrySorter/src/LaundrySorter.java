import objectdraw.*;
import java.awt.*;

public class LaundrySorter extends WindowController {
	
	private FramedRect whites; // Instance variables for washing baskets
	private FramedRect darks;
	private FramedRect colors;
	private Text lwhites;
	private Text ldarks;
	private Text lcolors;

	private FilledRect swatch; // Instance variables for color swatch
	private FramedRect fswatch;
	private RandomIntGenerator colorgen; 
	private Color color; 
	private FramedRect answer;
	private int ncorrect;
	private int nincorrect;
	private Text lncorrect;
	private Text lnincorrect;
	private int red;
	private int green;
	private int blue;
	
	private Location lpoint; //Instance variable for mouse's last point
				
	public void begin() {
		whites = new FramedRect(55, 225, 70, 70, canvas); // Draw washing baskets
		darks = new FramedRect(162, 225, 70, 70, canvas);
		colors = new FramedRect(272, 225, 70, 70, canvas);
		lwhites = new Text("whites", 72, 255, canvas);
		ldarks = new Text("darks", 182, 255, canvas);
		lcolors = new Text("colors", 292, 255, canvas);
		
		swatch = new FilledRect(170, 50, 60, 60, canvas); // Draw color swatch 
		fswatch = new FramedRect(170, 50, 60, 60, canvas);
		colorgen = new RandomIntGenerator(0, 255);
		red = colorgen.nextValue();
		green = colorgen.nextValue();
		blue = colorgen.nextValue();
		color = new Color(red, green, blue);
		swatch.setColor(color);
		if (red + green + blue < 230) {
			answer = darks;
		} else if (red + green + blue > 600) {
			answer = whites;
		} else {
			answer = colors;
		} 
		ncorrect = 0;
		nincorrect = 0;
		lncorrect = new Text("Correct = " + ncorrect, 110, 325, canvas);
		lnincorrect = new Text("Inorrect = " + nincorrect, 200, 325, canvas);
	}

	public void onMousePress(Location point) {
        lpoint = point;
    }
	
	public void onMouseDrag(Location point) {
        if (swatch.contains(lpoint)) {
            swatch.move(point.getX() - lpoint.getX(), point.getY() - lpoint.getY());
            fswatch.move(point.getX() - lpoint.getX(), point.getY() - lpoint.getY());
            lpoint = point;                
        }
    }
	
	public void onMouseRelease(Location point) {
		if (swatch.contains(lpoint)) {
			if (answer.contains(point)) {
				ncorrect++;
				lncorrect.setText("Correct = " + ncorrect);
				red = colorgen.nextValue();
				green = colorgen.nextValue();
				blue = colorgen.nextValue();
				color = new Color(red, green, blue);
				swatch.setColor(color);
				if (red + green + blue < 230) {
					answer = darks;
				} else if (red + green + blue > 600) {
					answer = whites;
				} else {
					answer = colors;
				} 
			} else if (!(whites.contains(point)) && !(darks.contains(point))&& !(colors.contains(point))) {
				
			} else {
				nincorrect++;
				lnincorrect.setText("Incorrect = " + nincorrect);
			}
			swatch.moveTo(170, 50);
			fswatch.moveTo(170, 50);
		}
	}

	public static void main(String[] args) {
		new LaundrySorter().startController(400, 400);
	}	 
}
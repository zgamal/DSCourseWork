import objectdraw.*;

public class NonEmptyScribbleCollection implements ScribbleCollectionInterface {
	
	// new non-empty Scribble.
	private ScribbleInterface scribble;
	
	// non-empty Scribbles collection.
	private ScribbleCollectionInterface scribbles;
	
	public NonEmptyScribbleCollection(ScribbleInterface scribble, ScribbleCollectionInterface scribbles) {
		this.scribble = scribble;
		this.scribbles = scribbles;
	}
	
	// pre:
	// post: returns the scribble that contains the point;
	//    if none contain it, returns an empty scribble
	public ScribbleInterface scribbleSelected(Location point) {
		if (scribble.contains(point)) return scribble;
		return scribbles.scribbleSelected(point);  
	}
	
	// pre:
	// post: returns the first scribble in the list;
	//   returns null if the list is empty.
	public ScribbleInterface getFirst() {
		return scribble;
	}
	
	// pre:
	// post: returns the list of scribbles excluding the first.
	//   returns an empty scribble collection if the list is empty.
	public ScribbleCollectionInterface getRest() {
		return scribbles;
	}

}
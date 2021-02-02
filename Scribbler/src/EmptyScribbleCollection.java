import objectdraw.*;

public class EmptyScribbleCollection implements ScribbleCollectionInterface {
	
	// empty Scribble.
	public static final EmptyScribble EMPTY_SCRIBBLE = new EmptyScribble();
	
	// pre:
	// post: returns the scribble that contains the point;
	public ScribbleInterface scribbleSelected(Location point) {
		return EMPTY_SCRIBBLE;   // change if necessary!
	}
	
	// pre: 
	// post: returns the first scribble in the collection;
	//   returns empty scribble if the collection is empty.
	public ScribbleInterface getFirst(){
		return EMPTY_SCRIBBLE;   // change if necessary!
	}
	
	// pre:
	// post: returns the list of scribbles excluding the first.
	//   returns an empty scribble collection if the list is empty.
	public ScribbleCollectionInterface getRest() {
		return this;
	}

}
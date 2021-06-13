import java.util.Iterator;

public class BinaryTree<E> {
	
	/* Instance Variables */
	private E data;
	private BinaryTree<E> leftChild;
	private BinaryTree<E> rightChild;
	private BinaryTree<E> parent;
	
	/* Constructors */
	public BinaryTree() {
		/* Default constructor makes an empty tree */
		data = null;
		leftChild = this;
		rightChild = this;
		parent = null;
	}
	
	public BinaryTree(E value) {
		/* Leaf nodes */
		this(value, null, null);
	}
	
	public BinaryTree(E value, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
		data = value;
		if (leftChild == null) leftChild = new BinaryTree<E>();
		if (rightChild == null) rightChild = new BinaryTree<E>();
		setLeft(leftChild);
		setRight(rightChild);
	}

	
	/* Accessor Methods */
	public E value() {
		return this.data;
	}
	
	
	public BinaryTree<E> left() {
		return this.leftChild;
	}
	
	public BinaryTree<E> right() {
		return this.rightChild;
	}
	
	public boolean isEmpty() {
		return this.data == null;
	}
	
	/* Iterator Methods */
	public int size() {
		if (isEmpty()) return 0;
		return  1 + leftChild.size() + rightChild.size();
	}
	
	public BinaryTree<E> root() {
		if (parent == null) return this;
		return parent.root();
	}
	
	public int height() {
		if (isEmpty()) return 0;
		int leftH = leftChild.size();
		int rightH = rightChild.size();
		if (leftH > rightH) return 1 + leftH;
		return 1 + rightH;
	}
	
	public int depth() {
		if (parent == null) return 0;
		return 1 + parent.depth();
	}
	
//	public Iterator<E> iterator() {
//	}
	
	/* Mutator Methods */	
	public void setLeft(BinaryTree<E> subTree) {
		if (isEmpty()) return;
		if (leftChild != null && leftChild.parent == this) leftChild.setParent(null);
		this.leftChild = subTree;
		leftChild.setParent(this);
	}
	
	public void setRight(BinaryTree<E> subTree) {
		if (isEmpty()) return;
		if (rightChild != null && rightChild.parent == this) rightChild.setParent(null);
		this.rightChild = subTree;
		rightChild.setParent(this);
	}	
	
	protected void setParent(BinaryTree<E> subTree) {
		this.parent = subTree;
	} 

}

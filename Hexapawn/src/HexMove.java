public class HexMove {
	
    private int from;
    private int to;
    protected int cols;
    
    public HexMove(int from, int to, int cols) {
		this.from = from;
		this.to = to;
		this.cols = cols;
    }
    
    public int from() {
    	return this.from;
    }
    
    public int to() {
    	return this.to;
    }
    
    public String toString() {
        return "Move from [" + (1 + (from/cols)) + "," + (1 + (from%cols)) + "] " + "to [" + (1 + (to/cols)) + "," + (1 + (to%cols)) + "].";
    } 

} 
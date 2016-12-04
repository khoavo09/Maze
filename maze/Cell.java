/**
 * Cell.java: Represent a cell in the maze with index for row and col
 */
package maze;

public class Cell<T> extends Vertex {
	int x;
	int y;

	public Cell(T label,int x,int y) {
		super(label);
		this.x = x;
		this.y = y;
	}
        
    public Cell(T label) {
		super(label);
		x=0;
		y=0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	

}

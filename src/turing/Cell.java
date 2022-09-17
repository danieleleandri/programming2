package turing;


/**
 * Represents one cell on a Turing Machine tape.
 */
public class Cell {
	public Cell(Cell prev, Cell next,char content) {
		this.content = content;
		this.left = left;
		this.right = right;
	}
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
	public char content;  // The character in this cell.
	public Cell left;     // Pointer to the cell to the right of this one.
	public Cell right;     // Pointer to the cell to the left of this one.
	
}



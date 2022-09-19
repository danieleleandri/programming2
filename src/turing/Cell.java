package turing;

/**
 * Represents one cell on a Turing Machine tape.
 */
public class Cell {
	/**
	 * The constructor of a new element of the Tape
	 * @param left this is a pointer to the left element of the tape
	 * @param right this is a pointer to the left element of the tape
	 * @param content the char content of the created Cell
	 */
	public Cell(Cell left, Cell right,char content) {
		this.content = content;
		this.left = left;
		this.right = right;
	}
	//Getters...............................
	/**
	 * Getter method for the Cell Content
	 * @return returns the char from the current cell
	 */
	public char getContent() {
		return content;
	}
	/**
	 * Get the left pointer of the currentCell
	 * @return the left pointer
	 */
	public Cell getLeft() {
		return(this.left);
	}
	/**
	 * Get the right pointer of the currentCell
	 * @return the right pointer
	 */
	public Cell getRight() {
		return(this.right);
	}
	//Setters...............................
	/**
	 * changes the char in the current cell to the specified value
	 * @param content the new value for the Cell
	 */
	public void setContent(char content) {
		this.content = content;
	}
	/**
	 * The method is to set the left pointer of the Cell
	 * @param left  the left pointer of the Cell
	 */
	public void setLeft(Cell left) {
		this.left = left;
	}
	/**
	 * The method is to set the right pointer of the Cell
	 * @param right  the left pointer of the Cell
	 */
	public void setRight(Cell right) {
		this.right = right;
	}
	private char content;  // The character in this cell.
	private Cell left;     // Pointer to the cell to the right of this one.
	private Cell right;     // Pointer to the cell to the left of this one.
	
}
/**/


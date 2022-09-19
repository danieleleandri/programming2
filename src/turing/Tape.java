package turing;

/**
 * This class represent the Turing machin tapes. It is an ADT that:
 * 1) Could be read at once using String getTapeContent();
 * 2) Could be use to obtain the current cell's value
 * 3) Could be use to update the current cell value using setContent(char newContent)
 * 
 * The tape's cursor could be right/left moved by using the methods:
 * 1) moveLeft() this method will also add a new cell (initialized with the ' ' char) on the left of the tape if needed.
 * 2) moveRight() this method will also add a new cell (initialized with the ' ' char) on the right of the tape if needed.
 * @author xxx.yyy Hidden because of the evaluation
 *
 */
public class Tape {
	/**
	 * The following are important bookmarks of the tape:
	 * - startTape is a pointer to the leftmost element of the tape
	 * - currentCell is a pointer to the rightmost element of the tape
	 * - currentCell is the pointer to the current cell on the tape
	 */
	public static Cell startTape, endTape;
	public static Cell currentCell;

	/**
	 * Tape() is the constructor of a new Tape instance.
	 * The method takes no parameters, creates a new cell initialized with ' '
	 * and then sets both startTape and endTape to point to currentCell; 
	 */
	public Tape() {
		currentCell = new Cell(null, null, ' ');
		startTape = currentCell;
		endTape = currentCell;
	}


	public void goToStart() {
		currentCell = startTape;
	}

	public void gotToEnd() {
		currentCell = endTape;
	}
	/**
	 * The method moves the cursor (currentCell) towards the left of the Tape.
	 * If it is needed a new cell will be automatically added before the 
	 * leftmost Cell of the Tape.
	 */
	public void moveLeft() {
		// if (currentCell.left == null) throw (new IllegalStateException("The method
		// thrown an IllegalStateExcpetion"));
		if (currentCell.getLeft() == null) {
			currentCell.setLeft( new Cell(null, currentCell, ' '));
			(currentCell.getLeft()).setRight(currentCell);
			startTape = currentCell.getLeft();
		}
		currentCell = currentCell.getLeft();
	}
	/**
	 * The method moves the cursor (currentCell) towards the right of the Tape.
	 * If it is needed a new cell will be automatically added after the 
	 * righttmost Cell of the Tape.
	 */
	public void moveRight() {
		if (currentCell.getRight() == null) {
			currentCell.setRight(new Cell(currentCell, null, ' '));
			(currentCell.getRight()).setLeft( currentCell );
			endTape = currentCell.getRight();
		}
		currentCell = currentCell.getRight();
	}

	//Getters -------------------------------
	/** 
	 * The method returns the content of the current Cell
	 * @return a char with the content of the current Cell
	 */
	public char getContent() {
		return currentCell.getContent();
	}

	/**
	 * The method return the whole content of the Tape
	 * @return a String containing the whole text present in the Tape
	 */
	public String getTapeContents() {
		String stringContent = "";
		Cell printItem = startTape;
		System.out.println();

		while (printItem != null) {
			stringContent += printItem.getContent();
			printItem = printItem.getRight();
		}

		return stringContent;
	}
	//Setters -------------------------------
	/**
	 * The method sets the content of the current Cell
	 * @param newContent is the char to be inserted in the currentCell
	 */
	public void setContent(char newContent) {
		currentCell.setContent( newContent);
	}

	
}

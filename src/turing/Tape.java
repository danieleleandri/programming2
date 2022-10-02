package turing;

public class Tape {
	int state;
	public static Cell startTape, endTape;
	public static Cell currentCell;

	public Tape() {
		currentCell = new Cell(null, null, ' ');
		startTape = currentCell;
		endTape = currentCell;
	}

	public char getContent() {
		return currentCell.content;
	}

	public void goToStart() {
		currentCell = startTape;
	}

	public void gotToEnd() {
		currentCell = endTape;
	}

	public String getTapeContents() {
		String stringContent = "";
		Cell printItem = startTape;
		System.out.println();

		while (printItem != null) {
			stringContent += printItem.content;
			printItem = printItem.right;
		}

		return stringContent;
	}

	public void setContent(char newContent) {
		currentCell.content = newContent;
	}

	public void moveLeft() throws IllegalStateException {
		if (currentCell.left == null) {
			currentCell.left = new Cell(null, currentCell, ' ');
			(currentCell.left).right = currentCell;
			startTape = currentCell.left;
		}
		currentCell = currentCell.left;
	}

	public void moveRight() {
		if (currentCell.right == null) {
			currentCell.right = new Cell(currentCell, null, ' ');
			(currentCell.right).left = currentCell;
			endTape = currentCell.right;
		}
		currentCell = currentCell.right;
	}

}

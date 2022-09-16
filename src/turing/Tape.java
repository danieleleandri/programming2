package turing;

import javax.xml.stream.events.EndDocument;

public class Tape  {
	int state;
	public static TapeItem startList,endList;
	public static TapeItem currentState;

	public Tape() {
		currentState = new TapeItem(null,null,1,' ');
		state = currentState.state;
		startList = currentState;
		endList = currentState;
	}
	
	public char getContent() {		
		return currentState.content;
	}

	public void goToStart() {
		currentState = startList;
	}
	
	public void gotToEnd() {
		currentState = endList;
	}
	public String getTapeContents() {
		String stringContent = "";
		TapeItem printItem = startList;
		System.out.println();
		
		while (printItem != null) {
			stringContent += printItem.content;
			printItem = printItem.right;
		}
		
		
		
//		
//		boolean cycle = false;
//		if(printItem.state != -1)  cycle = true;	
//		while(cycle){
//			stringContent += printItem.content;
//			if(printItem.state != -1) {
//				printItem = printItem.right;	
//			} else {
//				cycle = false;
//			}
//		}
		
		return stringContent;
	}


		
	public void setState(int state) {
		currentState.setState(state);
	}
			

	public void setContent(char newContent) {
		currentState.setContent(newContent);		
	}

	public void moveLeft() throws IllegalStateException {
		if (currentState.left == null) throw (new IllegalStateException("The method thrown an IllegalStateExcpetion"));
		currentState = currentState.left;
		state = currentState.state;
	}
	
	public void moveRight() {
		if(currentState.right == null) {
			currentState.right = new TapeItem (currentState,null,0, ' ' );
			endList = currentState.right;
		}
		currentState = currentState.right;
		state = currentState.state;
	}

}

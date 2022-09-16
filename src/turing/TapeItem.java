package turing;

public class TapeItem {
	TapeItem left;
	TapeItem right;
	int state;
	char content;
	public TapeItem (TapeItem left, TapeItem right, int state, char content) {
		this.left = left;
		this.right = right;
		this.state = state;
		this.content = content;
	}
	public TapeItem getLeft() {
		return left;
	}
	public void setLeft(TapeItem left) {
		this.left = left;
	}
	public TapeItem getRight() {
		return right;
	}
	public void setRight(TapeItem right) {
		this.right = right;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public char getContent() {
		return content;
	}
	public void setContent(char content) {
		this.content = content;
	}
 
}

package ec_takara;

import java.io.Serializable;

public class PageBean implements Serializable {
	private int next;
	private int previous;
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	
}


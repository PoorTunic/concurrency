package org.iut.nantes;

public class Fork {

	private int forkNumber;
	private boolean use = false;

	public Fork(int i) {
		forkNumber = i;
	}

	public int getForkNumber() {
		return forkNumber;
	}
	
	public boolean isUse() {
		return !this.use;
	}
	
	public void put() {
		this.use = false;
	}
	
	public void gotten() {
		this.use = true;
	}	
}
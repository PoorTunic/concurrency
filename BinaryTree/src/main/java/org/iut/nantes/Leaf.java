package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class Leaf {
	
	int value;
	Leaf leftLeaf;
	Leaf rightLeaf;

	public Leaf(int value) {
		this.value = value;
		rightLeaf = null;
		leftLeaf = null;
	}

	public int getValue() {
		return value;
	}
}

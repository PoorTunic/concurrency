package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class Run implements Runnable {

	private int nb;
	private Tree tree;

	/**
	 * 
	 * @param nb
	 * @param a
	 */
	public Run(int value, Tree a) {
		this.nb = value;
		this.tree = a;
	}

	public void run() {
		tree.add(nb);
	}
}

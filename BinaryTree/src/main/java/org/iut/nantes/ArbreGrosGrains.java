package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class ArbreGrosGrains implements Tree {
	Leaf racine;

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private Leaf addRecursive(Leaf current, int value) {
		if (current == null) {
			return new Leaf(value);
		}

		if (value < current.value) {
			current.leftLeaf = addRecursive(current.leftLeaf, value);
		} else if (value > current.value) {
			current.rightLeaf = addRecursive(current.rightLeaf, value);
		} else {
			return current;
		}

		return current;
	}

	public synchronized void add(int value) {
		racine = addRecursive(racine, value);
	}

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private boolean containsNoeudRecursive(Leaf current, int value) {
		if (current == null) {
			return false;
		}
		if (value == current.value) {
			return true;
		}
		return value < current.value ? containsNoeudRecursive(current.leftLeaf, value)
				: containsNoeudRecursive(current.rightLeaf, value);
	}

	/**
	 * 
	 */
	public synchronized boolean contains(int value) {
		return containsNoeudRecursive(racine, value);
	}

	/**
	 * 
	 * @param racine
	 * @return
	 */
	private int findSmallestValue(Leaf racine) {
		return racine.leftLeaf == null ? racine.value : findSmallestValue(racine.leftLeaf);
	}

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private Leaf deleteRecursive(Leaf current, int value) {
		if (current == null) {
			return null;
		}

		if (value == current.value) {
			if (current.leftLeaf == null && current.rightLeaf == null) {
				return null;
			} else if (current.rightLeaf == null) {
				return current.leftLeaf;
			} else if (current.leftLeaf == null) {
				return current.rightLeaf;
			} else {
				int smallestValue = findSmallestValue(current.rightLeaf);
				current.value = smallestValue;
				current.rightLeaf = deleteRecursive(current.rightLeaf, smallestValue);
				return current;
			}
		}
		if (value < current.value) {
			current.leftLeaf = deleteRecursive(current.leftLeaf, value);
			return current;
		}
		current.rightLeaf = deleteRecursive(current.rightLeaf, value);
		return current;
	}

	/**
	 * 
	 */
	public synchronized void delete(int value) {
		racine = deleteRecursive(racine, value);
	}

	/**
	 * 
	 * @param node
	 */
	public void traverse(Leaf node) {
		if (node != null) {
			traverse(node.leftLeaf);
			System.out.print(" " + node.value);
			traverse(node.rightLeaf);
		}
	}

	/**
	 * 
	 */
	public String toString() {
		traverse(racine);
		return "";
	}

}
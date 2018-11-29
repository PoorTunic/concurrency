package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class ArbreFinGrains implements Tree {

	Leaf racine;

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private synchronized Leaf addRecursive(Leaf current, int value) {
		// ajoute le noeud
		if (current == null) {
			return new Leaf(value);
		}

		// si la valeur est plus petite que le noeud courant alors il ajoute la valeur
		// dans le noeud a gauche
		if (value < current.value) {
			current.leftLeaf = addRecursive(current.leftLeaf, value);

			// si la valeur est plus grande que le noeud courant alors il ajoute la valeur
			// dans le noeud a droite
		} else if (value > current.value) {
			current.rightLeaf = addRecursive(current.rightLeaf, value);
			// sinon la valeur existe déjà alors il ne se passe rien
		} else {
			return current;
		}
		return current;
	}

	public void add(int value) {
		racine = addRecursive(racine, value);
	}

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private synchronized boolean containsNoeudRecursive(Leaf current, int value) {
		// si le noeud est null alors il ne contient pas la valeur recherchée
		if (current == null) {
			return false;
		}
		// si la valeur du noeud courant est egal à la valeur recharché alors on
		// retourne vrai
		if (value == current.value) {
			return true;
		}
		return value < current.value ? containsNoeudRecursive(current.leftLeaf, value)
				: containsNoeudRecursive(current.rightLeaf, value);
	}

	public boolean contains(int value) {
		return containsNoeudRecursive(racine, value);
	}

	/**
	 * 
	 * @param racine
	 * @return
	 */
	private synchronized int findSmallestValue(Leaf racine) {
		return racine.leftLeaf == null ? racine.value : findSmallestValue(racine.leftLeaf);
	}

	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */
	private synchronized Leaf deleteRecursive(Leaf current, int value) {
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

	public void delete(int value) {
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

	public String toString() {
		traverse(racine);
		return "";
	}

	public static void main(String[] args) {
		ArbreFinGrains test = new ArbreFinGrains();
		test.add(5);
		test.add(5);

	}
}
package org.iut.nantes;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

/**
 * 
 * @author dagui
 *
 */
public class App {

	private static int nb_Int = 10000;
	private static int max = 200;
	private static int min = 0;
	private static Random rand = new Random();

	/**
	 * Méthode principale
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		System.out.println("ARBRE DE RECHERCHE");
		System.out.println("---------------------------------------------------------------------------------" + "\n");

		List<Integer> ensemble = new ArrayList<Integer>();

		for (int i = 0; i < nb_Int; i++) {
			int randomNum = rand.nextInt((max - min) + 1) + min;
			ensemble.add(randomNum);
		}

		Tree ag = new ArbreGrosGrains();
		Thread threads[] = new Thread[ensemble.size()];

		long start = System.currentTimeMillis();

		for (int cpt = 0; cpt < ensemble.size(); ++cpt) {
			Run myRunnable = new Run(ensemble.get(cpt), ag);
			threads[cpt] = new Thread(myRunnable);
			threads[cpt].start();
		}
		// Permet d'attendre la fin de l'éxécution
		for (int cpt = 0; cpt < ensemble.size(); ++cpt) {
			try {
				threads[cpt].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Arbre verrouillage à gros grains" + "\n");
		System.out.println(ag.toString());
		System.out.println("Temps d'éxécution : " + (System.currentTimeMillis() - start) + " ms" + "\n");

		Tree af = new ArbreFinGrains();

		long start2 = System.currentTimeMillis();
		for (int cpt = 0; cpt < ensemble.size(); ++cpt) {
			Run myRunnable = new Run(ensemble.get(cpt), af);
			threads[cpt] = new Thread(myRunnable);
			threads[cpt].start();
		}
		// Permet d'attendre la fin de l'éxécution
		for (int cpt = 0; cpt < ensemble.size(); ++cpt) {
			try {
				threads[cpt].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Arbre verrouillage à grains fins" + "\n");
		System.out.println(af.toString());
		System.out.println("Temps d'éxécution : " + (System.currentTimeMillis() - start2) + " ms" + "\n");

		System.out.println("END !");
		System.out.println("---------------------------------------------------------------------------------" + "\n");
	}
}

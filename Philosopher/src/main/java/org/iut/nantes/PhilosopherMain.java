package org.iut.nantes;

public class PhilosopherMain {
	
	public static void main(String[] args) {
		
		// Five philosophers in the table
		Philosopher philosophers[] = new Philosopher[5];
		
		// Five forks in the table
		Fork forks[] = new Fork[5];
		
		// We need manage the forks
		EatingManager forkManager = new EatingManager();

		for (int i = 0; i < 5; i++) {
			philosophers[i] = new Philosopher(i, forkManager);
			forks[i] = new Fork(i);
		}
		
		// We declare the order of the forks
		philosophers[0].setForks(forks[0], forks[4]);
		philosophers[1].setForks(forks[1], forks[0]);
		philosophers[2].setForks(forks[2], forks[1]);
		philosophers[3].setForks(forks[3], forks[2]);
		philosophers[4].setForks(forks[4], forks[3]);
		
		// All philosopher start to eat
		for (int i = 0; i < philosophers.length; i++) {
			philosophers[i].start();
		}
	}

}
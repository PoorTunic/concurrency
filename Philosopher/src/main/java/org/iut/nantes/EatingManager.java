package org.iut.nantes;

public class EatingManager extends Thread {
	
	public synchronized void getForks(int numberPhilosopher, Fork forkRight, Fork forkLeft) {
		while (forkRight.isUse() == false || forkLeft.isUse() == false) {
			try {
				System.out.println("Philosopher "+ numberPhilosopher + " is wating. . .");
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		forkRight.gotten();
		forkLeft.gotten();
	}

	public synchronized void setForks(int Philosophe, Fork forkRight, Fork forkLeft) {
		forkRight.put();
		forkLeft.put();
		this.notify();
	}
}
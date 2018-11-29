package org.iut.nantes;

public class Philosopher extends Thread {
	
	int numberPhilosopher;
	int eating = 0;
	EatingManager eatingManager;
	Fork leftFork;
	Fork rightFork;

	public Philosopher(int numberPhilosopher, EatingManager eatingManager) {
		this.numberPhilosopher = numberPhilosopher;
		this.eatingManager = eatingManager;
	}

	public void setForks(Fork rightFork, Fork leftFork) {
		this.rightFork = rightFork;
		this.leftFork = leftFork;
	}
	
	public void think() {
		try {
			System.out.println("Philosopher " + numberPhilosopher + " is thinking");
			sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	public void eating() {
		try {
			StringBuilder string = new StringBuilder();
			string.append("Philosopher number ").append(numberPhilosopher).append(" is eating\n").append("Fork ").append(rightFork.getForkNumber()).append(" and ").append(leftFork.getForkNumber()).append(" are ocuped");
			System.out.println(string);
			sleep(4000);
		} catch (InterruptedException e) {
		}
		StringBuilder string = new StringBuilder();
		string.append("Fork ").append(leftFork.getForkNumber()).append(" and ").append(rightFork.getForkNumber()).append(" are free");
		System.out.println(string);
	}
	
	@Override
	public void run() {
		// If are two or less philosophers thinking, the current philosopher is going to think
		while (eating < 3) {
			this.think();
			
			// Philosopher takes the forks
			eatingManager.getForks(numberPhilosopher, rightFork, leftFork);
			
			// He starts to eat
			this.eating();
			eating++;
			
			// He ends to eat and put again the forks in the table
			eatingManager.setForks(numberPhilosopher, rightFork, leftFork);
		}
	}
}
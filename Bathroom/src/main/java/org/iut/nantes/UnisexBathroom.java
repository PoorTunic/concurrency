package org.iut.nantes;

/**
 * @author dagui
 *
 */
public class UnisexBathroom extends Thread {

	private int personCount = 0;

	private boolean actualSex = false;

	// Here, we verify if in the bathroom are someone and the sex conditions
	public synchronized void enter(Person person) {
		// If the bathroom is empty or get the last sex person
		if (personCount < 1 && this.actualSex != person.getSex()) {
			this.actualSex = person.getSex();
		}

		// If there are more people in the bathroom, we will check every person and his
		// sex
		while (personCount > 2 || this.actualSex != person.getSex()) {
			try {
				// We identify her sex
				if (person.getSex()) {
					System.out.println("He waits");
				} else {
					System.out.println("She waits");
				}
				
				wait();
				
				// Here is to know the last sex person and know his sex to put the actual sex in the bathroom
				if (personCount < 1 && this.actualSex != person.getSex()) {
					this.actualSex = person.getSex();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// When someone enter in the bathroom, we add one person
		this.personCount++;

		if (person.getSex()) {
			System.out.println("He enters in the bathroom");
		} else {
			System.out.println("She enters in the bathroom");
		}
	}

	public synchronized void exit(Person person) {
		// We rest a person in the person counter
		this.personCount--;
		
		// We notify that thread is free
		notifyAll();
		
		if (person.getSex()) {
			System.out.println("He exits");
		} else {
			System.out.println("She exits");
		}
	}

}

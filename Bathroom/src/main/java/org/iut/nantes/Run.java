package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class Run implements Runnable{


	// The bathroom requires a person and the bathroom
	private Person person;
	private UnisexBathroom bathroom;
	
	// Default constructor
	public Run(Person person, UnisexBathroom bathroom) {
		super();
		this.person = person;
		this.bathroom= bathroom;
	}

	// Here, we execute the Thread
	public void run() {		
		try {
			// A person enter in the bathroom
			bathroom.enter(person);
			// We wait a time in the bathroom
			Thread.sleep(120);
			// The person get out of the bathroom
			bathroom.exit(person);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

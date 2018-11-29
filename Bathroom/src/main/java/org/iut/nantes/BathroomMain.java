package org.iut.nantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dagui
 *
 */
public class BathroomMain {

	public static void main(String[] args) {
		Random randomSexValue = new Random();
		List<Person> people = new ArrayList<Person>();
		List<Thread> threads = new ArrayList<Thread>();
		UnisexBathroom bathroom = new UnisexBathroom();

		System.out.println("Unisex bathrooms");
		for (int i = 0; i < 10; i++) {
			people.add(new Person(randomSexValue.nextBoolean()));
		}

		for (int i = 0; i < people.size(); i++) {
			Run run = new Run(people.get(i), bathroom);
			Thread th = new Thread(run);
			threads.add(th);
			th.start();
		}

		for (int i = 0; i < people.size(); ++i) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Bathroom empty");
	}
}

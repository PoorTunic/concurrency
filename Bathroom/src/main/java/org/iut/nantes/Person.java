package org.iut.nantes;

/**
 * 
 * @author dagui
 *
 */
public class Person {

	// This defines if the person is man or woman
	private boolean sex;

	private String name;

	public Person(boolean sex) {
		this.sex = sex;
	}

	public Person(boolean sex, String name) {
		this.sex = sex;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean getSex() {
		return sex;
	}
}

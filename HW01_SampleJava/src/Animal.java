public class Animal {
	private String name;
	private int age;

	public Animal(String name, int age) {
		this.setName(name);
		this.setAge(age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length() <= 1) {
			throw new IllegalArgumentException("Name cannot be less than 2 characters");
		}

		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 0) {
			throw new IllegalArgumentException("Age cannot be less than 0");
		}

		this.age = age;
	}

	public String speak() {
		return "*animal sound*";
	}

	public boolean equals(Object obj) {
		Animal otherAnimal = obj instanceof Animal ? (Animal) obj : null;

		if (otherAnimal == null) {
			throw new ClassCastException("Given object is not of type Animal");
		}

		return this.getName().equals(otherAnimal.getName())
				&& this.getAge() == otherAnimal.getAge();
	}
}
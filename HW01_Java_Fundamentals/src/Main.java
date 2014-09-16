public class Main {
	private final static int HOW_MUCH_CAN_GEORGE_LIFT_IN_TONNES = 100;

	public static void main(String[] args) {
		for (int i = 1; i <= Main.HOW_MUCH_CAN_GEORGE_LIFT_IN_TONNES; i++) {
			if (i % 2 == 0) {
				System.out.println(i + " is even");
			} else {
				System.out.println(i + " is odd");
			}
		}

		Animal pesho = new Animal("Pesho", 5);
		Animal gosho = pesho;
		Animal stamat = new Animal("Stamat", 10);
		Animal stamatWannabe = new Animal("Stamat", 10);

		if (pesho == gosho) {
			System.out.println("Turns out Pesho is actually Gosho");
		}

		if (stamat.equals(pesho)) {
			// doesn't reach
			System.out.println("Turns out Stamat is actually Pesho");
		}

		if (stamat.equals(stamatWannabe)) {
			System.out.println("Stamat has a twin");
		}
	}
}
package se.prolore.junit.demo;

public class TestTarget {

	public int multiply(int x, int y) {
		if (x > 999) {
			System.err.println("X should be less than 999");
			throw new IllegalArgumentException("X should be less than 999");
		}
		System.out.println("Permorming: " + x + " * " + y);
		return x * y;
	}

}

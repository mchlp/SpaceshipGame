package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Coordinate;

public class CoordinateTest {

	Coordinate coordinate;

	private final static int TEST_RANGE = 10; // test numbers from 10^-(testRange) to 10^(testRange)
	private final static int NUM_PER_TEST_RANGE = 100; // number of random numbers to test per range

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		coordinate = new Coordinate();
		checkVariables(coordinate, 0, 0);
	}

	/**
	 * Tests the constructor with x and y parameters
	 */
	@Test
	public void testConstructor() {
		double newX, newY;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				newX = Math.random() * Math.pow(10, i);
				newY = Math.random() * Math.pow(10, i);

				coordinate = new Coordinate(newX, newY);
				checkVariables(coordinate, newX, newY);
			}
		}
	}

	/**
	 * Tests setter methods
	 */
	@Test
	public void testSetterMethods() {
		coordinate = new Coordinate();

		double newX, newY;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {

			// tests setX and setY methods
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {
				newX = Math.random() * Math.pow(10, i);
				newY = Math.random() * Math.pow(10, i);
				coordinate.setX(newX);
				coordinate.setY(newY);
				checkVariables(coordinate, newX, newY);

			}
		}
	}

	private void checkVariables(Coordinate testCoordinate, double x, double y) {
		assertEquals(coordinate.getX(), x, Math.ulp(x));
		assertEquals(coordinate.getY(), y, Math.ulp(y));
	}
}

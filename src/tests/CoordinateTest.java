package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Coordinate;

public class CoordinateTest {

	Coordinate coordinate = new Coordinate();

	private final int testRange = 10; // test numbers from 10^-(testRange) to 10^(testRange)
	private final int numTestPerRange = 100; // number of random numbers to test per range

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		assertEquals(coordinate.getX(), 0, Math.ulp(0));
		assertEquals(coordinate.getY(), 0, Math.ulp(0));
	}

	/**
	 * Tests the constructor with x and y parameters
	 */
	@Test
	public void testConstructor() {
		double newX, newY;
		for (int i = -testRange; i < testRange; i++) {
			for (int j = 0; j < numTestPerRange; j++) {

				newX = Math.random() * Math.pow(10, i);
				newY = Math.random() * Math.pow(10, i);

				coordinate = new Coordinate(newX, newY);
				assertEquals(coordinate.getX(), newX, Math.ulp(newX));
				assertEquals(coordinate.getY(), newY, Math.ulp(newY));
			}
		}
	}

	/**
	 * Tests setter methods
	 */
	@Test
	public void testSetterMethods() {
		double newX, newY;
		for (int i = -testRange; i < testRange; i++) {

			// tests setX and setY methods
			for (int j = 0; j < numTestPerRange; j++) {
				newX = Math.random() * Math.pow(10, i);
				newY = Math.random() * Math.pow(10, i);
				coordinate.setX(newX);
				coordinate.setY(newY);
				assertEquals(coordinate.getX(), newX, Math.ulp(newX));
				assertEquals(coordinate.getY(), newY, Math.ulp(newY));
			}
		}
	}
}

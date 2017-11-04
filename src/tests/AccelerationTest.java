package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Acceleration;
import game.Vector;

public class AccelerationTest {

	Acceleration acceleration = new Acceleration();

	private final int testRange = 10; // test numbers from 10^-(testRange) to 10^(testRange)
	private final int numTestPerRange = 100; // number of random numbers to test per range
	private final int degreeRange = Vector.MAX_DEGREES * 5; // test direction from 0 to this number of degrees

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		assertEquals(acceleration.getDirection(), 0, Math.ulp(0));
		assertEquals(acceleration.getRate(), 0, Math.ulp(0));
		assertEquals(acceleration.getXRate(), 0, Math.ulp(0));
		assertEquals(acceleration.getYRate(), 0, Math.ulp(0));
	}

	/**
	 * Tests the constructor with x and y parameters
	 */
	@Test
	public void testConstructor() {
		double rate, direction, actualDirection, xSpeed, ySpeed;
		for (int i = -testRange; i < testRange; i++) {
			for (int j = 0; j < numTestPerRange; j++) {

				// creating test data
				rate = Math.random() * Math.pow(10, i);
				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.cos(Math.toRadians(actualDirection)) * rate;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * rate;

				// create new Velocity object and test get methods
				acceleration = new Acceleration(rate, direction);
				assertEquals(acceleration.getRate(), rate, Math.ulp(rate));
				assertEquals(acceleration.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(acceleration.getXRate(), xSpeed, Math.ulp(xSpeed));
				assertEquals(acceleration.getYRate(), ySpeed, Math.ulp(ySpeed));
			}
		}
	}

	/**
	 * Tests setter methods
	 */
	@Test
	public void testSetterMethods() {
		acceleration = new Acceleration();
		double rate, direction, actualDirection, xSpeed, ySpeed;
		for (int i = -testRange; i < testRange; i++) {

			// test setAcceleration method
			for (int j = 0; j < numTestPerRange; j++) {

				rate = Math.random() * Math.pow(10, i);
				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.cos(Math.toRadians(actualDirection)) * rate;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * rate;

				acceleration.setAcceleration(rate, direction);
				assertEquals(acceleration.getRate(), rate, Math.ulp(rate));
				assertEquals(acceleration.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(acceleration.getXRate(), xSpeed, Math.ulp(xSpeed));
				assertEquals(acceleration.getYRate(), ySpeed, Math.ulp(ySpeed));
			}

			// test setRate method
			direction = Math.random() * degreeRange;
			actualDirection = direction % Vector.MAX_DEGREES;
			acceleration = new Acceleration(0, direction);

			for (int j = 0; j < numTestPerRange; j++) {

				rate = Math.random() * Math.pow(10, i);

				xSpeed = Math.cos(Math.toRadians(actualDirection)) * rate;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * rate;

				acceleration.setRate(rate);
				assertEquals(acceleration.getRate(), rate, Math.ulp(rate));
				assertEquals(acceleration.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(acceleration.getXRate(), xSpeed, Math.ulp(xSpeed));
				assertEquals(acceleration.getYRate(), ySpeed, Math.ulp(ySpeed));
			}

			// test setDirection method
			rate = Math.random() * Math.pow(10, i);
			acceleration = new Acceleration(rate, 0);

			for (int j = 0; j < numTestPerRange; j++) {

				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;

				xSpeed = Math.cos(Math.toRadians(actualDirection)) * rate;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * rate;

				acceleration.setDirection(actualDirection);
				assertEquals(acceleration.getRate(), rate, Math.ulp(rate));
				assertEquals(acceleration.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(acceleration.getXRate(), xSpeed, Math.ulp(xSpeed));
				assertEquals(acceleration.getYRate(), ySpeed, Math.ulp(ySpeed));
			}

		}
	}

}

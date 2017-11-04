package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Vector;
import game.Velocity;

public class VelocityTest {

	Velocity velocity = new Velocity();

	private final int testRange = 10; // test numbers from 10^-(testRange) to 10^(testRange)
	private final int numTestPerRange = 100; // number of random numbers to test per range
	private final int degreeRange = Vector.MAX_DEGREES * 5; // test direction from 0 to this number of degrees

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		assertEquals(velocity.getDirection(), 0, Math.ulp(0));
		assertEquals(velocity.getSpeed(), 0, Math.ulp(0));
		assertEquals(velocity.getXMagnitude(), 0, Math.ulp(0));
		assertEquals(velocity.getYMagnitude(), 0, Math.ulp(0));
	}

	/**
	 * Tests the constructor with x and y parameters
	 */
	@Test
	public void testConstructor() {
		double speed, direction, actualDirection, xSpeed, ySpeed;
		for (int i = -testRange; i < testRange; i++) {
			for (int j = 0; j < numTestPerRange; j++) {

				// creating test data
				speed = Math.random() * Math.pow(10, i);
				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.sin(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.cos(Math.toRadians(actualDirection)) * speed;

				if (actualDirection > 180) {
					xSpeed = -xSpeed;
				}

				if (actualDirection < 270 && actualDirection > 90) {
					ySpeed = -ySpeed;
				}

				// create new Velocity object and test get methods
				velocity = new Velocity(speed, direction);
				assertEquals(velocity.getSpeed(), speed, Math.ulp(speed));
				assertEquals(velocity.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(velocity.getXMagnitude(), xSpeed, Math.ulp(xSpeed));
				assertEquals(velocity.getYMagnitude(), ySpeed, Math.ulp(ySpeed));
			}
		}
	}

	/**
	 * Tests setter methods
	 */
	@Test
	public void testSetterMethods() {
		velocity = new Velocity();
		double speed, direction, actualDirection, xSpeed, ySpeed;
		for (int i = -testRange; i < testRange; i++) {

			// test setVelocity method
			for (int j = 0; j < numTestPerRange; j++) {

				speed = Math.random() * Math.pow(10, i);
				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.sin(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.cos(Math.toRadians(actualDirection)) * speed;

				if (actualDirection > 180) {
					xSpeed = -xSpeed;
				}

				if (actualDirection < 270 && actualDirection > 90) {
					ySpeed = -ySpeed;
				}

				velocity.setVector(speed, direction);
				assertEquals(velocity.getSpeed(), speed, Math.ulp(speed));
				assertEquals(velocity.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(velocity.getXMagnitude(), xSpeed, Math.ulp(xSpeed));
				assertEquals(velocity.getYMagnitude(), ySpeed, Math.ulp(ySpeed));
			}

			// test setSpeed method
			direction = Math.random() * degreeRange;
			actualDirection = direction % Vector.MAX_DEGREES;
			velocity = new Velocity(0, direction);

			for (int j = 0; j < numTestPerRange; j++) {

				speed = Math.random() * Math.pow(10, i);

				xSpeed = Math.sin(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.cos(Math.toRadians(actualDirection)) * speed;

				if (actualDirection > 180) {
					xSpeed = -xSpeed;
				}

				if (actualDirection < 270 && actualDirection > 90) {
					ySpeed = -ySpeed;
				}

				velocity.setSpeed(speed);
				assertEquals(velocity.getSpeed(), speed, Math.ulp(speed));
				assertEquals(velocity.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(velocity.getXMagnitude(), xSpeed, Math.ulp(xSpeed));
				assertEquals(velocity.getYMagnitude(), ySpeed, Math.ulp(ySpeed));
			}

			// test setDirection method
			speed = Math.random() * Math.pow(10, i);
			velocity = new Velocity(speed, 0);

			for (int j = 0; j < numTestPerRange; j++) {

				direction = Math.random() * degreeRange;
				actualDirection = direction % Vector.MAX_DEGREES;

				xSpeed = Math.sin(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.cos(Math.toRadians(actualDirection)) * speed;

				if (actualDirection > 180) {
					xSpeed = -xSpeed;
				}

				if (actualDirection < 270 && actualDirection > 90) {
					ySpeed = -ySpeed;
				}

				velocity.setDirection(actualDirection);
				assertEquals(velocity.getSpeed(), speed, Math.ulp(speed));
				assertEquals(velocity.getDirection(), actualDirection, Math.ulp(actualDirection));
				assertEquals(velocity.getXMagnitude(), xSpeed, Math.ulp(xSpeed));
				assertEquals(velocity.getYMagnitude(), ySpeed, Math.ulp(ySpeed));
			}

		}
	}
}

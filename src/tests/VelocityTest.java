package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Vector;
import game.Velocity;

public class VelocityTest {

	Velocity velocity;
	Velocity velocity2;
	Velocity velocity3;

	private final static double TARGET_ACCURACY = 0.00001;
	private final static int TEST_RANGE = 10; // test numbers from 10^-(testRange) to 10^(testRange)
	private final static int NUM_PER_TEST_RANGE = 100; // number of random numbers to test per range
	private final static int DEGREE_RANGE = Vector.MAX_DEGREES * 5; // test direction from 0 to this number of degrees

	/**
	 * Tests using hard-coded values
	 */
	@Test
	public void simpleTest() {
		double speed = 10;
		double direction = 405;
		double actualDirection = 45;
		double xSpeed = Math.sqrt(50);
		double ySpeed = Math.sqrt(50);

		velocity = new Velocity(speed, direction);
		checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);

		velocity = new Velocity(xSpeed, ySpeed, true);
		checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);

		double speed2 = 10;
		double direction2 = 585;
		double actualDirection2 = 225;
		double xSpeed2 = -Math.sqrt(50);
		double ySpeed2 = -Math.sqrt(50);

		double speed3 = 0;
		double xSpeed3 = xSpeed + xSpeed2;
		double ySpeed3 = ySpeed + ySpeed2;
		double actualDirection3 = 180;

		velocity = new Velocity(xSpeed, ySpeed, true);
		velocity2 = new Velocity(xSpeed2, ySpeed2, true);
		velocity3 = velocity.add(velocity2);

		checkVariables(velocity3, speed3, actualDirection3, xSpeed3, ySpeed3);

		velocity = new Velocity(speed, direction);
		velocity2 = new Velocity(speed2, direction2);
		velocity3 = velocity.add(velocity2);

		checkVariables(velocity3, speed3, actualDirection3, xSpeed3, ySpeed3);
	}

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		velocity = new Velocity();
		checkVariables(velocity, 0, 0, 0, 0);
	}

	/**
	 * Tests the constructor with speed and direction parameters
	 */
	@Test
	public void testConstructor() {
		double speed, direction, actualDirection, xSpeed, ySpeed;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				// creating test data
				speed = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.cos(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * speed;

				// create new Velocity object and test get methods
				velocity = new Velocity(speed, direction);
				checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);
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
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {

			// test setVelocity method
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				speed = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				actualDirection = direction % Vector.MAX_DEGREES;
				xSpeed = Math.cos(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * speed;

				velocity.setVelocity(speed, direction);
				checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);
			}

			// test setSpeed method
			direction = Math.random() * DEGREE_RANGE;
			direction = 270;

			actualDirection = direction % Vector.MAX_DEGREES;

			velocity = new Velocity(0, direction);

			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				speed = Math.random() * Math.pow(10, i);

				xSpeed = Math.cos(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * speed;

				velocity.setSpeed(speed);
				checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);
			}

			// test setDirection method
			speed = Math.random() * Math.pow(10, i);
			velocity = new Velocity(speed, 0);

			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				direction = Math.random() * DEGREE_RANGE;
				actualDirection = direction % Vector.MAX_DEGREES;

				xSpeed = Math.cos(Math.toRadians(actualDirection)) * speed;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * speed;

				velocity.setDirection(actualDirection);
				checkVariables(velocity, speed, actualDirection, xSpeed, ySpeed);
			}
		}
	}

	/**
	 * Tests add method
	 */
	@Test
	public void testAddMethod() {

		velocity = new Velocity();
		velocity2 = new Velocity();

		double speed, speed2, direction, direction2, actualDirection, actualDirection2, xSpeed, ySpeed, xSpeed2,
				ySpeed2, speed3, xSpeed3, ySpeed3, actualDirection3;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				// creating test data
				speed = Math.random() * Math.pow(10, i);
				speed2 = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				direction2 = Math.random() * DEGREE_RANGE;

				speed = 100;
				speed2 = 100;
				direction = 0;
				direction2 = 90;

				actualDirection = direction % Vector.MAX_DEGREES;
				actualDirection2 = direction2 % Vector.MAX_DEGREES;
				xSpeed = Math.cos(Math.toRadians(actualDirection)) * speed;
				xSpeed2 = Math.cos(Math.toRadians(actualDirection2)) * speed2;
				ySpeed = Math.sin(Math.toRadians(actualDirection)) * speed;
				ySpeed2 = Math.sin(Math.toRadians(actualDirection2)) * speed2;
				xSpeed3 = xSpeed + xSpeed2;
				ySpeed3 = ySpeed + ySpeed2;
				speed3 = Math.sqrt(Math.pow(xSpeed3, 2) + Math.pow(ySpeed3, 2));

				actualDirection3 = Math.toDegrees(Math.atan(ySpeed3 / xSpeed3));
				if (xSpeed3 <= 0 && ySpeed3 > 0) {
					actualDirection3 += 90;
				} else if (xSpeed3 <= 0 && ySpeed3 <= 0) {
					actualDirection3 += 180;
				} else if (xSpeed3 > 0 && ySpeed3 <= 0) {
					actualDirection3 += 270;
				}

				// create new Velocity objects and test add method
				velocity = new Velocity(speed, direction);
				velocity2 = new Velocity(speed2, direction2);
				velocity3 = velocity.add(velocity2);

				checkVariables(velocity3, speed3, actualDirection3, xSpeed3, ySpeed3);
			}
		}
	}

	private void checkVariables(Velocity checkVelocity, double speed, double actualDirection, double xSpeed,
			double ySpeed) {

		assertEquals(speed, checkVelocity.getSpeed(), TARGET_ACCURACY);
		assertEquals(actualDirection, checkVelocity.getDirection(), TARGET_ACCURACY);
		assertEquals(xSpeed, checkVelocity.getXSpeed(), TARGET_ACCURACY);
		assertEquals(ySpeed, checkVelocity.getYSpeed(), TARGET_ACCURACY);
	}
}

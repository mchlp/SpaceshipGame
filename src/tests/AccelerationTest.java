
/*
 * Michael Pu
 * SpaceshipGame - AccelerationTest
 * ICS3U1 - Mr. Radulovic
 * November 27, 2017
 */


package tests;

import backend.Acceleration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccelerationTest {

	private final static double TARGET_ACCURACY = 0.00001;

	@Test
	public void testBlankConstructor() {
		Acceleration acceleration = new Acceleration();
		assertEquals(0, acceleration.getRate(), TARGET_ACCURACY);
		assertEquals(0, acceleration.getDirection(), TARGET_ACCURACY);
		assertEquals(0, acceleration.getXRate(), TARGET_ACCURACY);
		assertEquals(0, acceleration.getYRate(), TARGET_ACCURACY);
	}

	@Test
	public void testConstructor() {

		Acceleration acceleration = new Acceleration(10, 405);
		assertEquals(10, acceleration.getRate(), TARGET_ACCURACY);
		assertEquals(45, acceleration.getDirection(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), acceleration.getXRate(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), acceleration.getYRate(), TARGET_ACCURACY);

		acceleration = new Acceleration(10, 10, true);
		assertEquals(Math.sqrt(200), acceleration.getRate(), TARGET_ACCURACY);
		assertEquals(45, acceleration.getDirection(), TARGET_ACCURACY);
		assertEquals(10, acceleration.getXRate(), TARGET_ACCURACY);
		assertEquals(10, acceleration.getYRate(), TARGET_ACCURACY);
	}

	@Test
	public void testOppositeDirectionAdd() {
		testAdd(10, 405, 10, 585, 0, 180, 0, 0);
		testAdd(5, 0, 5, 0, 10, 0, 10, 0);
		testAdd(5, 90, 5, 90, 10, 90, 0, 10);
	}

	private void testAdd(double rate1, double direction1, double rate2, double direction2, double expectedRate,
						 double expectedDirection, double expectedX, double expectedY) {
		Acceleration velocity1 = new Acceleration(rate1, direction1);
		Acceleration velocity2 = new Acceleration(rate2, direction2);
		Acceleration velocity3 = velocity1.add(velocity2);
		assertEquals(expectedRate, velocity3.getRate(), TARGET_ACCURACY);
		assertEquals(expectedDirection, velocity3.getDirection(), TARGET_ACCURACY);
		assertEquals(expectedX, velocity3.getXRate(), TARGET_ACCURACY);
		assertEquals(expectedY, velocity3.getYRate(), TARGET_ACCURACY);
	}
}

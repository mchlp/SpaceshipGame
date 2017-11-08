package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.Force;

public class ForceTest {

	private final static double TARGET_ACCURACY = 0.00001;

	@Test
	public void testBlankConstructor() {
		Force force = new Force();
		assertEquals(0, force.getForce(), TARGET_ACCURACY);
		assertEquals(0, force.getDirection(), TARGET_ACCURACY);
		assertEquals(0, force.getXForce(), TARGET_ACCURACY);
		assertEquals(0, force.getYForce(), TARGET_ACCURACY);
	}

	@Test
	public void testConstructor() {

		Force acceleration = new Force(10, 405);
		assertEquals(10, acceleration.getForce(), TARGET_ACCURACY);
		assertEquals(45, acceleration.getDirection(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), acceleration.getXForce(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), acceleration.getYForce(), TARGET_ACCURACY);

		acceleration = new Force(10, 10, true);
		assertEquals(Math.sqrt(200), acceleration.getForce(), TARGET_ACCURACY);
		assertEquals(45, acceleration.getDirection(), TARGET_ACCURACY);
		assertEquals(10, acceleration.getXForce(), TARGET_ACCURACY);
		assertEquals(10, acceleration.getYForce(), TARGET_ACCURACY);
	}

	@Test
	public void testOppositeDirectionAdd() {
		testAdd(10, 405, 10, 585, 0, 180, 0, 0);
	}

	private void testAdd(double rate1, double direction1, double rate2, double direction2, double expectedRate,
			double expectedDirection, double expectedX, double expectedY) {
		Force force1 = new Force(rate1, direction1);
		Force force2 = new Force(rate2, direction2);
		Force force3 = force1.add(force2);
		assertEquals(expectedRate, force3.getForce(), TARGET_ACCURACY);
		assertEquals(expectedDirection, force3.getDirection(), TARGET_ACCURACY);
		assertEquals(expectedY, force3.getXForce(), TARGET_ACCURACY);
		assertEquals(expectedRate, force3.getYForce(), TARGET_ACCURACY);
	}
}

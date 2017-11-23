
/*
 * Michael Pu
 * SpaceshipGame - VelocityTest
 * November 2017
 */


package tests;

import backend.Velocity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VelocityTest {

	private final static double TARGET_ACCURACY = 0.00001;

	@Test
	public void testBlankConstructor() {
		Velocity velocity = new Velocity();
		assertEquals(0, velocity.getSpeed(), TARGET_ACCURACY);
		assertEquals(0, velocity.getDirection(), TARGET_ACCURACY);
		assertEquals(0, velocity.getXSpeed(), TARGET_ACCURACY);
		assertEquals(0, velocity.getYSpeed(), TARGET_ACCURACY);
	}

	@Test
	public void testConstructor() {

		Velocity velocity = new Velocity(10, 405);
		assertEquals(10, velocity.getSpeed(), TARGET_ACCURACY);
		assertEquals(45, velocity.getDirection(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), velocity.getXSpeed(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), velocity.getYSpeed(), TARGET_ACCURACY);

		velocity = new Velocity(10, 10, true);
		assertEquals(Math.sqrt(200), velocity.getSpeed(), TARGET_ACCURACY);
		assertEquals(45, velocity.getDirection(), TARGET_ACCURACY);
		assertEquals(10, velocity.getXSpeed(), TARGET_ACCURACY);
		assertEquals(10, velocity.getYSpeed(), TARGET_ACCURACY);
	}

	@Test
	public void testOppositeDirectionAdd() {
		testAdd(10, 405, 10, 585, 0, 180, 0, 0);
        testAdd(5, 0, 5, 0, 10, 0, 10, 0);
        testAdd(5, 90, 5, 90, 10, 90, 0, 10);
    }

	private void testAdd(double speed1, double direction1, double speed2, double direction2, double expectedSpeed,
						 double expectedDirection, double expectedX, double expectedY) {
		Velocity velocity1 = new Velocity(speed1, direction1);
		Velocity velocity2 = new Velocity(speed2, direction2);
		Velocity velocity3 = velocity1.add(velocity2);
		assertEquals(expectedSpeed, velocity3.getSpeed(), TARGET_ACCURACY);
		assertEquals(expectedDirection, velocity3.getDirection(), TARGET_ACCURACY);
        assertEquals(expectedX, velocity3.getXSpeed(), TARGET_ACCURACY);
        assertEquals(expectedY, velocity3.getYSpeed(), TARGET_ACCURACY);
    }
}

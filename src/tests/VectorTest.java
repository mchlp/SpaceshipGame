
/*
 * Michael Pu
 * SpaceshipGame - VectorTest
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */


package tests;

import backend.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTest {

	private final static double TARGET_ACCURACY = 0.00001;

	@Test
	public void testConstructor() {

		Vector vector = new Vector();
		assertEquals(0, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(0, vector.getYComponent(), TARGET_ACCURACY);
		assertEquals(0, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(0, vector.getDirection(), TARGET_ACCURACY);

		vector = new Vector(10, 405);
		assertEquals(10, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(45, vector.getDirection(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(Math.sqrt(50), vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(10, 10, true);
		assertEquals(Math.sqrt(200), vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(45, vector.getDirection(), TARGET_ACCURACY);
		assertEquals(10, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(10, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(3, 4, true);
		assertEquals(5, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(Math.toDegrees(Math.atan(4.0 / 3)), vector.getDirection(), TARGET_ACCURACY);
		assertEquals(3, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(4, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(-3, -4, true);
		assertEquals(5, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(180 + Math.toDegrees(Math.atan(-4.0 / -3)), vector.getDirection(), TARGET_ACCURACY);
		assertEquals(-3, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(-4, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(-3, 4, true);
		assertEquals(5, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(180 + Math.toDegrees(Math.atan(4.0 / -3)), vector.getDirection(), TARGET_ACCURACY);
		assertEquals(-3, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(4, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(3, -4, true);
		assertEquals(5, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(360 + Math.toDegrees(Math.atan(-4.0 / 3)), vector.getDirection(), TARGET_ACCURACY);
		assertEquals(3, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(-4, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(0, 3, true);
		assertEquals(3, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(90, vector.getDirection(), TARGET_ACCURACY);
		assertEquals(0, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(3, vector.getYComponent(), TARGET_ACCURACY);

		vector = new Vector(0, -3, true);
		assertEquals(3, vector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(270, vector.getDirection(), TARGET_ACCURACY);
		assertEquals(0, vector.getXComponent(), TARGET_ACCURACY);
		assertEquals(-3, vector.getYComponent(), TARGET_ACCURACY);

	}

	@Test
	public void testOppositeDirectionAdd() {
		testAdd(10, 405, 10, 585, 0, 180, 0, 0);
	}

	@Test
	public void testSameDirectionAdd() {
		testAdd(10, 45, 10, 45, 20, 45, Math.sqrt(200), Math.sqrt(200));
	}

	@Test
	public void testDifferentDirectionAdd() {
		testAdd(10, 20, 10, 40, 19.6961550602, 30, 17.057370639, 9.8480775301);
		testAdd(12, 20, 25, 60, 35.05177, 47.287512, 23.776311, 25.754877);

	}

	@Test
	public void testXAxisAdd() {
		testAdd(10, 0, 10, 0, 20, 0, 20, 0);
		testAdd(20, 180, 20, 180, 40, 180, -40, 0);
	}

	@Test
	public void testYAxisAdd() {
		testAdd(10, 90, 10, 90, 20, 90, 0, 20);
		testAdd(30, 270, 30, 270, 60, 270, 0, -60);
	}

	private void testAdd(double magnitude1, double direction1, double mangitude2, double direction2,
			double expectedMagnitude, double expectedDirection, double expectedX, double expectedY) {
		Vector vector1 = new Vector(magnitude1, direction1);
		Vector vector2 = new Vector(mangitude2, direction2);
		Vector vector3 = vector1.add(vector2);
		assertEquals(expectedMagnitude, vector3.getMagnitude(), TARGET_ACCURACY);
		assertEquals(expectedDirection, vector3.getDirection(), TARGET_ACCURACY);
		assertEquals(expectedX, vector3.getXComponent(), TARGET_ACCURACY);
		assertEquals(expectedY, vector3.getYComponent(), TARGET_ACCURACY);
	}
}

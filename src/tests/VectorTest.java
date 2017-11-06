package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Vector;

public class VectorTest {

	Vector vector;
	Vector vector2;
	Vector vector3;

	VectorTestData testData1;
	VectorTestData testData2;
	VectorTestData testData3;

	private final static double TARGET_ACCURACY = 0.00001;
	private final static int TEST_RANGE = 1; // test numbers from 10^-(testRange) to 10^(testRange)
	private final static int NUM_PER_TEST_RANGE = 100; // number of random numbers to test per range
	private final static int DEGREE_RANGE = Vector.MAX_DEGREES * 5; // test direction from 0 to this number of degrees

	/**
	 * Tests using hard-coded values
	 */
	@Test
	public void simpleTest() {

		// test constructor
		testData1 = new VectorTestData(10, 405);
		testVectorConstructor(testData1);

		// test add function
		testData2 = new VectorTestData(10, 585);
		testData3 = new VectorTestData(0, 0);
		testVectorAdd(testData1, testData2, testData3);
	}

	/**
	 * Tests the constructor with no parameters
	 */
	@Test
	public void testBlankConstructor() {
		testData1 = new VectorTestData(0, 0);
		vector = new Vector();
		checkVariables(vector, testData1);
	}

	/**
	 * Tests using random values
	 */
	@Test
	public void randomTest() {
		double magnitude, direction;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {
				magnitude = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				testData1 = new VectorTestData(magnitude, direction);
				testVectorConstructor(testData1);
				testVectorSetters(testData1);
			}
		}
	}

	/**
	 * Tests setter methods
	 */
	@Test
	public void testSetterMethods() {
		vector = new Vector();
		double magnitude, direction;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {
				magnitude = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				testData1 = new VectorTestData(magnitude, direction);

			}
		}
	}

	/**
	 * Tests add method using random values
	 */
	@Test
	public void testAddMethod() {

		vector = new Vector();
		vector2 = new Vector();

		double magnitude, magnitude2, direction, direction2, magnitude3, direction3, newXComponent, newYComponent;
		for (int i = -TEST_RANGE; i < TEST_RANGE; i++) {
			for (int j = 0; j < NUM_PER_TEST_RANGE; j++) {

				// creating test data
				magnitude = Math.random() * Math.pow(10, i);
				magnitude2 = Math.random() * Math.pow(10, i);
				direction = Math.random() * DEGREE_RANGE;
				direction2 = Math.random() * DEGREE_RANGE;

				// generate data for combined vector
				newXComponent = magnitude * Math.cos(Math.toRadians(direction))
						+ magnitude2 * Math.cos(Math.toRadians(direction2));
				newYComponent = magnitude * Math.sin(Math.toRadians(direction))
						+ magnitude2 * Math.sin(Math.toRadians(direction2));
				magnitude3 = Math.sqrt(Math.pow(newXComponent, 2) + Math.pow(newYComponent, 2));
				direction3 = Math.toDegrees(Math.atan(newYComponent / newXComponent));
				if (newXComponent <= 0 && newYComponent > 0) {
					direction3 += 90;
				} else if (newXComponent <= 0 && newYComponent <= 0) {
					direction3 += 180;
				} else if (newXComponent > 0 && newYComponent <= 0) {
					direction3 += 270;
				}

				testData1 = new VectorTestData(magnitude, direction);
				testData2 = new VectorTestData(magnitude2, direction2);
				testData3 = new VectorTestData(magnitude3, direction3);

				testVectorAdd(testData1, testData2, testData3);
			}
		}
	}

	private void testVectorSetters(VectorTestData testData) {
		vector = new Vector();
		// test setVector
		vector.setVector(testData.getmMagnitude(), testData.getmDirection());
		checkVariables(vector, testData);
		// test setDirection
		vector.setDirection(testData.getmDirection());
		checkVariables(vector, testData);
		// test setMagnitude
		vector.setMagnitude(testData.getmMagnitude());
		checkVariables(vector, testData);
	}

	private void testVectorAdd(VectorTestData testData1, VectorTestData testData2, VectorTestData testData3) {

		vector = new Vector(testData1.getmXMagnitude(), testData1.getmYMagnitude(), true);
		vector2 = new Vector(testData2.getmXMagnitude(), testData2.getmYMagnitude(), true);
		vector3 = vector.add(vector2);

		checkVariables(vector3, testData3);

		vector = new Vector(testData1.getmMagnitude(), testData1.getmDirection());
		vector2 = new Vector(testData2.getmMagnitude(), testData2.getmDirection());
		vector3 = vector.add(vector2);

		checkVariables(vector3, testData3);
	}

	private void testVectorConstructor(VectorTestData testData) {

		vector = new Vector(testData.getmMagnitude(), testData.getmDirection());
		checkVariables(vector, testData);

		vector = new Vector(testData.getmXMagnitude(), testData.getmYMagnitude(), true);
		checkVariables(vector, testData);
	}

	private void checkVariables(Vector checkVector, VectorTestData testData) {

		assertEquals(testData.getmMagnitude(), checkVector.getMagnitude(), TARGET_ACCURACY);
		assertEquals(testData.getmActualDirection(), checkVector.getDirection(), TARGET_ACCURACY);
		assertEquals(testData.getmXMagnitude(), checkVector.getXComponent(), TARGET_ACCURACY);
		assertEquals(testData.getmYMagnitude(), checkVector.getYComponent(), TARGET_ACCURACY);
	}
}

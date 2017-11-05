package game;

public final class Utilities {

	public static final double EPSILON = 1E-5;

	/**
	 * Calculates the acceleration of an object from its mass and force applied
	 * 
	 * @param force
	 *            in newtons
	 * @param mass
	 *            in kilograms
	 * @return the acceleration the mass will experience in m/s/s
	 */
	public static double getAcceleration(long force, long mass) {
		return force / mass;
	}

	/**
	 * Calculates the force applied from the acceleration and mass of an object
	 * 
	 * @param acceleration
	 *            in m/s/s
	 * @param mass
	 *            in kilograms
	 * @return the force applied in newtons
	 */
	public static double getForce(double acceleration, long mass) {
		return mass * acceleration;
	}
}

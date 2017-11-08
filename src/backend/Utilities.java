package backend;

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
	public static Acceleration getAcceleration(Force force, long mass) {
		return new Acceleration(force.getForce() / mass, force.getDirection());
	}
}

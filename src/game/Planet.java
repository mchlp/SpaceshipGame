/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import backend.Acceleration;

/**
 * Planet object to calculate physics of objects moving on the surface of the
 * planet
 * 
 * @author Michael Pu
 */
public class Planet {

	private double mMass; // kilograms
	private double mRadius; // metres

	public final static double GRAVITATIONAL_CONTSTANT = 6.67408E-11;

	private final static double EARTH_MASS = 5.972E+24;
	private final static double EARTH_RADIUS = 6371000;

	/**
	 * Creates the Earth
	 */
	public Planet() {
		this(EARTH_MASS, EARTH_RADIUS);
	}

	/**
	 * Creates a planet object
	 * 
	 * @param mass
	 *            Mass of planet in kilograms
	 * @param radius
	 *            Radius of planet in metres
	 */
	public Planet(double mass, double radius) {
		mMass = mass;
		mRadius = radius;
	}

	/**
	 * Calculates the acceleration due to gravity on the planet
	 * 
	 * @return {@link Acceleration} due to gravity on the planet
	 */
	public Acceleration getPlanetaryAcceleration() {
		double gravity = (GRAVITATIONAL_CONTSTANT * mMass) / Math.pow(mRadius, 2);
		return new Acceleration(gravity, 270);
	}
}

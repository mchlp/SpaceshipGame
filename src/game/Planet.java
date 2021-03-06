
/*
 * Michael Pu
 * SpaceshipGame - Planet
 * ICS3U1 - Mr. Radulovic
 * November 27, 2017
 */


package game;

import backend.Acceleration;

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

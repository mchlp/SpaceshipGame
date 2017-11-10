package backend;

public class Planet {

	private double mMass;
	private double mRadius;

	public final static double GRAVITATIONAL_CONTSTANT = 6.67408E-11;

	private final static double EARTH_MASS = 5.972E+24; // kilograms
	private final static double EARTH_RADIUS = 6371000; // metres

	public Planet() {
		this(EARTH_MASS, EARTH_RADIUS);
	}

	public Planet(double mass, double radius) {
		mMass = mass;
		mRadius = radius;
	}

	public Acceleration getPlanetaryAcceleration() {
		double gravity = (GRAVITATIONAL_CONTSTANT * mMass) / Math.pow(mRadius, 2);
		return new Acceleration(gravity, 270);
	}
}

package game;

import javax.swing.text.html.ImageView;

public class Spaceship extends Object {

	private long engineThrust; // newtons
	private int fuelTimeLeft; // seconds
	private double burnRatePerSecond; // kilograms per second
	private double secondsEngineOnFor; // seconds

	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.81; // metres per second per second

	// Initial/Default Values
	private static final long DEFAULT_MASS = 549_100; // kilograms
	private static final long DEFAULT_ENGINE_THRUST = 7_607_000; // newtons
	private static final int DEFAULT_FUEL_TIME_LEFT = 10; // seconds
	private static final int DEFAULT_BURN_RATE_PER_SECOND = 1000; // kilograms per second
	private static final double INITAL_SPEED = 7900; // metres per second
	private static final int INITAL_DIRECTION = 180; // degrees
	private static final Vector INITAL_VELOCITY = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
	private static final Coordinate INITAL_POSITION = new Coordinate(100, 100);

	public Spaceship(ImageView image) {
		super(DEFAULT_MASS, INITAL_VELOCITY, INITAL_POSITION, image);
		engineThrust = DEFAULT_ENGINE_THRUST;
		fuelTimeLeft = DEFAULT_FUEL_TIME_LEFT;
		burnRatePerSecond = DEFAULT_BURN_RATE_PER_SECOND;
	}

	public void engineOn() {
		secondsEngineOnFor++;
	}

	public void engineOff() {

	}

	public Coordinate getPosition() {
		return mPosition;
	}

	public Vector getVelocity() {
		return mVelocity;
	}

	public long getMass() {
		return mMass;
	}
}

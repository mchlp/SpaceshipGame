package game;

import javax.swing.text.html.ImageView;

public class Spaceship extends Object {

	private long engineThrust;

	private static final long DEFAULT_MASS = 549_100; // kilograms
	private static final long DEFAULT_ENGINE_THRUST = 7_607_000; // Newtons
	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.8; // metres per second per second
	private static final double INITAL_SPEED = 7900; // metres per second
	private static final int INITAL_DIRECTION = 180; // degrees
	private static final Velocity INITAL_VELOCITY = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
	private static final Coordinate INITAL_POSITION = new Coordinate(100, 100);

	public Spaceship(ImageView image) {
		super(DEFAULT_MASS, INITAL_VELOCITY, INITAL_POSITION, image);
	}

	public void engineOn() {

	}

	public void engineOff() {

	}

	public Coordinate getPosition() {
		return mPosition;
	}

	public Velocity getVelocity() {
		return mVelocity;
	}

	public long getMass() {
		return mMass;
	}
}

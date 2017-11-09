package backend;

import javafx.scene.image.ImageView;

/**
 * Represents a spaceship sprite and stores various values relating to the
 * spaceship and features methods to control the spaceship.
 * 
 * @author Michael Pu
 */

public class Spaceship extends Sprite {

	private long frameCount = 0;

	private long mEngineThrust; // newtons
	private double mFuelTimeLeft; // seconds
	private double mBurnRatePerSecond; // kilograms per second
	private double mSecondsEngineOnFor; // seconds
	private boolean mEngineOn;

	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.81; // metres per second per second

	// Initial/Default Values
	private static final long DEFAULT_MASS = 549_100; // kilograms
	private static final long DEFAULT_ENGINE_THRUST = 7_607_000; // newtons
	private static final double DEFAULT_FUEL_TIME_LEFT = 10; // seconds
	private static final double DEFAULT_BURN_RATE_PER_SECOND = 1000; // kilograms per second
	private static final double INITAL_SPEED = 0; // metres per second
	private static final int INITAL_DIRECTION = 270; // degrees
	private static final Velocity INITAL_VELOCITY = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
	private static final Coordinate INITAL_POSITION = new Coordinate(500, 10);

	/**
	 * @param image
	 *            {@link ImageView} of spaceship
	 */
	public Spaceship(ImageView image) {
		this(DEFAULT_MASS, image);
	}

	/**
	 * @param mass
	 *            Mass of spaceship in kilograms
	 * @param image
	 *            Image of spaceship
	 */
	public Spaceship(long mass, ImageView image) {
		this(mass, INITAL_VELOCITY, INITAL_POSITION, image);
	}

	/**
	 * @param mass
	 *            Mass of spaceship in kilograms
	 * @param velocity
	 *            Starting velocity
	 * @param position
	 *            Starting position
	 * @param image
	 *            Image of spaceship
	 */
	public Spaceship(long mass, Velocity velocity, Coordinate position, ImageView image) {
		this(mass, velocity, position, image, DEFAULT_ENGINE_THRUST, DEFAULT_FUEL_TIME_LEFT,
				DEFAULT_BURN_RATE_PER_SECOND);
	}

	/**
	 * @param mass
	 *            Mass of spaceship in kilograms
	 * @param velocity
	 *            Starting velocity
	 * @param position
	 *            Starting position
	 * @param image
	 *            Image of spaceship
	 * @param engineThrust
	 *            Thrust produced by engine in newtons
	 * @param fuelTimeLeft
	 *            Amount of burn time in seconds
	 * @param burnRatePerSecond
	 *            Kilograms of fuel burned per second
	 */
	public Spaceship(long mass, Velocity velocity, Coordinate position, ImageView image, long engineThrust,
			double fuelTimeLeft, double burnRatePerSecond) {

		super(mass, velocity, position, image);
		mPosition = INITAL_POSITION;
		mVelocity = INITAL_VELOCITY;
		mMass = DEFAULT_MASS;
		mEngineThrust = engineThrust;
		mFuelTimeLeft = fuelTimeLeft;
		mBurnRatePerSecond = burnRatePerSecond;
		mImageView.setPreserveRatio(true);
		mImageView.setFitHeight(200);
		updatePositionOfImageView(mPosition);
	}

	/**
	 * updates the Spaceship object, to be run every frame
	 * 
	 * @param deltaTime
	 *            Number of seconds that have elapsed since the last update
	 */
	public void update(double deltaTime) {
		Acceleration curAccel = new Acceleration();
		Acceleration gravAccel = new Acceleration(9.8 * deltaTime, 270);
		curAccel = curAccel.add(gravAccel);
		if (mEngineOn) {
			Acceleration engineAccel = new Acceleration(20 * deltaTime, 90);
			curAccel = curAccel.add(engineAccel);
		}
		mVelocity = mVelocity.accelerate(curAccel);
		// mVelocity = new Velocity(1 * deltaTime, 270);
		if (mPosition.getY() > mImageView.getScene().getHeight() - mImageView.getFitHeight()) {
			if (mVelocity.getDirection() > 180) {
				mVelocity = new Velocity();
			}
		}
		mPosition.move(mVelocity, 0.5);
		// System.out.println(frameCount + " " + mVelocity.getSpeed());
		System.out.println(mEngineOn);
		updatePositionOfImageView(mPosition);
		frameCount++;
	}

	/**
	 * Engine turned on
	 */
	public void engineOn() {
		mEngineOn = true;
	}

	/**
	 * Engine turned off
	 */
	public void engineOff() {
		mEngineOn = false;
		mSecondsEngineOnFor = 0;
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

	private void updatePositionOfImageView(Coordinate position) {
		mImageView.setX(position.getX());
		mImageView.setY(position.getY());
	}
}

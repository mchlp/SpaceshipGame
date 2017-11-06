package game;

import javax.swing.text.html.ImageView;

/**
 * Represents a spaceship sprite and stores various values relating to the
 * spaceship and features methods to control the spaceship.
 * 
 * @author Michael Pu
 */

public class Spaceship extends Object {

	private long mEngineThrust; // newtons
	private double mFuelTimeLeft; // seconds
	private double mBurnRatePerSecond; // kilograms per second
	private double mSecondsEngineOnFor; // seconds

	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.81; // metres per second per second

	// Initial/Default Values
	private static final long DEFAULT_MASS = 549_100; // kilograms
	private static final long DEFAULT_ENGINE_THRUST = 7_607_000; // newtons
	private static final double DEFAULT_FUEL_TIME_LEFT = 10; // seconds
	private static final double DEFAULT_BURN_RATE_PER_SECOND = 1000; // kilograms per second
	private static final double INITAL_SPEED = 7900; // metres per second
	private static final int INITAL_DIRECTION = 180; // degrees
	private static final Velocity INITAL_VELOCITY = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
	private static final Coordinate INITAL_POSITION = new Coordinate(100, 100);

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
		mEngineThrust = engineThrust;
		mFuelTimeLeft = fuelTimeLeft;
		mBurnRatePerSecond = burnRatePerSecond;
	}

	/**
	 * updates the Spaceship object, to be run every frame
	 * 
	 * @param FPS
	 *            Frames per second the game is running at
	 */
	public void update(int FPS) {

	}

	/**
	 * Engine turned on
	 */
	public void engineOn() {
		mSecondsEngineOnFor++;
	}

	/**
	 * Engine turned off
	 */
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

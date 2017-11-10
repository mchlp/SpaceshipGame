package backend;

import javafx.scene.image.Image;
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
	private double mSpaceshipHeight;
	private Image mEngineOnImage;
	private Image mEngineOffImage;
	private Planet mPlanet;

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
	 * @param imageView
	 *            {@link ImageView} of spaceship
	 */
	public Spaceship(ImageView imageView, Image engineOffImage, Image engineOnImage, Planet planet) {
		this(DEFAULT_MASS, imageView, engineOffImage, engineOnImage, planet);
	}

	/**
	 * @param mass
	 *            Mass of spaceship in kilograms
	 * @param imageView
	 *            Image of spaceship
	 */
	public Spaceship(long mass, ImageView imageView, Image engineOffImage, Image engineOnImage, Planet planet) {
		this(mass, INITAL_VELOCITY, INITAL_POSITION, imageView, engineOffImage, engineOnImage, planet);
	}

	/**
	 * @param mass
	 *            Mass of spaceship in kilograms
	 * @param velocity
	 *            Starting velocity
	 * @param position
	 *            Starting position
	 * @param imageView
	 *            Image of spaceship
	 */
	public Spaceship(long mass, Velocity velocity, Coordinate position, ImageView imageView, Image engineOffImage,
			Image engineOnImage, Planet planet) {
		this(mass, velocity, position, imageView, DEFAULT_ENGINE_THRUST, DEFAULT_FUEL_TIME_LEFT,
				DEFAULT_BURN_RATE_PER_SECOND, engineOffImage, engineOnImage, planet);
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
			double fuelTimeLeft, double burnRatePerSecond, Image engineOffImage, Image engineOnImage, Planet planet) {

		super(mass, velocity, position, image);
		mPosition = INITAL_POSITION;
		mVelocity = INITAL_VELOCITY;
		mMass = DEFAULT_MASS;
		mEngineThrust = engineThrust;
		mFuelTimeLeft = fuelTimeLeft;
		mBurnRatePerSecond = burnRatePerSecond;
		mEngineOnImage = engineOnImage;
		mEngineOffImage = engineOffImage;
		mPlanet = planet;
		engineOff();
		mSpaceshipHeight = mImageView.getBoundsInParent().getHeight();
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
		Acceleration gravAccel = mPlanet.getPlanetaryAcceleration();
		gravAccel.setRate(gravAccel.getRate() * deltaTime);
		curAccel = curAccel.add(gravAccel);
		if (mEngineOn) {
			Acceleration engineAccel = new Acceleration(17 * deltaTime, 90);
			curAccel = curAccel.add(engineAccel);
		}
		mVelocity = mVelocity.accelerate(curAccel);
		// mVelocity = new Velocity(1 * deltaTime, 270);
		if (mPosition.getY() > mImageView.getScene().getHeight() - mSpaceshipHeight) {
			if (mVelocity.getDirection() > 180) {
				mVelocity = new Velocity();
			}
		}
		mPosition.move(mVelocity, 0.5);
		updatePositionOfImageView(mPosition);
		frameCount++;
	}

	/**
	 * Engine turned on
	 */
	public void engineOn() {
		mImageView.setImage(mEngineOnImage);
		// mImageView.setFitWidth(150);
		// mImageView.setPreserveRatio(true);
		mEngineOn = true;
	}

	/**
	 * Engine turned off
	 */
	public void engineOff() {
		mImageView.setImage(mEngineOffImage);
		// mImageView.setFitWidth(150);
		// mImageView.setPreserveRatio(true);
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

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

	private Acceleration mEngineAccel = new Acceleration(17, 90);
	private double mFuelTimeLeft; // seconds
	private boolean mEngineOn;
	private double mSpaceshipHeight;
	private SpaceshipImageSet mImageSet;
	private Planet mPlanet;
	private Acceleration mCurEngineAccel;
	private SpaceshipEngineDirection mEngineDirection = SpaceshipEngineDirection.MIDDLE;
	private AudioControl mAudioControl = new AudioControl();

	private boolean mFuelAlarmPlayed = false;

	private static final double FUEL_CRITICAL = 1;
	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final Acceleration ZERO_ACCELERATION = new Acceleration();

	private static final double MIDDLE_ENGINE_ANGLE = 90;
	private static final double LEFT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE - 20;
	private static final double RIGHT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE + 20;

	// Initial/Default Values
	private static final double DEFAULT_FUEL_TIME_LEFT = 5; // seconds
	private static final Velocity INITAL_VELOCITY = new Velocity(0, 0);
	private static final Coordinate INITAL_POSITION = new Coordinate(200, 10);

	public Spaceship(ImageView imageView, SpaceshipImageSet imageSet, Planet planet) {
		this(INITAL_VELOCITY, INITAL_POSITION, imageView, DEFAULT_FUEL_TIME_LEFT, imageSet, planet);
	}

	public Spaceship(Velocity velocity, Coordinate position, ImageView image, double fuelTimeLeft,
			SpaceshipImageSet imageSet, Planet planet) {

		super(velocity, position, image);
		mPosition = INITAL_POSITION;
		mVelocity = INITAL_VELOCITY;
		mFuelTimeLeft = fuelTimeLeft;
		mImageSet = imageSet;
		mPlanet = planet;
		mImageView.setPreserveRatio(true);
		mImageView.setFitWidth(50);
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
		if (mFuelTimeLeft <= FUEL_CRITICAL && !mFuelAlarmPlayed) {
			mAudioControl.playAlarm();
			mFuelAlarmPlayed = true;
		}
		if (mEngineOn) {
			mFuelTimeLeft -= deltaTime;
			curAccel = curAccel.add(mCurEngineAccel.getAccelerationByTime(deltaTime));
			if (mFuelTimeLeft < 0) {
				engineOff();
			}
		}
		mVelocity = mVelocity.accelerate(curAccel);
		if (mPosition.getY() > mImageView.getScene().getHeight() - mSpaceshipHeight) {
			if (mVelocity.getDirection() > 180) {
				mVelocity = new Velocity();
				mPosition.setY(mImageView.getScene().getHeight() - mSpaceshipHeight);
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
		if (mFuelTimeLeft > 0) {
			mCurEngineAccel = mEngineAccel;
			mAudioControl.playEngines();
			switch (mEngineDirection) {
			case LEFT:
				mImageView.setImage(mImageSet.getmImageRocketLeftOn());
				mCurEngineAccel.setDirection(LEFT_ENGINE_ANGLE);
				break;
			case RIGHT:
				mImageView.setImage(mImageSet.getmImageRocketRightOn());
				mCurEngineAccel.setDirection(RIGHT_ENGINE_ANGLE);
				break;
			default:
				mImageView.setImage(mImageSet.getmImageRocketMiddleOn());
				mCurEngineAccel.setDirection(MIDDLE_ENGINE_ANGLE);
				break;
			}
			mEngineOn = true;
		}
	}

	/**
	 * Engine turned off
	 */
	public void engineOff() {
		mAudioControl.stopEngines();
		switch (mEngineDirection) {
		case LEFT:
			mImageView.setImage(mImageSet.getmImageRocketLeftOff());
			break;
		case RIGHT:
			mImageView.setImage(mImageSet.getmImageRocketRightOff());
			break;
		default:
			mImageView.setImage(mImageSet.getmImageRocketMiddleOff());
			break;
		}
		mCurEngineAccel = ZERO_ACCELERATION;
		// mImageView.setFitWidth(150);
		// mImageView.setPreserveRatio(true);
		mEngineOn = false;
	}

	public void setEngineDirection(SpaceshipEngineDirection state) {
		mEngineDirection = state;
		if (mEngineOn) {
			engineOn();
		} else {
			engineOff();
		}
	}

	public Coordinate getPosition() {
		return mPosition;
	}

	public Velocity getVelocity() {
		return mVelocity;
	}

	private void updatePositionOfImageView(Coordinate position) {
		mImageView.setX(position.getX());
		mImageView.setY(position.getY());
	}
}

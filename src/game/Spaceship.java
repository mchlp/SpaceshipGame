/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import backend.Acceleration;
import backend.Coordinate;
import backend.SpaceshipImageSet;
import backend.Sprite;
import backend.Utilities;
import backend.Velocity;
import javafx.scene.image.ImageView;

/**
 * Represents a spaceship sprite and stores various values relating to the
 * spaceship
 * 
 * @author Michael Pu
 */

public class Spaceship extends Sprite {

	// possible states of spaceship
	public static enum SpaceshipState {
		FLYING, TOUCHED, LANDED, CLOSE, CRASHED
	}

	// possible directions the engine can bevel in
	public static enum SpaceshipEngineDirection {
		LEFT, RIGHT, MIDDLE;
	}

	// keep track of the number of times the frame has been updated
	private long frameCount = 0;

	// acceleration engine produces in m/s/s
	private Acceleration mEngineAccel;
	// amount of fuel burn time left in seconds
	private double mFuelTimeLeft;
	// if the engine is on
	private boolean mEngineOn;
	// height of the spaceship in pixels
	private double mSpaceshipHeight;
	// images of spaceship with different engine positions and flames
	private SpaceshipImageSet mImageSet;
	// planet the spaceship is currently on
	private Planet mPlanet;
	// the direction the engine of the saceship is facing
	private SpaceshipEngineDirection mEngineDirection;
	// the current state of the spaceship
	private SpaceshipState mState;
	// amount of time spaceship must remain in the landing pad to be landed in
	// seconds
	private double mLandingTimeout;
	// the y position of the ground in pixels
	private double mGroundLevel;
	// if the spaceship is currently at a safe speed (under MAX_IMPACT_SPEED)
	private boolean mAtSafeSpeed;
	// the dimensions of the window in pixels
	private Coordinate mScreenDimesions;
	// the imageview of the spaceship
	private ImageView mImageView;

	// if the fuel low alarm has been played
	private boolean mFuelAlarmPlayed = false;
	// if the explosion has been played
	private boolean mExplosionAnimated = false;

	// mFuelTimeLeft value when the low fuel alarm sounds in seconds
	private static final double FUEL_CRITICAL = 1.5;
	// distance from ground when ground proximity alarm sounds in pixels
	private static final double GROUND_PROXIMITY = 300;
	// maximum impact speed for safe landing in m/s/s
	private static final int MAX_IMPACT_SPEED = 5;

	// angles the engine bevels at
	private static final double MIDDLE_ENGINE_ANGLE = 90;
	private static final double LEFT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE - 20;
	private static final double RIGHT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE + 20;

	// Initial/Default Values
	private static final Acceleration DEFAULT_ENGINE_ACCELERATION = new Acceleration(17, 90);
	private static final int DEFAULT_LANDING_TIMEOUT = 2;
	private static final double DEFAULT_FUEL_TIME_LEFT = 4; // seconds
	private static final Velocity INITAL_VELOCITY = new Velocity(0, 0);
	private static final Coordinate INITAL_POSITION = new Coordinate(200, 10);

	/**
	 * Creates a spaceship object using simple parameters
	 * 
	 * @param imageView
	 *            {@link ImageView} of spaceship
	 * @param imageSet
	 *            {@link ImageSet} of spaceship
	 * @param planet
	 *            {@link Planet} is spaceship is on
	 */
	public Spaceship(ImageView imageView, SpaceshipImageSet imageSet, Planet planet) {
		this(INITAL_VELOCITY.copy(), INITAL_POSITION.copy(), imageView, DEFAULT_FUEL_TIME_LEFT, imageSet, planet,
				DEFAULT_ENGINE_ACCELERATION);
	}

	/**
	 * Creates a spaceship object using detailed parameters
	 * 
	 * @param velocity
	 *            Initial {@link Velocity} of spaceship
	 * @param position
	 *            Initial {@link Coordinate position} of spaceship
	 * @param image
	 *            {@link ImageView} of spaceship
	 * @param fuelTimeLeft'starting
	 *            Amount of fuel left in seconds
	 * @param imageSet
	 *            {@link ImageSet} of spaceship
	 * @param planet
	 *            {@link Planet} spaceship is on
	 * @param engineAcceleration
	 *            {@link Acceleration} the engine produces each second
	 */
	public Spaceship(Velocity velocity, Coordinate position, ImageView image, double fuelTimeLeft,
			SpaceshipImageSet imageSet, Planet planet, Acceleration engineAcceleration) {

		super(velocity, position, image);

		// set member variables using provided values and default values
		mImageView = (ImageView) mNode;
		mImageView.setPreserveRatio(true);
		mImageView.setFitWidth(50);
		mState = SpaceshipState.FLYING;
		mEngineDirection = SpaceshipEngineDirection.MIDDLE;
		mFuelTimeLeft = fuelTimeLeft;
		mEngineAccel = engineAcceleration;
		mLandingTimeout = DEFAULT_LANDING_TIMEOUT;
		mImageSet = imageSet;
		mPlanet = planet;
		engineOff();
		mSpaceshipHeight = mImageView.getBoundsInParent().getHeight();
		updatePositionOfImageView();
	}

	/**
	 * Updates the Spaceship object, to be run every frame
	 * 
	 * @param deltaTime
	 *            Number of seconds that have elapsed since the last update
	 */
	@Override
	public void update(double deltaTime) {

		// update spaceship if it has not crashed yet
		if (mState != SpaceshipState.CRASHED) {

			// get ground level (bottom of screen) and screen dimesions
			mGroundLevel = mImageView.getScene().getHeight();
			mScreenDimesions = new Coordinate(mImageView.getScene().getWidth(), mImageView.getScene().getHeight());

			// reset current acceleration for this grame
			Acceleration curAccel = new Acceleration();
			// get acceleratiion due to gravity and add to current acceleration
			Acceleration gravAccel = mPlanet.getPlanetaryAcceleration().getAccelerationByTime(deltaTime);
			curAccel = curAccel.add(gravAccel);

			// if spaceship is touching the landing pad
			if (mState == SpaceshipState.TOUCHED) {
				mLandingTimeout -= deltaTime;
				if (mLandingTimeout <= 0) {
					mState = SpaceshipState.LANDED;
					engineOff();
				}
			} else {
				mLandingTimeout = DEFAULT_LANDING_TIMEOUT;
			}

			// play fuel alarm if conidtions met
			if (mFuelTimeLeft <= FUEL_CRITICAL && !mFuelAlarmPlayed) {
				AudioControl.playFuelAlarm();
				mFuelAlarmPlayed = true;
			}

			// if engine is on, add engine acceleration to current acceleration and decrease
			// fuel burn time
			if (mEngineOn) {
				mFuelTimeLeft -= Math.min(deltaTime, mFuelTimeLeft); // so that burn time left does not go under 0
				curAccel = curAccel.add(mEngineAccel.getAccelerationByTime(deltaTime));
				if (mFuelTimeLeft <= 0) {
					engineOff();
				}
			}

			// accelerate the current velocity by the current acceleration
			mVelocity = mVelocity.accelerate(curAccel);
			// move the spaceship by the current velocity
			mPosition.move(mVelocity);

			// check if spaceship is at safe speed, if not play alarm
			if (Math.abs(mVelocity.getSpeed()) <= MAX_IMPACT_SPEED) {
				mAtSafeSpeed = true;
				AudioControl.fast.stop();
			} else {
				if (!AudioControl.fast.isPlaying()) {
					AudioControl.playFast();
				}
				mAtSafeSpeed = false;
			}

			// if spaceship is below or at ground level
			if (mPosition.getY() >= mGroundLevel - mSpaceshipHeight) {
				// set velocity of spaceship to zero
				mVelocity = new Velocity();
				// if spaceship is below ground, move it to ground level
				mPosition.setY(mGroundLevel - mSpaceshipHeight);
				if (mState == SpaceshipState.FLYING) {
					explode();
				} else {
					// if spaceship is over the landing pad
					if (!mAtSafeSpeed) {
						explode();
					}
				}
			}

			// check if spaceship is within the left and right bounds of screen
			if (mPosition.getX() < 0 || mPosition.getX() + mImageView.getFitWidth() > mScreenDimesions.getX()) {
				explode();
			}

			// check if spaceship is lower than ground proximity alarm altitude
			if (mState == SpaceshipState.FLYING && (mGroundLevel - mPosition.getY()) < GROUND_PROXIMITY) {
				if (!AudioControl.terrainAlarm.isPlaying()) {
					AudioControl.playTerrainAlarm();
				}
			}

			// update the position of the spaceship imageView
			updatePositionOfImageView();
			frameCount++;
		}

	}

	// when spaceship explodes
	private void explode() {
		mState = SpaceshipState.CRASHED;
		mImageView.setImage(null);
		if (!mExplosionAnimated) {
			new Explosion(Utilities.getCentreofImageView(mImageView));
			mExplosionAnimated = true;
		}
	}

	/**
	 * Turn engine on
	 */
	public void engineOn() {
		if (mFuelTimeLeft > 0 && mState != SpaceshipState.LANDED && mState != SpaceshipState.CRASHED) {
			AudioControl.playEngines();
			switch (mEngineDirection) {
			case LEFT:
				mImageView.setImage(mImageSet.getmImageRocketLeftOn());
				mEngineAccel.setDirection(LEFT_ENGINE_ANGLE);
				break;
			case RIGHT:
				mImageView.setImage(mImageSet.getmImageRocketRightOn());
				mEngineAccel.setDirection(RIGHT_ENGINE_ANGLE);
				break;
			default:
				mImageView.setImage(mImageSet.getmImageRocketMiddleOn());
				mEngineAccel.setDirection(MIDDLE_ENGINE_ANGLE);
				break;
			}
			mEngineOn = true;
		}
	}

	/**
	 * Turn engine off
	 */
	public void engineOff() {
		AudioControl.stopEngines();
		if (mState != SpaceshipState.CRASHED) {
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
		}
		mEngineOn = false;
	}

	/**
	 * Sets state of spaceship
	 * 
	 * @param state
	 *            {@link SpaceshipState State} to set to
	 */
	public void setState(SpaceshipState state) {
		mState = state;
	}

	/**
	 * Sets direction the engine is facing
	 * 
	 * @param direction
	 *            {@link SpaceshipEngineDirection Direction} the engine is facing
	 */
	public void setEngineDirection(SpaceshipEngineDirection direction) {
		mEngineDirection = direction;
		if (mEngineOn) {
			engineOn();
		} else {
			engineOff();
		}
	}

	/**
	 * @return {@link Coordinate Position} of spaceship
	 */
	public Coordinate getPosition() {
		return mPosition;
	}

	/**
	 * @return {@link Velocity} of spaceship
	 */
	public Velocity getVelocity() {
		return mVelocity;
	}

	// updates the imageView location using the coordinates stored in the mPosition
	// variable
	private void updatePositionOfImageView() {
		mImageView.setX(mPosition.getX());
		mImageView.setY(mPosition.getY());
	}

	/**
	 * @return If the spaceship is at a safe speed
	 */
	public boolean getmAtSafeSpeed() {
		return mAtSafeSpeed;
	}

	/**
	 * @return The {@link SpaceshipState state} of the spaceship
	 */
	public SpaceshipState getmState() {
		return mState;
	}

	/**
	 * @return The height of the spaceship
	 */
	public double getmSpaceshipHeight() {
		return mSpaceshipHeight;
	}

	/**
	 * @return The {@link ImageView} of the spaceship
	 */
	public ImageView getmImageView() {
		return mImageView;
	}

	/**
	 * @return the fuel burn time left
	 */
	public double getmFuelTimeLeft() {
		return mFuelTimeLeft;
	}

	/**
	 * @return the maximum current speed of the spaceship
	 */
	public double getSpeed() {
		return mVelocity.getSpeed();
	}

	/**
	 * @return if the fuel left is at a critical amount
	 */
	public boolean getFuelCritical() {
		return mFuelTimeLeft <= FUEL_CRITICAL;
	}
}

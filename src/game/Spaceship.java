package game;

import backend.Acceleration;
import backend.Coordinate;
import backend.SpaceshipEngineDirection;
import backend.SpaceshipImageSet;
import backend.Velocity;
import javafx.scene.image.ImageView;

/**
 * Represents a spaceship sprite and stores various values relating to the
 * spaceship and features methods to control the spaceship.
 * 
 * @author Michael Pu
 */

public class Spaceship extends Sprite {

	public enum SpaceshipState {
		FLYING, TOUCHED, LANDED, CLOSE, CRASHED
	}

	private long frameCount = 0;

	private Acceleration mEngineAccel = new Acceleration(17, 90);
	private double mFuelTimeLeft; // seconds
	private boolean mEngineOn;
	private double mSpaceshipHeight;
	private SpaceshipImageSet mImageSet;
	private Planet mPlanet;
	private Acceleration mCurEngineAccel;
	private SpaceshipEngineDirection mEngineDirection = SpaceshipEngineDirection.MIDDLE;
	private SpaceshipState mState;
	private double mLandingTimeout = DEFAULT_LANDING_TIMEOUT;
	private double mGroundLevel;
	private boolean mAtSafeSpeed;
	private double mPixelToMetreRatio;

	private boolean mFuelAlarmPlayed = false;
	private boolean mExplosionAnimated = false;

	private static final double FUEL_CRITICAL = 1.5;
	private static final double GROUND_PROXIMITY = 300;
	private static final int MAX_IMPACT_SPEED = 4; // metres per second
	private static final Acceleration ZERO_ACCELERATION = new Acceleration();

	private static final double MIDDLE_ENGINE_ANGLE = 90;
	private static final double LEFT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE - 20;
	private static final double RIGHT_ENGINE_ANGLE = MIDDLE_ENGINE_ANGLE + 20;

	// Initial/Default Values
	private static final int DEFAULT_LANDING_TIMEOUT = 2;
	private static final double DEFAULT_FUEL_TIME_LEFT = 7; // seconds
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
		mState = SpaceshipState.FLYING;
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
	@Override
	public void update(double deltaTime) {

		if (mState != SpaceshipState.CRASHED) {

			System.out.println(mVelocity.getSpeed());

			mGroundLevel = mImageView.getScene().getHeight();
			mPixelToMetreRatio = mGroundLevel / 2112;
			mPixelToMetreRatio = 1;

			Acceleration curAccel = new Acceleration();
			Acceleration gravAccel = mPlanet.getPlanetaryAcceleration();
			gravAccel.setRate(gravAccel.getRate() * deltaTime);
			curAccel = curAccel.add(gravAccel);

			if (mState == SpaceshipState.TOUCHED) {
				mLandingTimeout -= deltaTime;
				if (mLandingTimeout <= 0) {
					mState = SpaceshipState.LANDED;
					engineOff();
				}
			} else {
				mLandingTimeout = DEFAULT_LANDING_TIMEOUT;
			}

			if (mFuelTimeLeft <= FUEL_CRITICAL && !mFuelAlarmPlayed) {
				AudioControl.playFuelAlarm();
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
			if (mPosition.getY() > mGroundLevel - mSpaceshipHeight) {
				if (mVelocity.getDirection() > 180) {
					mVelocity = new Velocity();
					mPosition.setY(mGroundLevel - mSpaceshipHeight);
				}
			}

			mPosition.move(mVelocity, 1.0);

			if (Math.abs(mVelocity.getSpeed()) <= MAX_IMPACT_SPEED) {
				mAtSafeSpeed = true;
				AudioControl.fast.stop();
			} else {
				if (!AudioControl.fast.isPlaying()) {
					AudioControl.playFast();
				}
				mAtSafeSpeed = false;
			}

			if (mPosition.getY() >= mGroundLevel - mSpaceshipHeight) {
				if (!mAtSafeSpeed) {
					explode();
				}
			}

			if (mState == SpaceshipState.FLYING && (mGroundLevel - mPosition.getY()) < GROUND_PROXIMITY) {
				if (!AudioControl.terrainAlarm.isPlaying()) {
					AudioControl.playTerrainAlarm();
				}
			}

			updatePositionOfImageView(mPosition);
			frameCount++;
		}
	}

	private void explode() {
		mState = SpaceshipState.CRASHED;
		mImageView.setImage(null);
		if (!mExplosionAnimated) {
			new Explosion(getCentreofImage());
			mExplosionAnimated = true;
		}
	}

	/**
	 * Engine turned on
	 */
	public void engineOn() {
		if (mFuelTimeLeft > 0 && mState != SpaceshipState.LANDED && mState != SpaceshipState.CRASHED) {
			mCurEngineAccel = mEngineAccel;
			AudioControl.playEngines();
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
		mCurEngineAccel = ZERO_ACCELERATION;
		mEngineOn = false;
	}

	public void setState(SpaceshipState state) {
		mState = state;
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

	public boolean getmAtSafeSpeed() {
		return mAtSafeSpeed;
	}

	public SpaceshipState getmState() {
		return mState;
	}

	public double getmSpaceshipHeight() {
		return mSpaceshipHeight;
	}

	public ImageView getmImageView() {
		return mImageView;
	}
}

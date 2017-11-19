
/*
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */

package game;

import backend.Coordinate;
import backend.Sprite;
import backend.Velocity;
import game.Spaceship.SpaceshipState;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LandingPad extends Sprite {

	// possible states
	private enum State {
		NOT_LANDED, CLOSE, TOUCHED, LANDED
	}

	// dimensions of landing pad
	private static final int PAD_WIDTH = 200;
	private static final int PAD_HEIGHT = 15;

	// colours of landing pad
	private static final Color RED = Color.RED;
	private static final Color YELLOW = Color.YELLOW;
	private static final Color GREEN_YELLOW = Color.GREENYELLOW;
	private static final Color GREEN = Color.GREEN;

	// current state of landing pad
	private State mState;
	// rectange of landing pad to be displayed on screen
	private Rectangle mRectangle;
	// spaceship that is supposed to land on the pad
	private Spaceship mSpaceship;

	// if the landed sound is playing
	private boolean playingLanded = false;

	/**
	 * Creates a landing pad for the spaceship to land on
	 * 
	 * @param spaceship
	 *            {@link Spaceship} that is supposed to land on the pad
	 * @param rectangle
	 *            {@link Rectangle} representing the landing pad on screen
	 * @param maxX
	 *            Maximum possible X position of landing pad
	 * @param yPos
	 *            Y position of landing pad (ground level)
	 */
	public LandingPad(Spaceship spaceship, Rectangle rectangle, double maxX, double yPos) {
		super(new Velocity(), new Coordinate(), null);
		mSpaceship = spaceship;
		mNode = rectangle;
		mRectangle = (Rectangle) mNode;
		mRectangle.setVisible(true);
		mRectangle.setX((int) (Math.random() * (maxX - PAD_WIDTH)));
		mRectangle.setY(yPos - PAD_HEIGHT);
		mRectangle.setWidth(PAD_WIDTH);
		mRectangle.setHeight(PAD_HEIGHT);
		mState = State.NOT_LANDED;
	}

	@Override
	public void update(double deltaTime) {

		// update the y position of the landing pad if window was resized
		mRectangle.setY(mRectangle.getScene().getHeight() - PAD_HEIGHT);
		mPosition.setX(mRectangle.getX());
		mPosition.setY(mRectangle.getY());

		// bounds of the spaceship
		Bounds spaceshipBounds = mSpaceship.getmImageView().getBoundsInParent();

		// if spaceship crashed, the landing pad state is not landed
		if (mSpaceship.getmState() == SpaceshipState.CRASHED) {
			mState = State.NOT_LANDED;
		} else {
			if (checkInside(spaceshipBounds)) {
				// the spaceship is in the landing pad
				AudioControl.terrainAlarm.stop();
				// check if spaceship has fully landed on pad
				if (mSpaceship.getmState() == SpaceshipState.LANDED) {
					mState = State.LANDED;
				} else {
					// the spaceship has landed, but is not fully landed (timeout not over)
					mState = State.TOUCHED;
					mSpaceship.setState(SpaceshipState.TOUCHED);
					if (!playingLanded) {
						AudioControl.playTouched();
						playingLanded = true;
					}
				}
			} else {
				// the spaceship is not in the landing pad
				AudioControl.touched.stop();
				playingLanded = false;
				if (mSpaceship.getmState() != SpaceshipState.CRASHED) {
					// if the spaceship has not crashed, set the state to default (FLYING)
					mSpaceship.setState(SpaceshipState.FLYING);
				}
				if (checkAbove(spaceshipBounds)) {
					// if the spaceship is above the landing pad
					AudioControl.terrainAlarm.stop();
					mState = State.CLOSE;
					mSpaceship.setState(SpaceshipState.CLOSE);
				} else {
					// if the spaceship is not touching or above the landing pad
					mState = State.NOT_LANDED;
				}
			}
		}
		// update the colour of the landing pad
		updateColor();

	}

	// checks if the bounds are directly above the landing pad
	private boolean checkAbove(Bounds checkBounds) {
		return (checkBounds.getMaxX() < mRectangle.getBoundsInParent().getMaxX()
				&& checkBounds.getMinX() > mRectangle.getBoundsInParent().getMinX());
	}

	// checks if the bounds are directly above the landing pad and touching the
	// landing pad
	private boolean checkInside(Bounds checkBounds) {
		return (checkAbove(checkBounds)
				&& checkBounds.getMaxY() - (checkBounds.getHeight() - mSpaceship.getmSpaceshipHeight()) > mRectangle
						.getBoundsInParent().getMinY());
	}

	// updates the colour of the landing pad according to the state
	private void updateColor() {
		switch (mState) {
		case CLOSE:
			mRectangle.setFill(YELLOW);
			break;
		case LANDED:
			mRectangle.setFill(GREEN);
			break;
		case TOUCHED:
			mRectangle.setFill(GREEN_YELLOW);
			break;
		default:
			mRectangle.setFill(RED);
			break;
		}

	}

	/**
	 * @return {@link Rectangle} representing the landing pad
	 */
	public Rectangle getmRectangle() {
		return mRectangle;
	}
}

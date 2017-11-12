package backend;

import backend.Spaceship.SpaceshipState;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LandingPad {

	private enum State {
		NOT_LANDED, CLOSE, TOUCHED, LANDED
	};

	private static final int PAD_WIDTH = 200;
	private static final int PAD_HEIGHT = 15;

	private static final Color RED = Color.RED;
	private static final Color YELLOW = Color.YELLOW;
	private static final Color GREEN_YELLOW = Color.GREENYELLOW;
	private static final Color GREEN = Color.GREEN;

	private State mState;
	private Rectangle mRectangle;

	private boolean playedLanded = false;
	private boolean playingClose = false;

	public LandingPad(Rectangle rectangle, double maxX, double maxY) {
		mRectangle = rectangle;
		mState = State.NOT_LANDED;
		mRectangle.setX((int) (Math.random() * (maxX - PAD_WIDTH)));
		mRectangle.setY(maxY - PAD_HEIGHT);
		mRectangle.setWidth(PAD_WIDTH);
		mRectangle.setHeight(PAD_HEIGHT);
		updateColor();
	}

	public void update(double deltaTime, Spaceship spaceship) {

		// update the y position of the landing pad if window was resized
		mRectangle.setY(mRectangle.getScene().getHeight() - PAD_HEIGHT);

		Bounds checkBounds = spaceship.mImageView.getBoundsInParent();
		if (checkInside(checkBounds)) {
			AudioControl.stopClose();
			playingClose = false;
			if (spaceship.getState() == SpaceshipState.LANDED) {
				mState = State.LANDED;
			} else {
				mState = State.TOUCHED;
				spaceship.setState(SpaceshipState.TOUCHED);
				if (!playedLanded) {
					AudioControl.playTouched();
					playedLanded = true;
				}
			}
		} else {
			playedLanded = false;
			if (spaceship.getState() != SpaceshipState.CRASHED) {
				spaceship.setState(SpaceshipState.FLYING);
			}
			if (checkAbove(checkBounds)) {
				mState = State.CLOSE;
				spaceship.setState(SpaceshipState.CLOSE);
				if (!playingClose) {
					AudioControl.playClose();
					playingClose = true;
				}
			} else {
				AudioControl.stopClose();
				playingClose = false;
				mState = State.NOT_LANDED;
			}
		}
		updateColor();
	}

	public boolean checkAbove(Bounds checkBounds) {
		return (checkBounds.getMaxX() < mRectangle.getBoundsInParent().getMaxX()
				&& checkBounds.getMinX() > mRectangle.getBoundsInParent().getMinX());
	}

	public boolean checkInside(Bounds checkBounds) {
		return (checkBounds.getMaxX() < mRectangle.getBoundsInParent().getMaxX()
				&& checkBounds.getMinX() > mRectangle.getBoundsInParent().getMinX()
				&& checkBounds.getMaxY() > mRectangle.getBoundsInParent().getMinY());
	}

	public void updateColor() {
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

}

package backend;

import game.AudioControl;
import game.Spaceship;
import game.Spaceship.SpaceshipState;
import game.Sprite;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LandingPad extends Sprite {

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
	private Spaceship mSpaceship;

	private boolean playedLanded = false;
	private boolean playingClose = false;

	public LandingPad(Spaceship spaceship, Rectangle rectangle, double maxX, double maxY) {
		super(new Velocity(), new Coordinate(), null);
		mSpaceship = spaceship;
		mRectangle = rectangle;
		mState = State.NOT_LANDED;
		mRectangle.setX((int) (Math.random() * (maxX - PAD_WIDTH)));
		mRectangle.setY(maxY - PAD_HEIGHT);
		mRectangle.setWidth(PAD_WIDTH);
		mRectangle.setHeight(PAD_HEIGHT);
		updateColor();
	}

	@Override
	public void update(double deltaTime) {

		// update the y position of the landing pad if window was resized
		mRectangle.setY(mRectangle.getScene().getHeight() - PAD_HEIGHT);
		mPosition.setX(mRectangle.getX());
		mPosition.setY(mRectangle.getY());

		Bounds checkBounds = mSpaceship.getmImageView().getBoundsInParent();

		if (mSpaceship.getmState() == SpaceshipState.CRASHED) {
			mState = State.NOT_LANDED;
		} else {

			if (checkInside(checkBounds)) {

				if (mSpaceship.getmState() == SpaceshipState.LANDED) {
					mState = State.LANDED;
				} else {
					mState = State.TOUCHED;
					mSpaceship.setState(SpaceshipState.TOUCHED);
					if (!playedLanded) {
						AudioControl.playTouched();
						playedLanded = true;
					}
				}

			} else {
				playedLanded = false;
				if (mSpaceship.getmState() != SpaceshipState.CRASHED) {
					mSpaceship.setState(SpaceshipState.FLYING);
				}
				if (checkAbove(checkBounds)) {
					mState = State.CLOSE;
					mSpaceship.setState(SpaceshipState.CLOSE);
				} else {
					mState = State.NOT_LANDED;
				}
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
				&& checkBounds.getMaxY() - (checkBounds.getHeight() - mSpaceship.getmSpaceshipHeight()) > mRectangle
						.getBoundsInParent().getMinY());
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

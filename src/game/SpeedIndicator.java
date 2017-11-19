
/*
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */

package game;

import backend.Coordinate;
import backend.TextLabel;
import backend.Utilities;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SpeedIndicator extends TextLabel {

	private static final Color SAFE_SPEED_FONT_COLOUR = Color.BLACK;
	private static final Color UNSAFE_SPEED_FONT_COLOUR = Color.FIREBRICK;

    @SuppressWarnings("CanBeFinal")
    private Spaceship mSpaceship;

	// top left position of speed label
	private static final Coordinate SPEED_TOP_LEFT = new Coordinate(10, 60);

	protected SpeedIndicator(Text text, Spaceship spaceship) {
		super(text, SPEED_TOP_LEFT);
		mSpaceship = spaceship;
	}

	@Override
	public void update(double deltaTime) {
		if (mSpaceship.getSpeed() > 0) {
			mText.setText("Speed: " + Utilities.round(mSpaceship.getSpeed(), 2));
		}
		if (mSpaceship.getmAtSafeSpeed()) {
			mText.setFill(SAFE_SPEED_FONT_COLOUR);
		} else {
			mText.setFill(UNSAFE_SPEED_FONT_COLOUR);
		}
	}
}


/*
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */

package game;

import backend.Coordinate;
import backend.TextLabel;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverIndicator extends TextLabel {

	private static final int FONT_SIZE = 100;
	private static final Color LOSE_FONT_COLOUR = Color.RED;
	private static final String LOSE_TEXT = "Game Over!";
	private static final Color WIN_FONT_COLOUR = Color.GREEN;
	private static final String WIN_TEXT = "You Win!";
	private static final Color FONT_OUTLINE = Color.BLACK;

	private Spaceship mSpaceship;

	protected GameOverIndicator(Text text, Spaceship spaceship) {
		super(text, new Coordinate());
		mText.setVisible(false);
		mSpaceship = spaceship;
	}

	@Override
	public void update(double deltaTime) {

		switch (mSpaceship.getmState()) {
		case CRASHED:
			// if the spaceship crashed, you lose
			mText.setText(LOSE_TEXT);
			mText.setFont(new Font(FONT_SIZE));
			mText.setFill(LOSE_FONT_COLOUR);
			break;
		case LANDED:
			// if the spaceship landed, you win
			mText.setText(WIN_TEXT);
			mText.setFont(new Font(FONT_SIZE));
			mText.setFill(WIN_FONT_COLOUR);

			break;
		}
		mText.setStroke(FONT_OUTLINE);
		mText.setStrokeWidth(2);
		mText.setVisible(true);

		double centreX = mText.getScene().getWidth() / 2 - mText.getBoundsInParent().getWidth() / 2;
		double centerY = mText.getScene().getHeight() / 2 - mText.getBoundsInParent().getHeight() / 2;
		mPosition = new Coordinate(centreX, centerY);
		updatePosition();

	}

}

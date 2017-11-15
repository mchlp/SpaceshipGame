package game;

import backend.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverIndicator extends TextLabel {

	private static int FONT_SIZE = 100;
	private static Color LOSE_FONT_COLOUR = Color.RED;
	private static Color WIN_FONT_COLOUR = Color.GREEN;
	private static Color FONT_OUTLINE = Color.BLACK;

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
			mText.setText("Game Over");
			mText.setFont(new Font(FONT_SIZE));
			mText.setFill(LOSE_FONT_COLOUR);
			break;
		case LANDED:
			mText.setText("You Win");
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

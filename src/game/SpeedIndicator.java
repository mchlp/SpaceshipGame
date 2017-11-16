package game;

import backend.Coordinate;
import backend.TextLabel;
import backend.Utilities;
import javafx.scene.text.Text;

public class SpeedIndicator extends TextLabel {

	private Spaceship mSpaceship;

	private static final Coordinate SPEED_TOP_LEFT = new Coordinate(10, 60);

	protected SpeedIndicator(Text text, Spaceship spaceship) {
		super(text, SPEED_TOP_LEFT);
		mSpaceship = spaceship;
	}

	@Override
	public void update(double deltaTime) {
		mText.setText("Speed: " + Utilities.round(mSpaceship.getSpeed(), 2));
	}

}

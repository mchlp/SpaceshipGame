package game;

import backend.Coordinate;
import backend.Utilities;
import javafx.scene.text.Text;

public class FuelIndicator extends TextLabel {

	private Spaceship mSpaceship;

	private static final Coordinate FUEL_TOP_LEFT = new Coordinate(20, 20);

	protected FuelIndicator(Text text, Spaceship spaceship) {
		super(text, FUEL_TOP_LEFT);
		mSpaceship = spaceship;
	}

	@Override
	public void update(double deltaTime) {
		mText.setText(Utilities.round(mSpaceship.getmFuelTimeLeft(), 2));
	}

}

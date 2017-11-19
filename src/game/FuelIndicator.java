/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import backend.Coordinate;
import backend.TextLabel;
import backend.Utilities;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Displays the fuel burn time remaining as text on the screen
 * 
 * @author Michael Pu
 */
public class FuelIndicator extends TextLabel {

	private static final Color SAFE_FUEL_FONT_COLOUR = Color.BLACK;
	private static final Color LOW_FUEL_FONT_COLOUR = Color.FIREBRICK;

	private Spaceship mSpaceship;

	// top left position of the label
	private static final Coordinate FUEL_TOP_LEFT = new Coordinate(10, 10);

	protected FuelIndicator(Text text, Spaceship spaceship) {
		super(text, FUEL_TOP_LEFT);
		mSpaceship = spaceship;
	}

	@Override
	public void update(double deltaTime) {
		mText.setText("Fuel Left: " + Utilities.round(mSpaceship.getmFuelTimeLeft(), 2));
		if (mSpaceship.getFuelCritical()) {
			mText.setFill(LOW_FUEL_FONT_COLOUR);
		} else {
			mText.setFill(SAFE_FUEL_FONT_COLOUR);
		}
	}

}
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
import javafx.scene.text.Text;

public class TimeIndicator extends TextLabel {

	private double mTimePassed = 0;

	private static final Coordinate TIME_TOP_LEFT = new Coordinate(20, 100);

	public TimeIndicator(Text text) {
		super(text, TIME_TOP_LEFT);
	}

	@Override
	public void update(double deltaTime) {
		mTimePassed += deltaTime;
		mText.setText(Utilities.round(mTimePassed, 2));
	}

}

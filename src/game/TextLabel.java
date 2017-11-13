package game;

import backend.Coordinate;
import backend.Velocity;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class TextLabel extends Sprite {

	protected Text mText;
	protected Coordinate TOP_LEFT = new Coordinate(20, 20);

	protected static final int FONT_SIZE = 25;

	protected TextLabel(Text text, Coordinate position) {
		super(new Velocity(), position, new ImageView());
		mText = text;
		mText.setX(TOP_LEFT.getX());
		mText.setY(TOP_LEFT.getY() + FONT_SIZE);
		mText.setFont(new Font(FONT_SIZE));
	}

	@Override
	public abstract void update(double deltaTime);

}

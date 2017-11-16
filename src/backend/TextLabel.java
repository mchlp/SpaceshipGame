package backend;

import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class TextLabel extends Sprite {

	protected Text mText;

	protected static final int FONT_SIZE = 25;

	protected TextLabel(Text text, Coordinate position) {
		super(new Velocity(), position, new ImageView());
		mText = text;
		mText.setX(mPosition.getX());
		mText.setY(mPosition.getY() + FONT_SIZE);
		mText.setFont(new Font(FONT_SIZE));
	}

	@Override
	public abstract void update(double deltaTime);

	public void updatePosition() {
		mText.setX(mPosition.getX());
		mText.setY(mPosition.getY());
	}
}

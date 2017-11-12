package game;

import backend.Coordinate;
import backend.Velocity;
import javafx.scene.image.ImageView;

public abstract class Sprite {

	protected Velocity mVelocity;
	protected Coordinate mPosition;
	protected ImageView mImageView;

	protected Sprite(Velocity velocity, Coordinate position, ImageView image) {
		mVelocity = velocity;
		mPosition = position;
		mImageView = image;
	}

	public abstract void update(double deltaTime);
}

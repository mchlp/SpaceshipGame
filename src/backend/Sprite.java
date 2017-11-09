package backend;

import javafx.scene.image.ImageView;

public abstract class Sprite {

	protected long mMass;
	protected Velocity mVelocity;
	protected Coordinate mPosition;
	protected ImageView mImageView;

	protected Sprite(long mass, Velocity velocity, Coordinate position, ImageView image) {
		mMass = mass;
		mVelocity = velocity;
		mPosition = position;
		mImageView = image;
	}
}

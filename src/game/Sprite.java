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

	public ImageView getmImageView() {
		return mImageView;
	}

	public Coordinate getCentreofImage() {
		double centreX = mImageView.getX() + mImageView.getFitWidth() / 2;
		double centreY = mImageView.getY() + mImageView.getFitHeight() / 2;
		System.out.println(centreX + " " + centreY);
		return new Coordinate(centreX, centreY);
	}
}

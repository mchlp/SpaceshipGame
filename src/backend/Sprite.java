/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package backend;

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

	protected Sprite() {
		mVelocity = new Velocity();
		mPosition = new Coordinate();
		mImageView = new ImageView();
	}

	public abstract void update(double deltaTime);

	public ImageView getmImageView() {
		return mImageView;
	}

	public Coordinate getCentreofImage() {
		double centreX = mImageView.getX() + mImageView.getFitWidth() / 2;
		double centreY = mImageView.getY() + mImageView.getFitHeight() / 2;
		return new Coordinate(centreX, centreY);
	}
}

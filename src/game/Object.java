package game;

import javax.swing.text.html.ImageView;

public abstract class Object {

	protected long mMass;
	protected Vector mVelocity;
	protected Coordinate mPosition;
	protected ImageView mImage;

	public Object(long mass, Vector velocity, Coordinate position, ImageView image) {
		mMass = mass;
		mVelocity = velocity;
		mPosition = position;
		mImage = image;
	}
}

package backend;

import javax.swing.text.html.ImageView;

public abstract class Object {

	protected long mMass;
	protected Velocity mVelocity;
	protected Coordinate mPosition;
	protected ImageView mImage;

	protected Object(long mass, Velocity velocity, Coordinate position, ImageView image) {
		mMass = mass;
		mVelocity = velocity;
		mPosition = position;
		mImage = image;
	}
}

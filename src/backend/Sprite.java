
/*
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */

package backend;

import javafx.scene.Node;

public abstract class Sprite {

	protected Velocity mVelocity;
	protected Coordinate mPosition;
	protected Node mNode;

	protected Sprite(Velocity velocity, Coordinate position, Node node) {
		mVelocity = velocity;
		mPosition = position;
		mNode = node;
	}

	public abstract void update(double deltaTime);

	public Node getmNode() {
		return mNode;
	}
}

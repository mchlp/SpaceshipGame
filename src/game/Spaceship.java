package game;

import javafx.scene.image.ImageView;

public class Spaceship {

	private Velocity velocity;
	private Coordinate position;
	private long mass;
	private ImageView image;

	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.8; // metres per second per second
	private static final double INITAL_SPEED = 7900; // metres per second
	private static final int INITAL_DIRECTION = 180; // degrees
	private static final int FORCE_OF_THRUST = 1000; // Newtons

	public Spaceship() {
		velocity = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
		position = new Coordinate();
	}

}

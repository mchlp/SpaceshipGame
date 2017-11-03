package game;

import javafx.scene.image.ImageView;

public class Spaceship {

	private Velocity velocity;
	private Coordinate position;
	private long mass;
	private long engineThrust;
	private ImageView image;

	private static final long DEFAULT_MASS = 549_100; // kilograms
	private static final long DEFAULT_ENGINE_THRUST = 7_607_000; // Newtons
	private static final int MAX_IMPACT_SPEED = 60; // metres per second
	private static final double GRAVITY_ACCEL = 9.8; // metres per second per second
	private static final double INITAL_SPEED = 7900; // metres per second
	private static final int INITAL_DIRECTION = 180; // degrees
	private static final Coordinate INITAL_POSITION = new Coordinate(100, 100);

	public Spaceship() {
		velocity = new Velocity(INITAL_SPEED, INITAL_DIRECTION);
		position = INITAL_POSITION;
	}
}

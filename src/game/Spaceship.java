package game;

public class Spaceship {

	private Velocity velocity;
	private Coordinate position;
	private long mass;

	private static final int MAX_IMPACT_SPEED = 60;
	private static final double GRAVITY_ACCEL = 9.8;

	private static final double INITAL_SPEED = 15000;
	private static final int INITAL_DIRECTION = 180;

	public Spaceship() {
		velocity = new Velocity(INITAL_SPEED, INITAL_DIRECTION);

	}

}

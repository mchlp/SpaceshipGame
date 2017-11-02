package game;

public class Velocity {
	private double speed;
	private int direction;

	public Velocity(double speed, int direction) {
		this.speed = speed;
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDirection() {
		return direction;
	}
}

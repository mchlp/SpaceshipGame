package game;

public class Velocity {
	private double speed;
	private double direction;

	public Velocity(double speed, double direction) {
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

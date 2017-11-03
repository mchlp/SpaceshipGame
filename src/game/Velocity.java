package game;

public class Velocity {

	private double speed; // speed (m/s)
	private double direction; // direction (0-359 degrees)
	private Coordinate xyRatio; // ratio of speed in X direction : speed in Y direction
	private Coordinate xyCoefficient; // direction (positive or negative) of displacement in X and Y direction

	public static final int MAX_DEGREES = 360;

	public Velocity() {
		this(0, 0);
	}

	public Velocity(double speed, double direction) {
		setVelocity(speed, direction);
	}

	/**
	 * updates the xToyRatio and xyCoefficient variables using the value stored in
	 * the direction variable
	 */
	private void updateXY() {

		// set ratio of speed in X direction to speed in Y direction
		xyRatio.setX(Math.sin(Math.toRadians(direction)));
		xyRatio.setY(Math.cos(Math.toRadians(direction)));

		// set direction of speed in X direction
		if (direction == 0 || direction == 180) {
			xyCoefficient.setX(0);
		} else if (direction < 180) {
			xyCoefficient.setX(1);
		} else {
			xyCoefficient.setX(-1);
		}

		// set direction of speed in Y direction
		if (direction == 90 || direction == 270) {
			xyCoefficient.setY(0);
		} else if (direction > 270 || direction < 90) {
			xyCoefficient.setY(1);
		} else {
			xyCoefficient.setY(-1);
		}
	}

	public void setVelocity(double speed, double direction) {
		this.speed = speed;
		this.direction = direction % MAX_DEGREES;
		xyCoefficient = new Coordinate();
		xyRatio = new Coordinate();
		updateXY();
	}

	public void setSpeed(double speed) {
		setVelocity(speed, direction);
	}

	public void setDirection(double direction) {
		setVelocity(speed, direction);
	}

	public double getSpeed() {
		return speed;
	}

	public double getDirection() {
		return direction;
	}

	/**
	 * @return speed in the X direction
	 */
	public double getXSpeed() {
		return xyRatio.getX() * xyCoefficient.getX() * speed;
	}

	/**
	 * @return speed in the Y direction
	 */
	public double getYSpeed() {
		return xyRatio.getY() * xyCoefficient.getY() * speed;
	}
}

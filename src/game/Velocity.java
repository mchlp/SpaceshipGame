package game;

public class Velocity extends Vector {

	public Velocity() {
		super();
	}

	/**
	 * @param speed
	 *            in m/s
	 * @param direction
	 *            in degrees (0-365 inclusive)
	 */
	public Velocity(double speed, double direction) {
		super(speed, direction);
	}

	public void setVelocity(double speed, double direction) {
		super.setVector(speed, direction);
	}

	public void setSpeed(double speed) {
		super.setMagnitude(speed);
	}

	public void setDirection(double direction) {
		super.setDirection(direction);
	}

	public double getSpeed() {
		return super.getMagnitude();
	}

	public double getDirection() {
		return super.getDirection();
	}

	/**
	 * @return speed in the X direction
	 */
	public double getXSpeed() {
		return super.getXMagnitude();
	}

	/**
	 * @return speed in the Y direction
	 */
	public double getYSpeed() {
		return super.getYMagnitude();
	}
}

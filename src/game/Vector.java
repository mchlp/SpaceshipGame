package game;

public abstract class Vector {

	protected double magnitude;
	protected double direction; // 0-359 degrees
	protected Coordinate xyRatio; // ratio of magnitude in X direction : magnitude in Y direction
	protected Coordinate xyCoefficient; // direction (positive or negative) of magnitude in X and Y direction

	public static final int MAX_DEGREES = 360;

	public Vector() {
		this(0, 0);
	}

	public Vector(double magnitude, double direction) {
		setVector(magnitude, direction);
	}

	/**
	 * updates the xToyRatio and xyCoefficient variables using the value stored in
	 * the direction variable
	 */
	protected void updateXY() {

		// set ratio of magnitude in X direction to magnitude in Y direction
		xyRatio.setX(Math.sin(Math.toRadians(direction)));
		xyRatio.setY(Math.cos(Math.toRadians(direction)));

		// set direction of magnitude in X direction
		if (direction == 0 || direction == 180) {
			xyCoefficient.setX(0);
		} else if (direction < 180) {
			xyCoefficient.setX(1);
		} else {
			xyCoefficient.setX(-1);
		}

		// set direction of magnitude in Y direction
		if (direction == 90 || direction == 270) {
			xyCoefficient.setY(0);
		} else if (direction > 270 || direction < 90) {
			xyCoefficient.setY(1);
		} else {
			xyCoefficient.setY(-1);
		}
	}

	public void setVector(double magnitude, double direction) {
		this.magnitude = magnitude;
		this.direction = direction % MAX_DEGREES;
		xyCoefficient = new Coordinate();
		xyRatio = new Coordinate();
		updateXY();
	}

	public void setMagnitude(double magnitude) {
		setVector(magnitude, direction);
	}

	public void setDirection(double direction) {
		setVector(magnitude, direction);
	}

	public double getMagnitude() {
		return magnitude;
	}

	public double getDirection() {
		return direction;
	}

	/**
	 * @return magnitude in the X direction
	 */
	public double getXMagnitude() {
		return xyRatio.getX() * xyCoefficient.getX() * magnitude;
	}

	/**
	 * @return magnitude in the Y direction
	 */
	public double getYMagnitude() {
		return xyRatio.getY() * xyCoefficient.getY() * magnitude;
	}

}

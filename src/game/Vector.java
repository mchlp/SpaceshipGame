package game;

public abstract class Vector {

	protected double magnitude;
	protected double direction; // 0-359 degrees

	public static final int MAX_DEGREES = 360;

	protected Vector() {
		this(0, 0);
	}

	/**
	 * Creates a Vector object using magnitude and direction
	 * 
	 * @param magnitude
	 *            Magnitude of vector
	 * @param direction
	 *            Direction of vector
	 */
	protected Vector(double magnitude, double direction) {
		setVector(magnitude, direction);
	}

	/**
	 * Creates a Vector object using X and Y components
	 * 
	 * @param xComponent
	 *            X Component of vector
	 * @param yComponent
	 *            Y Component of vector
	 * @param xyComponent
	 *            To differentiate between magnitude and direction constructor
	 */
	protected Vector(double xComponent, double yComponent, boolean xyComponent) {
		double[] magnitudeAndDirection = componentToMagnitudeAndDirection(xComponent, yComponent);
		setVector(magnitudeAndDirection[0], magnitudeAndDirection[1]);
	}

	private double[] componentToMagnitudeAndDirection(double xComponent, double yComponent) {
		double[] result = new double[2];
		result[0] = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
		result[1] = Math.toDegrees(Math.atan(yComponent / xComponent));
		if (xComponent <= 0 && yComponent > 0) {
			result[1] += 90;
		} else if (xComponent <= 0 && yComponent <= 0) {
			result[1] += 180;
		} else if (xComponent > 0 && yComponent <= 0) {
			result[1] += 270;
		}
		return result;

	}

	protected void setVector(double magnitude, double direction) {
		setMagnitude(magnitude);
		setDirection(direction);
	}

	protected void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	protected void setDirection(double direction) {
		this.direction = direction % MAX_DEGREES;
	}

	protected double getMagnitude() {
		return magnitude;
	}

	protected double getDirection() {
		return direction;
	}

	/**
	 * @return magnitude in the X direction
	 */
	protected double getXComponent() {
		return magnitude * Math.cos(Math.toRadians(direction));
	}

	/**
	 * @return magnitude in the Y direction
	 */
	protected double getYComponent() {
		return magnitude * Math.sin(Math.toRadians(direction));
	}

}

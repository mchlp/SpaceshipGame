package game;

public abstract class Vector {

	protected double magnitude;
	protected double direction; // 0-359 degrees
	protected Coordinate xyRatio; // ratio of magnitude in X direction : magnitude in Y direction
	protected Coordinate xyCoefficient; // direction (positive or negative) of magnitude in X and Y direction

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
		return result;

	}

	protected void setVector(double magnitude, double direction) {
		this.magnitude = magnitude;
		this.direction = direction % MAX_DEGREES;
		xyCoefficient = new Coordinate();
		xyRatio = new Coordinate();
	}

	protected void setMagnitude(double magnitude) {
		setVector(magnitude, direction);
	}

	protected void setDirection(double direction) {
		setVector(magnitude, direction);
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

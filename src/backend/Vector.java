package backend;

public class Vector {

	protected double magnitude;
	protected double direction; // 0-359 degrees

	public static final int MAX_DEGREES = 360;

	public Vector() {
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
	public Vector(double magnitude, double direction) {
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
	public Vector(double xComponent, double yComponent, boolean xyComponent) {
		setXY(xComponent, yComponent);
	}

	private double[] componentToMagnitudeAndDirection(double xComponent, double yComponent) {
		double[] result = new double[2];
		result[0] = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
		result[1] = Math.toDegrees(Math.atan(yComponent / xComponent));

		if (xComponent <= 0 && yComponent > 0) {
			result[1] += 180;
		} else if (xComponent <= 0 && yComponent <= 0) {
			result[1] += 180;
		} else if (xComponent > 0 && yComponent <= 0) {
			result[1] += 360;
		}

		return result;

	}

	public void setXY(double xComponent, double yComponent) {
		double[] magnitudeAndDirection = componentToMagnitudeAndDirection(xComponent, yComponent);
		setVector(magnitudeAndDirection[0], magnitudeAndDirection[1]);
	}

	public void setVector(double magnitude, double direction) {
		setMagnitude(magnitude);
		setDirection(direction);
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	public void setDirection(double direction) {
		this.direction = direction % MAX_DEGREES;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public double getDirection() {
		return direction;
	}

	/**
	 * Adds two Vector objects together and returns the sum
	 * 
	 * @param vector2
	 *            Vector object to add
	 * @return Vector object representing the sum
	 */
	public Vector add(Vector vector2) {
		double newXComponent = this.getXComponent() + vector2.getXComponent();
		double newYComponent = this.getYComponent() + vector2.getYComponent();
		return new Vector(newXComponent, newYComponent, true);
	}

	/**
	 * @return magnitude in the X direction
	 */
	public double getXComponent() {
		return magnitude * Math.cos(Math.toRadians(direction));
	}

	/**
	 * @return magnitude in the Y direction
	 */
	public double getYComponent() {
		return magnitude * Math.sin(Math.toRadians(direction));
	}

}

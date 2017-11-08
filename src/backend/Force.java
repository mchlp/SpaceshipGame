package backend;

/**
 * Represents force and stores a force and a direction
 * 
 * @author Michael Pu
 */

public class Force {

	private Vector mVector = new Vector();

	public Force() {
		mVector.setVector(0, 0);
	}

	/**
	 * Creates a Force object using a vector object
	 * 
	 * @param vector
	 *            a Vector object representing the force and direction
	 */
	public Force(Vector vector) {
		mVector = vector;
	}

	/**
	 * Creates an Force object using X and Y forces
	 * 
	 * @param xForce
	 *            X Force in newtons
	 * @param yForce
	 *            Y Force in newtons
	 * @param xyForce
	 *            To differentiate between force and direction constructor
	 */
	public Force(double xForce, double yForce, boolean xySpeed) {
		mVector.setXY(xForce, yForce);
	}

	/**
	 * Creates an Force object from force and direction
	 * 
	 * @param force
	 *            Force in newtons
	 * @param direction
	 *            Direction of force in degrees (0-365 inclusive)
	 */
	public Force(double force, double direction) {
		setForce(force, direction);
	}

	public void setForce(double force, double direction) {
		setForce(force);
		setDirection(direction);
	}

	public void setForce(double force) {
		mVector.setMagnitude(force);
	}

	public void setDirection(double direction) {
		mVector.setDirection(direction);
	}

	public double getForce() {
		return mVector.getMagnitude();
	}

	public double getDirection() {
		return mVector.getDirection();
	}

	/**
	 * Adds two Force objects together and returns the sum
	 * 
	 * @param force2
	 *            Force object to add
	 * @return Force object representing the sum
	 */
	public Force add(Force force2) {
		return new Force(mVector.add(force2.mVector));
	}

	/**
	 * @return Force in the X direction
	 */
	public double getXForce() {
		return mVector.getXComponent();
	}

	/**
	 * @return Force in the Y direction
	 */
	public double getYForce() {
		return mVector.getYComponent();
	}
}

package backend;

/**
 * Represents acceleration and stores a rate and a direction
 * 
 * @author Michael Pu
 *
 */

public class Acceleration extends Vector {

	public Acceleration() {
		super();
	}

	/**
	 * Creates a Acceleration object using X and Y Rate
	 * 
	 * @param xRate
	 *            X Rate of acceleration in m/s/s
	 * @param yRate
	 *            Y Rate of acceleration in m/s/s
	 * @param xySpeed
	 *            To differentiate between rate and direction constructor
	 */
	public Acceleration(double xRate, double yRate, boolean xyRate) {
		super(xRate, yRate, xyRate);
	}

	/**
	 * Creates a Velocity object from speed and direction
	 * 
	 * @param speed
	 *            Rate of acceleration in m/s/s
	 * @param direction
	 *            Direction of acceleration in degrees (0-365 inclusive)
	 */
	public Acceleration(double rate, double direction) {
		super(rate, direction);
	}

	public void setAcceleration(double rate, double direction) {
		super.setVector(rate, direction);
	}

	public void setRate(double rate) {
		super.setMagnitude(rate);
	}

	public void setDirection(double direction) {
		super.setDirection(direction);
	}

	public double getRate() {
		return super.getMagnitude();
	}

	public double getDirection() {
		return super.getDirection();
	}

	/**
	 * Adds two Acceleration objects together and returns the sum
	 * 
	 * @param acceleration2
	 *            Acceleration object to add
	 * @return Acceleration object representing the sum
	 */
	public Acceleration add(Acceleration acceleration2) {
		return new Acceleration(this.getXComponent() + acceleration2.getXComponent(),
				this.getYComponent() + acceleration2.getYComponent());
	}

	/**
	 * @return Rate in the X direction
	 */
	public double getXRate() {
		return super.getXComponent();
	}

	/**
	 * @return Rate in the Y direction
	 */
	public double getYRate() {
		return super.getYComponent();
	}
}

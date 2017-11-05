package game;

/**
 * Represents velocity and stores a speed and a direction
 * 
 * @author Michael Pu
 */

public class Velocity extends Vector {

	public Velocity() {
		super();
	}

	/**
	 * Creates a Vector object using X and Y speeds
	 * 
	 * @param xSpeed
	 *            X Speed of vector
	 * @param ySpeed
	 *            Y Speed of vector
	 * @param xySpeed
	 *            To differentiate between magnitude and direction constructor
	 */
	public Velocity(double xSpeed, double ySpeed, boolean xySpeed) {
		super(xSpeed, ySpeed, xySpeed);
	}

	/**
	 * Creates a Velocity object from speed and direction
	 * 
	 * @param speed
	 *            Speed of velocity in m/s
	 * @param direction
	 *            Direction of velocity in degrees (0-365 inclusive)
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
	 * Adds two Velocity objects together and returns the sum
	 * 
	 * @param velocity2
	 *            Velocity object to add
	 * @return Velocity object representing the sum
	 */
	public Velocity add(Velocity velocity2) {
		return new Velocity(this.getXComponent() + velocity2.getXComponent(),
				this.getYComponent() + velocity2.getYComponent(), true);
	}

	/**
	 * @return Speed in the X direction
	 */
	public double getXSpeed() {
		return super.getXComponent();
	}

	/**
	 * @return Speed in the Y direction
	 */
	public double getYSpeed() {
		return super.getYComponent();
	}
}

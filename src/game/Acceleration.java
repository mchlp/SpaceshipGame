package game;

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
	 * 
	 * @param rate
	 *            Rate in m/s/s
	 * @param direction
	 *            Direction in degrees (0-365, inclusive)
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
	 * @return Rate in the X direction
	 */
	public double getXRate() {
		return super.getXMagnitude();
	}

	/**
	 * @return Rate in the Y direction
	 */
	public double getYRate() {
		return super.getYMagnitude();
	}
}

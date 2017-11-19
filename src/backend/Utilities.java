
/*
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 */

package backend;

import javafx.scene.image.ImageView;

import java.io.InputStream;

public final class Utilities {

	public static final double EPSILON = 1E-5;
	public static final String IMAGE_DIRECTORY = "/images/";
	public static final String AUDIO_DIRECTORY = "/audio/";

	/**
	 * Calculates the acceleration of an object from its mass and force applied
	 * 
	 * @param force
	 *            in newtons
	 * @param mass
	 *            in kilograms
	 * @return the acceleration the mass will experience in m/s/s
	 */
	public static Acceleration getAcceleration(Force force, long mass) {
		return new Acceleration(force.getForce() / mass, force.getDirection());
	}

	public static String getResource(String name) {
		return Utilities.class.getResource(name).toString();
	}

	public static InputStream getResourceAsStream(String name) {
		return Utilities.class.getResourceAsStream(name);
	}

	public static String round(double number, int places) {
		int roundedDouble = (int) (number * Math.pow(10, places));
		return Double.toString((double) roundedDouble / Math.pow(10, places));
	}

	public static Coordinate getCentreofImageView(ImageView imageView) {
		double centreX = imageView.getX() + imageView.getFitWidth() / 2;
		double centreY = imageView.getY() + imageView.getFitHeight() / 2;
		return new Coordinate(centreX, centreY);
	}
}

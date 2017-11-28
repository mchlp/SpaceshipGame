
/*
 * Michael Pu
 * SpaceshipGame - SpaceshipImageSet
 * ICS3U1 - Mr. Radulovic
 * November 27, 2017
 */


package backend;

import javafx.scene.image.Image;

public class SpaceshipImageSet {

	private Image mImageRocketLeftOff;
	private Image mImageRocketLeftOn;
	private Image mImageRocketRightOff;
	private Image mImageRocketRightOn;
	private Image mImageRocketMiddleOff;
	private Image mImageRocketMiddleOn;

	public SpaceshipImageSet(String imageRocketLeftOff, String imageRocketLeftOn, String imageRocketRightOff,
			String imageRocketRightOn, String imageRocketMiddleOff, String imageRocketMiddleOn) {
		mImageRocketLeftOff = new Image(Utilities.getResourceAsStream(imageRocketLeftOff));
		mImageRocketLeftOn = new Image(Utilities.getResourceAsStream(imageRocketLeftOn));
		mImageRocketRightOff = new Image(Utilities.getResourceAsStream(imageRocketRightOff));
		mImageRocketRightOn = new Image(Utilities.getResourceAsStream(imageRocketRightOn));
		mImageRocketMiddleOff = new Image(Utilities.getResourceAsStream(imageRocketMiddleOff));
		mImageRocketMiddleOn = new Image(Utilities.getResourceAsStream(imageRocketMiddleOn));
	}

	public Image getmImageRocketLeftOff() {
		return mImageRocketLeftOff;
	}

	public void setmImageRocketLeftOff(Image mImageRocketLeftOff) {
		this.mImageRocketLeftOff = mImageRocketLeftOff;
	}

	public Image getmImageRocketLeftOn() {
		return mImageRocketLeftOn;
	}

	public void setmImageRocketLeftOn(Image mImageRocketLeftOn) {
		this.mImageRocketLeftOn = mImageRocketLeftOn;
	}

	public Image getmImageRocketRightOff() {
		return mImageRocketRightOff;
	}

	public void setmImageRocketRightOff(Image mImageRocketRightOff) {
		this.mImageRocketRightOff = mImageRocketRightOff;
	}

	public Image getmImageRocketRightOn() {
		return mImageRocketRightOn;
	}

	public void setmImageRocketRightOn(Image mImageRocketRightOn) {
		this.mImageRocketRightOn = mImageRocketRightOn;
	}

	public Image getmImageRocketMiddleOff() {
		return mImageRocketMiddleOff;
	}

	public void setmImageRocketMiddleOff(Image mImageRocketMiddleOff) {
		this.mImageRocketMiddleOff = mImageRocketMiddleOff;
	}

	public Image getmImageRocketMiddleOn() {
		return mImageRocketMiddleOn;
	}

	public void setmImageRocketMiddleOn(Image mImageRocketMiddleOn) {
		this.mImageRocketMiddleOn = mImageRocketMiddleOn;
	}

}

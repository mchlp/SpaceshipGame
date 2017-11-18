/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import backend.Coordinate;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Animates an explosion
 * 
 * @author Michael Pu
 *
 */
public class Explosion {

	// pane to add explosion to
	private static Pane sPane;

	// FPS rate for explosion animation
	public static final int FPS = 120;

	// set up explosion spritesheet
	private static final String SPRITESHEET_LOCATION = Utilities.IMAGE_DIRECTORY + "explosion.png";
	private static final Image SPRITESHEET = new Image(SPRITESHEET_LOCATION);
	private static final int NUM_ROWS = 6;
	private static final int NUM_COLS = 8;
	public static final double CELL_WIDTH = SPRITESHEET.getWidth() / NUM_COLS;
	public static final double CELL_HEIGHT = SPRITESHEET.getHeight() / NUM_ROWS;

	private int frameNumber = 1;
	private double sinceLastFrame = 0;
	private ImageView mImageView;
	private long prevTime;
	private AnimationTimer timer;

	/**
	 * Creates and animates an explosion object
	 * 
	 * @param centrePosition
	 *            {@link Coordinate} representing the centre of the explosion
	 */
	public Explosion(Coordinate centrePosition) {
		mImageView = new ImageView(SPRITESHEET);
		// set position of explosion
		Coordinate position = getTopLeftPosition(centrePosition);
		mImageView.setX(position.getX());
		mImageView.setY(position.getY());
		// add explosion to pane
		sPane.getChildren().add(mImageView);
		// play explosion sound
		AudioControl.playExplosion();

		// set up explosion animation timer
		prevTime = System.nanoTime();
		timer = new AnimationTimer() {
			@Override
			public void handle(long curTime) {
				double deltaTime = (curTime - prevTime) / 1E9;
				update(deltaTime);
				prevTime = curTime;
			}
		};
		update(1 / 60);
		timer.start();
	}

	// animation loop
	private void update(double deltaTime) {

		// calculate the viewport of the current sprite on the spritesheet
		int row = frameNumber / NUM_COLS;
		int col = (frameNumber % NUM_COLS) - 1;
		Rectangle2D viewPort = new Rectangle2D(col * CELL_WIDTH, row * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		mImageView.setViewport(viewPort);

		// if the last frame has been displayed
		if (frameNumber >= NUM_COLS * NUM_ROWS) {
			mImageView.setImage(null);
			sPane.getChildren().remove(mImageView);
			timer.stop();
		}

		sinceLastFrame += deltaTime;

		// if it is time to display the next frame of animation
		if (sinceLastFrame > 1.0 / FPS) {
			int numFramesToAdvance = (int) (sinceLastFrame * FPS);
			frameNumber += numFramesToAdvance;
			sinceLastFrame -= (double) numFramesToAdvance / FPS;
		}
	}

	/**
	 * Sets the pane to add {@link Explosion explosions} to
	 * 
	 * @param pane
	 *            {@link Pane} to add {@link Explosion explosions} to
	 */
	public static void setPane(Pane pane) {
		sPane = pane;
	}

	// returns the top left position of the explosion based on the centre position
	private Coordinate getTopLeftPosition(Coordinate centrePosition) {
		double centreX = centrePosition.getX();
		double centreY = centrePosition.getY();
		double topLeftX = centreX - CELL_WIDTH / 2;
		double topLeftY = centreY - CELL_HEIGHT / 2;
		return new Coordinate(topLeftX, topLeftY);
	}
}

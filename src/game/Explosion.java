package game;

import backend.Coordinate;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Explosion {

	private static Pane sPane;

	public static final int FPS = 120;

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

	public Explosion(Coordinate centrePosition) {
		mImageView = new ImageView(SPRITESHEET);
		Coordinate position = getTopLeftPosition(centrePosition);
		mImageView.setX(position.getX());
		mImageView.setY(position.getY());
		sPane.getChildren().add(mImageView);
		AudioControl.playExplosion();

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

	// Game loop

	private void update(double deltaTime) {

		int row = frameNumber / NUM_COLS;
		int col = (frameNumber % NUM_COLS) - 1;

		Rectangle2D viewPort = new Rectangle2D(col * CELL_WIDTH, row * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);

		mImageView.setViewport(viewPort);

		if (frameNumber >= NUM_COLS * NUM_ROWS) {
			mImageView.setImage(null);
			sPane.getChildren().remove(mImageView);
			System.out.println("END");
			timer.stop();
		}
		sinceLastFrame += deltaTime;
		System.out.println(sinceLastFrame);
		if (sinceLastFrame > 1.0 / FPS) {
			int numFramesToAdvance = (int) (sinceLastFrame * FPS);
			frameNumber += numFramesToAdvance;
			sinceLastFrame -= (double) numFramesToAdvance / FPS;
		}
	}

	public static void setPane(Pane pane) {
		sPane = pane;
	}

	private Coordinate getTopLeftPosition(Coordinate centrePosition) {
		double centreX = centrePosition.getX();
		double centreY = centrePosition.getY();
		double topLeftX = centreX - CELL_WIDTH / 2;
		double topLeftY = centreY - CELL_HEIGHT / 2;
		return new Coordinate(topLeftX, topLeftY);
	}
}

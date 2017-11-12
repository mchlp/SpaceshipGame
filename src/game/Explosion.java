package game;

import java.util.ArrayList;

import backend.Coordinate;
import backend.Utilities;
import backend.Velocity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Explosion extends Sprite {

	private static ArrayList<Sprite> sSpriteList;

	private static final int CELL_WIDTH = 256;
	private static final int CELL_HEIGHT = 256;
	private static final int NUM_ROWS = 6;
	private static final int NUM_COLS = 8;
	private static final String SPRITESHEET_LOCATION = Utilities.IMAGE_DIRECTORY + "rocketLeftOff.png";
	private static final Image SPRITESHEET = new Image(SPRITESHEET_LOCATION);

	private int frameNumber = 1;
	private Rectangle viewPort;

	protected Explosion(Coordinate position) {
		super(new Velocity(), position, new ImageView());
		sSpriteList.add(this);
		mImageView.setImage(SPRITESHEET);
		viewPort = new Rectangle(CELL_WIDTH, CELL_HEIGHT);
	}

	@Override
	public void update(double deltaTime) {
		int row = frameNumber / NUM_COLS;
		int col = (frameNumber % NUM_COLS) - 1;

		viewPort.setX(col * NUM_COLS);
		viewPort.setY(row * NUM_ROWS);

		mImageView.setClip(viewPort);

		if (frameNumber >= NUM_COLS * NUM_ROWS) {
			sSpriteList.remove(this);
		}

		frameNumber++;
	}

	public static void setSpriteArrayList(ArrayList<Sprite> arrayList) {
		sSpriteList = arrayList;
	}
}

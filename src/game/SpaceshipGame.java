package game;

import backend.Planet;
import backend.Spaceship;
import backend.SpaceshipEngineDirection;
import backend.SpaceshipImageSet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpaceshipGame extends Application {

	private static final String IMAGE_DIRECTORY = "/images/";
	private static final String IMAGE_ROCKET_LEFT_OFF = IMAGE_DIRECTORY + "rocketLeftOff.png";
	private static final String IMAGE_ROCKET_LEFT_ON = IMAGE_DIRECTORY + "rocketLeftOn.png";
	private static final String IMAGE_ROCKET_RIGHT_OFF = IMAGE_DIRECTORY + "rocketRightOff.png";
	private static final String IMAGE_ROCKET_RIGHT_ON = IMAGE_DIRECTORY + "rocketRightOn.png";
	private static final String IMAGE_ROCKET_MIDDLE_OFF = IMAGE_DIRECTORY + "rocketMiddleOff.png";
	private static final String IMAGE_ROCKET_MIDDLE_ON = IMAGE_DIRECTORY + "rocketMiddleOn.png";

	private long prevTime;
	private Spaceship spaceship;
	private Planet earth;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();

		// Load the background image file
		Image backgroundImage = new Image(getClass().getResourceAsStream("/images/planet.jpg"));
		ImageView backgroundImageView = new ImageView();
		backgroundImageView.setImage(backgroundImage);

		// Load the spaceship image file
		SpaceshipImageSet spaceshipImageSet = new SpaceshipImageSet(IMAGE_ROCKET_LEFT_OFF, IMAGE_ROCKET_LEFT_ON,
				IMAGE_ROCKET_RIGHT_OFF, IMAGE_ROCKET_RIGHT_ON, IMAGE_ROCKET_MIDDLE_OFF, IMAGE_ROCKET_MIDDLE_ON);
		ImageView spaceshipImageView = new ImageView();
		spaceshipImageView.setPreserveRatio(true);
		spaceshipImageView.setFitWidth(50);
		spaceshipImageView.setX(500);
		spaceshipImageView.setY(500);

		// Create your spaceship here
		earth = new Planet();
		spaceship = new Spaceship(spaceshipImageView, spaceshipImageSet, earth);

		// Add your images to the root pane
		root.getChildren().add(backgroundImageView);
		root.getChildren().add(spaceshipImageView);

		// Create your scene using the root pane
		Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyPressed) {
				String code = keyPressed.getCode().toString();
				switch (code) {
				case "SPACE":
					spaceship.engineOn();
					break;
				case "LEFT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.RIGHT);
					break;
				case "RIGHT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.LEFT);
				}
			}
		});

		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyReleased) {
				String code = keyReleased.getCode().toString();
				switch (code) {
				case "SPACE":
					spaceship.engineOff();
					break;
				case "LEFT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.MIDDLE);
					break;
				case "RIGHT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.MIDDLE);
				}

			}
		});

		prevTime = System.nanoTime();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long curTime) {
				double deltaTime = (curTime - prevTime) / 1E9;
				onUpdate(deltaTime);
				prevTime = curTime;
			}
		};

		timer.start();

		primaryStage.setTitle("Spaceship Game");
		// Set the scene for this stage
		primaryStage.setScene(scene);
		// Finally, show the primary stage
		primaryStage.show();
	}

	private void onUpdate(double deltaTime) {
		spaceship.update(deltaTime);
	}
}

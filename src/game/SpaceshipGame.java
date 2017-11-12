package game;

import java.util.ArrayList;

import backend.LandingPad;
import backend.SpaceshipEngineDirection;
import backend.SpaceshipImageSet;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SpaceshipGame extends Application {

	private static final String IMAGE_DIRECTORY = "/images/";
	private static final String IMAGE_ROCKET_LEFT_OFF = IMAGE_DIRECTORY + "rocketLeftOff.png";
	private static final String IMAGE_ROCKET_LEFT_ON = IMAGE_DIRECTORY + "rocketLeftOn.png";
	private static final String IMAGE_ROCKET_RIGHT_OFF = IMAGE_DIRECTORY + "rocketRightOff.png";
	private static final String IMAGE_ROCKET_RIGHT_ON = IMAGE_DIRECTORY + "rocketRightOn.png";
	private static final String IMAGE_ROCKET_MIDDLE_OFF = IMAGE_DIRECTORY + "rocketMiddleOff.png";
	private static final String IMAGE_ROCKET_MIDDLE_ON = IMAGE_DIRECTORY + "rocketMiddleOn.png";
	private static final String IMAGE_BACKGROUND = IMAGE_DIRECTORY + "planet.jpg";

	private Scene scene;

	private ArrayList<Sprite> allSprites = new ArrayList<>();

	private long prevTime;
	private Spaceship spaceship;
	private Planet earth;
	private Planet moon;
	private LandingPad landingPad;

	private ImageView spaceshipImageView;
	private ImageView backgroundImageView;
	private Rectangle landingPadView;

	private double windowWidth;
	private double windowHeight;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();

		// Create scene using root pane
		scene = new Scene(root);

		// Load the background image file
		Image backgroundImage = new Image(Utilities.getResourceAsStream(IMAGE_BACKGROUND));
		backgroundImageView = new ImageView();
		backgroundImageView.setImage(backgroundImage);
		root.getChildren().add(backgroundImageView);

		// Add scene to stage and show
		primaryStage.setTitle("Spaceship Game");
		primaryStage.setScene(scene);
		primaryStage.show();

		windowWidth = primaryStage.getWidth();
		windowHeight = primaryStage.getHeight();

		// Load the spaceship image filesSystem.out.println(scene.getHeight());
		SpaceshipImageSet spaceshipImageSet = new SpaceshipImageSet(IMAGE_ROCKET_LEFT_OFF, IMAGE_ROCKET_LEFT_ON,
				IMAGE_ROCKET_RIGHT_OFF, IMAGE_ROCKET_RIGHT_ON, IMAGE_ROCKET_MIDDLE_OFF, IMAGE_ROCKET_MIDDLE_ON);

		// Create and set up spaceship imageview
		spaceshipImageView = new ImageView();

		// Create spaceship object
		moon = new Planet(7.34747309E+22, 1737000);
		earth = new Planet();
		spaceship = new Spaceship(spaceshipImageView, spaceshipImageSet, earth);

		// Create landing pad
		landingPadView = new Rectangle();
		landingPad = new LandingPad(spaceship, landingPadView, windowWidth, windowHeight);

		// Add children to root
		root.getChildren().add(spaceshipImageView);
		root.getChildren().add(landingPadView);

		// Add sprites to add sprite list
		allSprites.add(spaceship);
		allSprites.add(landingPad);

		// Handle key presses
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

		// Handles key releases
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

		// Game loop
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
	}

	private void onUpdate(double deltaTime) {
		for (Sprite sprite : allSprites) {
			sprite.update(deltaTime);
		}
	}
}

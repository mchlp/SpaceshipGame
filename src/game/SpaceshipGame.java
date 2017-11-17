/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import java.util.ArrayList;

import backend.SpaceshipEngineDirection;
import backend.SpaceshipImageSet;
import backend.Sprite;
import backend.Utilities;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * 
 * JavaFX Application class to run game (entry point for game)
 */

public class SpaceshipGame extends Application {

	private static final String IMAGE_ICON = Utilities.IMAGE_DIRECTORY + "icon.png";
	private static final String IMAGE_ROCKET_LEFT_OFF = Utilities.IMAGE_DIRECTORY + "rocketLeftOff.png";
	private static final String IMAGE_ROCKET_LEFT_ON = Utilities.IMAGE_DIRECTORY + "rocketLeftOn.png";
	private static final String IMAGE_ROCKET_RIGHT_OFF = Utilities.IMAGE_DIRECTORY + "rocketRightOff.png";
	private static final String IMAGE_ROCKET_RIGHT_ON = Utilities.IMAGE_DIRECTORY + "rocketRightOn.png";
	private static final String IMAGE_ROCKET_MIDDLE_OFF = Utilities.IMAGE_DIRECTORY + "rocketMiddleOff.png";
	private static final String IMAGE_ROCKET_MIDDLE_ON = Utilities.IMAGE_DIRECTORY + "rocketMiddleOn.png";
	private static final String IMAGE_BACKGROUND = Utilities.IMAGE_DIRECTORY + "planet.jpg";

	private Scene scene;
	private Pane root;
	private Stage primaryStage;
	private AnimationTimer timer;

	private ArrayList<Sprite> allSprites;

	private long prevTime;
	private Spaceship spaceship;
	private Planet earth;
	private Planet moon;
	private LandingPad landingPad;
	private FuelIndicator fuelIndicator;
	private SpeedIndicator speedIndicator;
	private GameOverIndicator gameOverIndicator;
	private ContextMenu menuBar;

	private ImageView spaceshipImageView;
	private ImageView backgroundImageView;
	private Rectangle landingPadView;
	private Text fuelLeftText;
	private Text speedText;
	private Text gameOverText;

	private double windowWidth;
	private double windowHeight;
	public double timePassed;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		root = new Pane();
		scene = new Scene(root);
		initalizeGame();
	}

	private void initalizeGame() {

		root.getChildren().clear();
		allSprites = new ArrayList<>();
		allSprites.clear();

		// Load the background image file
		Image backgroundImage = new Image(Utilities.getResourceAsStream(IMAGE_BACKGROUND));

		backgroundImageView = new ImageView();
		backgroundImageView.setImage(backgroundImage);
		root.getChildren().add(backgroundImageView);

		// Add scene to stage and show
		primaryStage.getIcons().add(new Image(Utilities.getResourceAsStream(IMAGE_ICON)));
		primaryStage.setTitle("Spaceship Game");
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
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

		// Create text for fuel left
		fuelLeftText = new Text();
		fuelIndicator = new FuelIndicator(fuelLeftText, spaceship);

		// Create text for speed of spaceship
		speedText = new Text();
		speedIndicator = new SpeedIndicator(speedText, spaceship);

		// Create game over text label
		gameOverText = new Text();
		gameOverIndicator = new GameOverIndicator(gameOverText, spaceship);

		// Menu on top
		menuBar = new ContextMenu();
		MenuItem restartMenuItem = new MenuItem("Restart");
		restartMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				restart();
			}
		});

		menuBar.getItems().add(restartMenuItem);
		backgroundImageView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent click) {
				menuBar.show(backgroundImageView, click.getX(), click.getY());
			}
		});

		// Add children to root
		root.getChildren().add(spaceshipImageView);
		root.getChildren().add(landingPadView);
		root.getChildren().add(fuelLeftText);
		root.getChildren().add(speedText);
		root.getChildren().add(gameOverText);

		// Add sprites to add sprite list
		allSprites.add(spaceship);
		allSprites.add(landingPad);
		allSprites.add(fuelIndicator);
		allSprites.add(speedIndicator);
		allSprites.add(gameOverIndicator);

		Explosion.setPane(root);

		// Handle key presses
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyPressed) {
				String code = keyPressed.getCode().toString();
				System.out.println(code);
				switch (code) {
				case "SPACE":
					spaceship.engineOn();
					break;
				case "LEFT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.RIGHT);
					break;
				case "RIGHT":
					spaceship.setEngineDirection(SpaceshipEngineDirection.LEFT);
					break;
				}
				keyPressed.consume();
			}
		});

		// Handles key releases
		scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

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
					break;
				}
				keyReleased.consume();

			}
		});

		// Game loop
		prevTime = System.nanoTime();
		timer = new AnimationTimer() {
			@Override
			public void handle(long curTime) {
				double deltaTime = (curTime - prevTime) / 1E9;
				onUpdate(deltaTime);
				prevTime = curTime;
				timePassed += deltaTime;
			}
		};

		timer.start();
	}

	private void onUpdate(double deltaTime) {

		for (Sprite sprite : allSprites) {
			sprite.update(deltaTime);
		}
	}

	private void restart() {
		System.out.println("CODE");
		menuBar.hide();
		timer.stop();
		initalizeGame();
	}
}

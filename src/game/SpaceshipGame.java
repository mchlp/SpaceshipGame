package game;

import backend.Planet;
import backend.Spaceship;
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
		Image engineOffImage = new Image(getClass().getResourceAsStream("/images/rocket.png"));
		Image engineOnImage = new Image(getClass().getResourceAsStream("/images/rocketFlame.png"));
		ImageView spaceshipImageView = new ImageView();
		spaceshipImageView.setPreserveRatio(true);
		spaceshipImageView.setFitWidth(50);
		spaceshipImageView.setX(500);
		spaceshipImageView.setY(500);

		Image prankImage = new Image(getClass().getResourceAsStream("/images/clown.png"));
		ImageView prankView = new ImageView();
		prankView.setImage(prankImage);
		prankView.setPreserveRatio(true);
		prankView.setFitHeight(1000);
		prankView.setX(100);
		prankView.setY(100);
		prankView.setVisible(false);

		// Create your spaceship here
		earth = new Planet();
		spaceship = new Spaceship(spaceshipImageView, engineOffImage, engineOnImage, earth);

		// Add your images to the root pane
		root.getChildren().add(backgroundImageView);
		root.getChildren().add(spaceshipImageView);
		root.getChildren().add(prankView);

		// Create your scene using the root pane
		Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyPressed) {
				String code = keyPressed.getCode().toString();
				switch (code) {
				case "SPACE":
					spaceship.engineOn();
					/*
					old code
					if (prankView.isVisible()) {
						prankView.setVisible(false);
					} else {
						prankView.setVisible(true);
					}
					*/
					break;
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

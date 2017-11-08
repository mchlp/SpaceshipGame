package game;

import backend.Spaceship;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpaceshipGame extends Application {

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
		Image spaceshipImage = new Image(getClass().getResourceAsStream("/images/rocket.png"));
		ImageView spaceshipImageView = new ImageView();
		spaceshipImageView.setImage(spaceshipImage);
		spaceshipImageView.setPreserveRatio(true);
		spaceshipImageView.setFitHeight(200);
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
		Spaceship spaceship = new Spaceship(spaceshipImageView);
		System.out.println(spaceship.getVelocity().getSpeed());

		// Add your images to the root pane
		root.getChildren().add(backgroundImageView);
		root.getChildren().add(spaceshipImageView);
		root.getChildren().add(prankView);

		// Create your scene using the root pane
		Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				switch (code) {
				case "SPACE":
					if (prankView.isVisible()) {
						prankView.setVisible(false);
					} else {
						prankView.setVisible(true);
					}
					break;
				}
			}
		});

		primaryStage.setTitle("Spaceship Game");
		// Set the scene for this stage
		primaryStage.setScene(scene);
		// Finally, show the primary stage
		primaryStage.show();
	}

}

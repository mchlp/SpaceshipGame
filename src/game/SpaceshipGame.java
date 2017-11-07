package game;

import backend.Spaceship;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

		// Create your spaceship here
		Spaceship spaceship = new Spaceship(spaceshipImageView);

		// Add your images to the root pane
		root.getChildren().add(backgroundImageView);
		root.getChildren().add(spaceshipImageView);

		// Create your scene using the root pane
		Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());

		primaryStage.setTitle("Spaceship Game");
		// Set the scene for this stage
		primaryStage.setScene(scene);
		// Finally, show the primary stage
		primaryStage.show();
	}

}

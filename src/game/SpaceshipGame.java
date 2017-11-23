
/*
 * Michael Pu
 * SpaceshipGame - SpaceshipGame
 * November 2017
 */

package game;

import backend.SpaceshipImageSet;
import backend.Sprite;
import backend.Utilities;
import game.Spaceship.SpaceshipEngineDirection;
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

import java.util.ArrayList;

/**
 * JavaFX Application class to run game (entry point for game)
 */

public class SpaceshipGame extends Application {

    // image file locations
    private static final String IMAGE_ICON = Utilities.IMAGE_DIRECTORY + "icon.png";
    private static final String IMAGE_ROCKET_LEFT_OFF = Utilities.IMAGE_DIRECTORY + "rocketLeftOff.png";
    private static final String IMAGE_ROCKET_LEFT_ON = Utilities.IMAGE_DIRECTORY + "rocketLeftOn.png";
    private static final String IMAGE_ROCKET_RIGHT_OFF = Utilities.IMAGE_DIRECTORY + "rocketRightOff.png";
    private static final String IMAGE_ROCKET_RIGHT_ON = Utilities.IMAGE_DIRECTORY + "rocketRightOn.png";
    private static final String IMAGE_ROCKET_MIDDLE_OFF = Utilities.IMAGE_DIRECTORY + "rocketMiddleOff.png";
    private static final String IMAGE_ROCKET_MIDDLE_ON = Utilities.IMAGE_DIRECTORY + "rocketMiddleOn.png";
    private static final String IMAGE_BACKGROUND = Utilities.IMAGE_DIRECTORY + "planet.jpg";

    // JavaFX objects
    private Scene scene;
    private Pane root;
    private Stage primaryStage;
    private AnimationTimer timer;

    // list containing all sprites in the game
    private ArrayList<Sprite> allSprites;

    // time when game was last updated
    private long prevTime;

    // menu to display on right click
    private ContextMenu rightClickMenu;

    // entry point for game
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // set up game window
        this.primaryStage = primaryStage;
        root = new Pane();
        scene = new Scene(root);

        initalizeGame();
    }

    private void initalizeGame() {

        // Reset variables
        root.getChildren().clear();
        allSprites = new ArrayList<>();
        allSprites.clear();

        // Load the background image file and add to root
        Image backgroundImage = new Image(Utilities.getResourceAsStream(IMAGE_BACKGROUND));
        ImageView backgroundImageView = new ImageView();
        backgroundImageView.setImage(backgroundImage);
        root.getChildren().add(backgroundImageView);

        // Add scene to stage and show stage
        primaryStage.getIcons().add(new Image(Utilities.getResourceAsStream(IMAGE_ICON)));
        primaryStage.setTitle("Spaceship Game");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();

        // Get dimensions of stage
        double windowWidth = primaryStage.getWidth();
        double windowHeight = primaryStage.getHeight();

        // Load the spaceship image files
        SpaceshipImageSet spaceshipImageSet = new SpaceshipImageSet(IMAGE_ROCKET_LEFT_OFF, IMAGE_ROCKET_LEFT_ON,
                IMAGE_ROCKET_RIGHT_OFF, IMAGE_ROCKET_RIGHT_ON, IMAGE_ROCKET_MIDDLE_OFF, IMAGE_ROCKET_MIDDLE_ON);

        // Create and set up planets
        Planet moon = new Planet(7.34747309E+22, 1737000);
        Planet earth = new Planet();

        // Create spaceship
        ImageView spaceshipImageView = new ImageView();
        Spaceship spaceship = new Spaceship(spaceshipImageView, spaceshipImageSet, earth);

        // Create landing pad
        Rectangle landingPadView = new Rectangle();
        LandingPad landingPad = new LandingPad(spaceship, landingPadView, windowWidth, windowHeight);

        // Create text for fuel left
        Text fuelLeftText = new Text();
        FuelIndicator fuelIndicator = new FuelIndicator(fuelLeftText, spaceship);

        // Create text for speed
        Text speedText = new Text();
        SpeedIndicator speedIndicator = new SpeedIndicator(speedText, spaceship);

        // Create game over text label
        Text gameOverText = new Text();
        GameOverIndicator gameOverIndicator = new GameOverIndicator(gameOverText, spaceship);

        // Set up right-click menu
        rightClickMenu = new ContextMenu();

        // add and set up restart item to right-click menu
        MenuItem restartMenuItem = new MenuItem("Restart");
        restartMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restart();
            }
        });
        rightClickMenu.getItems().add(restartMenuItem);

        // show rightClickMenu when a right click is detected on the background image
        backgroundImageView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent click) {
                rightClickMenu.show(backgroundImageView, click.getScreenX(), click.getScreenY());
            }
        });

        // Add nodes to root
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

        // Set up pane for explosions
        Explosion.setPane(root);

        // Handle key presses
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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

        // Set up game loop
        prevTime = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long curTime) {
                double deltaTime = (curTime - prevTime) / 1E9;
                // System.out.println(1 / deltaTime);
                onUpdate(deltaTime);
                prevTime = curTime;
            }
        };

        // Start game loop
        timer.start();
    }

    private void onUpdate(double deltaTime) {
        // Call update function in each sprite
        for (Sprite sprite : allSprites) {
            sprite.update(deltaTime);
        }
    }

    // Restart the game
    private void restart() {
        rightClickMenu.hide();
        timer.stop();
        initalizeGame();
    }
}

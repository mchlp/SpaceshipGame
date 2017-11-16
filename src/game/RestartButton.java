package game;

import backend.Coordinate;
import backend.Sprite;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class RestartButton extends Sprite {

	private Button mButton;
	private boolean mClicked;

	public RestartButton(Button button) {
		super();
		mButton = button;
		mButton.setText("Restart");

		mButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				System.out.println("CLICK");
				mClicked = true;
			}
		});
	}

	@Override
	public void update(double deltaTime) {
		mPosition = new Coordinate(mButton.getScene().getWidth() - mButton.getWidth(), 0);
		updatePosition();
	}

	public void updatePosition() {
		mButton.relocate(mPosition.getX(), mPosition.getY());
	}

	public boolean getmClicked() {
		return mClicked;
	}

	public void setmClicked(boolean clicked) {
		mClicked = clicked;
	}
}

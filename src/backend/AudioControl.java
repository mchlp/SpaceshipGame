package backend;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioControl {

	private static final String AUDIO_DIRECTORY = "/audio/";
	private static final String AUDIO_ALARM = AUDIO_DIRECTORY + "alarm.mp3";
	private static final String AUDIO_ENGINE = AUDIO_DIRECTORY + "engines.mp3";

	private AudioClip mAlarm;
	private Media mEngines;
	private MediaPlayer mEnginesPlayer;

	public AudioControl() {
		mAlarm = new AudioClip(getClass().getResource(AUDIO_ALARM).toString());
		mEngines = new Media(getClass().getResource(AUDIO_ENGINE).toString());
		mEnginesPlayer = new MediaPlayer(mEngines);

	}

	public void playAlarm() {
		mAlarm.play();
	}

	public void playEngines() {
		mEnginesPlayer.play();
	}

	public void stopEngines() {
		mEnginesPlayer.stop();
		mEnginesPlayer.seek(mEnginesPlayer.getStartTime());
	}
}

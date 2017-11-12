package backend;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioControl {

	private static final String AUDIO_DIRECTORY = "/audio/";
	private static final String AUDIO_ALARM = AUDIO_DIRECTORY + "alarm.mp3";
	private static final String AUDIO_FUEL_ALARM = AUDIO_DIRECTORY + "fuelAlarm.mp3";
	private static final String AUDIO_ENGINE = AUDIO_DIRECTORY + "engines.mp3";
	private static final String AUDIO_TERRAIN_ALARM = AUDIO_DIRECTORY + "terrainAlarm.mp3";
	private static final String AUDIO_CLOSE = AUDIO_DIRECTORY + "close.mp3";
	private static final String AUDIO_LANDED = AUDIO_DIRECTORY + "landed.mp3";
	private static final String AUDIO_EXPLOSION = AUDIO_DIRECTORY + "explosion.mp3";

	public static AudioClip alarm = new AudioClip(Utilities.getResource(AUDIO_ALARM));
	public static AudioClip fuelAlarm = new AudioClip(Utilities.getResource(AUDIO_FUEL_ALARM));
	public static AudioClip terrainAlarm = new AudioClip(Utilities.getResource(AUDIO_TERRAIN_ALARM));
	public static AudioClip touched = new AudioClip(Utilities.getResource(AUDIO_LANDED));
	public static AudioClip explosion = new AudioClip(Utilities.getResource(AUDIO_EXPLOSION));

	private static Media engines = new Media(Utilities.getResource(AUDIO_ENGINE));
	private static MediaPlayer enginesPlayer = new MediaPlayer(engines);
	private static Media close = new Media(Utilities.getResource(AUDIO_CLOSE));
	private static MediaPlayer closePlayer = new MediaPlayer(close);

	public static void playAlarm() {
		alarm.play();
	}

	public static void playFuelAlarm() {
		fuelAlarm.play();
	}

	public static void playEngines() {
		closePlayer.setCycleCount(MediaPlayer.INDEFINITE);
		enginesPlayer.play();
	}

	public static void stopEngines() {
		enginesPlayer.stop();
		enginesPlayer.seek(enginesPlayer.getStartTime());
	}

	public static void playTerrainAlarm() {
		terrainAlarm.play();
	}

	public static void playTouched() {
		touched.play();
	}

	public static void playExplosion() {
		explosion.play();
	}

	public static void playClose() {
		closePlayer.setCycleCount(MediaPlayer.INDEFINITE);
		closePlayer.play();
	}

	public static void stopClose() {
		closePlayer.stop();
		closePlayer.seek(closePlayer.getStartTime());
	}
}

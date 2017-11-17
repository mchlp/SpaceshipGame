/*******************************************************************************
 * Michael Pu
 * Spaceship Game Assignment
 * ICS3U1 - November 2017
 * Mr. Radulovic
 ******************************************************************************/
package game;

import backend.Utilities;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioControl {

	private static final String AUDIO_ALARM = Utilities.AUDIO_DIRECTORY + "alarm.mp3";
	private static final String AUDIO_FUEL_ALARM = Utilities.AUDIO_DIRECTORY + "fuelAlarm.mp3";
	private static final String AUDIO_ENGINE = Utilities.AUDIO_DIRECTORY + "engines.mp3";
	private static final String AUDIO_TERRAIN_ALARM = Utilities.AUDIO_DIRECTORY + "terrainAlarm.mp3";
	private static final String AUDIO_FAST = Utilities.AUDIO_DIRECTORY + "fast.mp3";
	private static final String AUDIO_LANDED = Utilities.AUDIO_DIRECTORY + "landed.mp3";
	private static final String AUDIO_EXPLOSION = Utilities.AUDIO_DIRECTORY + "explosion.mp3";

	public static AudioClip alarm = new AudioClip(Utilities.getResource(AUDIO_ALARM));
	public static AudioClip fuelAlarm = new AudioClip(Utilities.getResource(AUDIO_FUEL_ALARM));
	public static AudioClip terrainAlarm = new AudioClip(Utilities.getResource(AUDIO_TERRAIN_ALARM));
	public static AudioClip touched = new AudioClip(Utilities.getResource(AUDIO_LANDED));
	public static AudioClip explosion = new AudioClip(Utilities.getResource(AUDIO_EXPLOSION));
	public static AudioClip fast = new AudioClip(Utilities.getResource(AUDIO_FAST));

	private static Media engines = new Media(Utilities.getResource(AUDIO_ENGINE));
	private static MediaPlayer enginesPlayer = new MediaPlayer(engines);

	public static void playAlarm() {
		alarm.play();
	}

	public static void playFuelAlarm() {
		fuelAlarm.play();
	}

	public static void playEngines() {
		enginesPlayer.setCycleCount(MediaPlayer.INDEFINITE);
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

	public static void playFast() {
		fast.play();
	}
}

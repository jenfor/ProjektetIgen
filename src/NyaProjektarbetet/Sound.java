package NyaProjektarbetet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
    /**
     * Class-constructor
     */
	public Sound(){
		
	}
    /**
     * Plays sounds/music. 
     * 
     * @param url    the source-name of the sound
     */
	public static synchronized void playSomeSound(final String url) {
		  new Thread(new Runnable() {
		//vill man ha loop: byt ut clip mot loop, fungerar nog bara med wav
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        MiniGame.class.getResourceAsStream(url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
}

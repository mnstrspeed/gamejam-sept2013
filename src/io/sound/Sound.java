package io.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * An example of loading and playing a sound using a Clip. This complete class
 * isn't in the book ;)
 */
public class Sound {

  public Sound() throws Exception {

    File soundFile = new File("res/sounds/monkey-yell1.wav");
    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
    Clip clip = (Clip) AudioSystem.getLine(info);
    clip.open(sound);

    clip.addLineListener(new LineListener() {
      public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
          event.getLine().close();
          System.exit(0);
        }
      }
    });

    // play the sound clip
    clip.start();
  }
}    

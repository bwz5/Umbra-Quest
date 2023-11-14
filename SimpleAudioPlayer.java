// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{
    String filePath = "audio/gameSound.wav";
    String keyCollected = "audio/Level Up Sound Effect.wav";
    String clickSound = "audio/Insert Tape - Sound Effect.wav";
    String damageTaken = "audio/Super Mario World  - Pipe, Damage Sound.wav";
    String fireballFilePath = "audio/cartoon fireball sound effect.wav";

    // constructor to initialize streams and clip
    public SimpleAudioPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // SimpleAudioPlayer.playSound(filePath);
    }

    public  synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        new File(url).getAbsoluteFile());
                    clip.open(inputStream);
                    clip.start();
                    while (true){
                        if (!clip.isRunning()){
                            clip.start();
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();

    }
    public  synchronized void playFireBallSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File(url).getAbsoluteFile());
                    clip.open(inputStream);
                    clip.start();
                    while (true){
                        if (!clip.isRunning()){
                            clip.start();
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }


}

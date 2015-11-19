package city;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;

/**
 * Handles playing, stoping, and looping of sounds for the game.
 * @author Tyler Thomas
 *
 */
public class Sound {
    private Clip clip;
    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
        	//fileName = "file:" + fileName;
          URL url = new URL(fileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(url);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);

        }catch(Exception e2){
        	
        	try{
        		
        		 URL url =  getClass().getResource(fileName);  
        		//System.out.println(url);
        		AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);// check the URL!
        		//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        		clip = AudioSystem.getClip();
                clip.open(inputStream);
                
        		
        	}
        
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
        }
    // play, stop, loop the sound clip
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
            clip.stop();
        }
    }
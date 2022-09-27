package com.sound;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Sound {


    Clip clip;
    URL[] soundURL =new URL[14]; //stores sound files

    public Sound() {


        soundURL[0] = getClass().getResource("/Sound/Music/arcade-game.wav");
        soundURL[1] = getClass().getResource("/Sound/audio001.wav");
        soundURL[2] = getClass().getResource("/Sound/SE/cat_meow2.wav");
        soundURL[3] = getClass().getResource("/Sound/SE/cat_scream.wav");
        soundURL[4] = getClass().getResource("/Sound/SE/chicken_bock_x.wav");
        soundURL[5] = getClass().getResource("/Sound/SE/chime_up.wav");
        soundURL[6] = getClass().getResource("/Sound/SE/click.wav");
        soundURL[7] = getClass().getResource("/Sound/SE/dog_bark_x.wav");
        soundURL[8] = getClass().getResource("/Sound/SE/dog_growl3.wav");
        soundURL[9] = getClass().getResource("/Sound/SE/fanfare.wav");
        soundURL[10] = getClass().getResource("/Sound/SE/whahwhah.wav");
        soundURL[11] = getClass().getResource("/Sound/SE/happycat.wav");
        soundURL[12] = getClass().getResource("/Sound/SE/happydog.wav");


    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println("File Set");


        }catch (Exception e) {
            System.out.println("Invalid File");
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}

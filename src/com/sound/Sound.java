package com.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL =new URL[10]; //stores sound files

    public Sound() {
        soundURL[0] = getClass().getResource("/Sound/arcade-game.wav");
        soundURL[1] = getClass().getResource("/Sound/audio001.wav");
        soundURL[2] = getClass().getResource("/Sound/fanfare2.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {}
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

    public static void playMusic(int i){

        Sound sound = new Sound();

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public static void main(String[] args) {

        playMusic(1);

    }
}

package com.sound;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Sound {
    JFrame frame;
    JPanel panel;

    Clip clip;
    URL[] soundURL =new URL[10]; //stores sound files

    public Sound() {
        frame = new JFrame("Audio");
        panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);

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
        new Sound();
        playMusic(0);

    }
}

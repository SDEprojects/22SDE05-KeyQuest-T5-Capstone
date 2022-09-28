package com.sound;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.net.URL;

public class Sound {


    private Clip clip;
    private URL[] soundURL =new URL[14]; //stores sound files
    private float previousVolume = 0;
    private float currentVolume = 0;
    private FloatControl fc;
    private boolean mute = false;


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
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);


        }catch (Exception e) {
        }
    }

    public void play() {
        //clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void volumeUp() {
        currentVolume += 1.0f;
        if(currentVolume > 6.0f) { //6.0 is highest FloatControl accepts
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeDown() {
        currentVolume -= 1.01f;
        if(currentVolume < -80.0f) { //-80.0f is lowest FloatControl accepts
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute() {
       this.mute = mute;
        if(!this.mute) {
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);

        }
        else if(this.mute) {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
        }
        System.out.println("this is" + mute);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public URL[] getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(URL[] soundURL) {
        this.soundURL = soundURL;
    }

    public float getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(float previousVolume) {
        this.previousVolume = previousVolume;
    }

    public float getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(float currentVolume) {
        this.currentVolume = currentVolume;
    }

    public FloatControl getFc() {
        return fc;
    }

    public void setFc(FloatControl fc) {
        this.fc = fc;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
}

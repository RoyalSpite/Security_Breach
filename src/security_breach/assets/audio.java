package security_breach.assets;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import security_breach.Game;

public class audio {

    private boolean playing;
    private File file;
    private Clip clip;
    private long audioIndex;
    
    public audio(String path){
        file = new File(path);
    }
    
    public void set(){
        try{
            if(file.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }
            else{
                System.out.println("404 not found.");
            }
                
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void playloop(){
        this.play();
        if(Game.isAudioOn()){
            playing = true;
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    public void play(){
        if(Game.isAudioOn()){
            this.set();
            clip.setMicrosecondPosition(audioIndex);
            clip.start();
        }
    }
    
    public void stop() {
        playing = false;
        audioIndex = (Game.isRunning()? (clip.getMicrosecondPosition()):(0));
        clip.stop();
        clip.close();
    }

    public boolean isPlaying() {
        return playing;
    }

}

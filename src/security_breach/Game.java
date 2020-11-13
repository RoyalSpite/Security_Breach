package security_breach;

import security_breach.Panel.GameFrame;
import java.util.Random;
import security_breach.Screen.MainFrame;
import security_breach.assets.audio;

public class Game{

    private static boolean audioOn;
    private static boolean running;
    private static boolean init;
    public static final Random rand = new Random();
    public static final audio fx1 = new audio("selection_beep.wav");
    public static final char omega = '\u03a9';
    public static int currentGame;
    public static GameFrame RealGame;
    public static MainFrame subGame;
    public static final int baseWidth = 660;
    public static final int baseHeight = 1210;

    
    public static void main(String[] args) {
        RealGame = new GameFrame("Security_Breach");
    }

    public static void setRunning(boolean running) {
        Game.running = running;
    }

    public static boolean isRunning() {
        return running;
    }

    public static boolean isAudioOn() {
        return audioOn;
    }

    public static void setAudioOn(boolean audioOn) {
        Game.audioOn = audioOn;
    }

    public static boolean isInit() {
        return init;
    }

    public static void setInit(boolean init) {
        Game.init = init;
    }
    
    public static boolean PrimeNumberChecker(int n){
        
        for(int i=2;i<=n/2;i++)
	{
           int temp = n%i;
	   if(temp==0)
	   {
	      return false;
	   }
	}
        return true;
        
    }
    
}

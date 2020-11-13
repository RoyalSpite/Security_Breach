
package security_breach.Screen;

import security_breach.Screen.GameScreen;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;
import security_breach.Game;


public class MainFrame extends JPanel{
    
    private int currentScreen;
    public static final int MainWidth = 1220;
    public static final int MainHeight = 620;
    public final CardLayout cards = new CardLayout();
    private String[] ScreenName;
    
    public MainFrame(String[] arr){
        ScreenName = arr;
        this.setVisible(true);
        this.setLayout(cards);
        this.setBounds(30,30,MainFrame.MainWidth,MainFrame.MainHeight);
    }


    @Override
    public GameScreen getComponent(int n) {
        return (GameScreen)super.getComponent(n);
    }
    
    public void showScreen(int n){        
        if(this.getComponent(currentScreen).timer!=null) this.getComponent(currentScreen).timer.stop();
        if(this.getComponent(currentScreen).music!=null 
                && this.getComponent(currentScreen).music.isPlaying()) this.getComponent(currentScreen).music.stop();
        
        this.cards.show(this,ScreenName[n]);
     
        if(this.getComponent(n).timer!=null) this.getComponent(n).timer.start();
        if(this.getComponent(n).music!=null && Game.isAudioOn()) this.getComponent(n).music.playloop();
        
        currentScreen = n;
        
    }

    public CardLayout getCards() {
        return cards;
    }
    
}
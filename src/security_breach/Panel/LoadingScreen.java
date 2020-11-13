package security_breach.Panel;

import security_breach.Screen.GameScreen;
import security_breach.Panel.GameFrame;
import security_breach.Screen.MainFrame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import security_breach.Game;
import security_breach.assets.GameLabel;

public class LoadingScreen extends GameScreen{
    
    private GameLabel Loading;
    private static int counter = 0;
    private static int cc = 0;
    
    public LoadingScreen(){
        timer = new Timer(250,this);
        Loading = new GameLabel("LOADING",60);
        Loading.setBounds((MainFrame.MainWidth/2)+70-175,(MainFrame.MainHeight/2)-50,175,50);
        Loading.removeMouseListener(Loading);
        this.add(Loading);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
        if(counter%5==0){
            cc++;
            if(cc==2){
                cc = 0;
                counter = 0;
                if(Game.isRunning()==false){
                    Game.RealGame.init();
                    Game.RealGame.getScreen().showScreen(2);
                    Game.setRunning(true);
                }
                else{
                    Game.RealGame.deinit();
                    Game.RealGame.getScreen().showScreen(0);
                    Game.setRunning(false);
                }
            }
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(GameFrame.SecondColor);
        for(int i=0;i<(counter%4);i++){
            g.fillRect((MainFrame.MainWidth/2)+70+(15*i),(MainFrame.MainHeight/2)-9,8,8);
        }
                
    }

    
}

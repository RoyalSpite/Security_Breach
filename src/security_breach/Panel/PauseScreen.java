package security_breach.Panel;

import security_breach.Panel.GameFrame;

import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import security_breach.Game;
import security_breach.Panel.PlayScreen;
import security_breach.Screen.GameScreen;
import security_breach.assets.GameLabel;

public class PauseScreen extends GameScreen{
    
    private GameLabel Label_back;
    private GameLabel Label_resume;
    private GameLabel Pause;
    
    public PauseScreen(){
        Pause = new GameLabel();
        Pause.setText("PAUSE",70);
        Pause.setHorizontalAlignment(SwingConstants.CENTER);
        Pause.setBounds(this.getWidth()/2-100,150,200,100);
        
        Label_back = new GameLabel("BACK TO MENU",45);
        Label_back.setHorizontalAlignment(SwingConstants.CENTER);
        Label_back.setBounds(this.getWidth()/2-350,350,250,50);
        Label_back.addMouseListener(this);
        
        Label_resume = new GameLabel("RESUME",45);
        Label_resume.setHorizontalAlignment(SwingConstants.CENTER);
        Label_resume.setBounds(this.getWidth()/2+100,350,200,50);
        Label_resume.addMouseListener(this);
        
        this.add(Pause);
        this.add(Label_back);
        this.add(Label_resume);
 
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(e.getSource()==Label_back){
                Game.RealGame.getScreen().showScreen(1);
            }
            else if(e.getSource()==Label_resume){
                Game.RealGame.getScreen().showScreen(2);
            }
        }
    }

}
package security_breach.Panel;

import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import security_breach.Game;
import security_breach.Screen.GameScreen;
import security_breach.Screen.MainFrame;
import security_breach.assets.GameLabel;

public class GameEnd extends GameScreen{
    
    private GameLabel GameStateText;
    private GameLabel Label_exit;
    private GameLabel Label_return;
    
    GameEnd(String str){
        
        GameStateText = new GameLabel();
        GameStateText.setText(str,60);
        GameStateText.setHorizontalAlignment(SwingConstants.CENTER);
        GameStateText.setBounds(0,(MainFrame.MainHeight/2)-100,MainFrame.MainWidth,65);
        
        Label_exit = new GameLabel("EXIT",45);
        Label_exit.setBounds((MainFrame.MainWidth/2)-300,(MainFrame.MainHeight/2)+100,150,50);
        Label_exit.setHorizontalAlignment(SwingConstants.CENTER);
        Label_exit.addMouseListener(this);
        
        Label_return = new GameLabel("BACT TO MENU",45);
        Label_return.setBounds((MainFrame.MainWidth/2)+100,(MainFrame.MainHeight/2)+100,250,50);
        Label_return.setHorizontalAlignment(SwingConstants.CENTER);
        Label_return.addMouseListener(this);
        
        this.add(GameStateText);
        this.add(Label_exit);
        this.add(Label_return);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(e.getButton()==MouseEvent.BUTTON1){
            if(e.getSource()==Label_exit) System.exit(0);
            
            if(e.getSource()==Label_return) Game.RealGame.getScreen().showScreen(1);
        }
    }

    
}
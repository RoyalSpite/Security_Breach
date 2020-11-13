package security_breach.Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import security_breach.Game;
import security_breach.Screen.GameScreen;
import security_breach.Screen.MainFrame;
import security_breach.assets.CoreButton;
import security_breach.assets.JHexPanel;

public class MainCore extends GameScreen{
    
    private JHexPanel alpha;
    private JHexPanel beta;
    private JHexPanel gamma;
    private JHexPanel delta;
    private final int radius = 100;
    
    public MainCore(){
        alpha = new CoreButton("ALPHA",(this.getWidth()/4)-(110),(this.getHeight()/4)-(192/2)-15,this.radius);
        alpha.addMouseListener(this);

        beta = new CoreButton("BETA",(this.getWidth()/4+this.getWidth()/2)-(110),alpha.getY(),this.radius);
        beta.addMouseListener(this);
        
        gamma = new CoreButton("GAMMA",beta.getX(),alpha.getY()+(this.getHeight()/2-29),this.radius);
        gamma.addMouseListener(this);
        
        delta = new CoreButton("DELTA",alpha.getX(),gamma.getY(),this.radius);
        delta.addMouseListener(this);
        
        this.add(alpha);
        this.add(beta);
        this.add(gamma);
        this.add(delta);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(e.getSource()==alpha && alpha.isClickable){
                Game.currentGame = 1;
            }
            else if(e.getSource()==beta && beta.isClickable){
                Game.currentGame = 2;
            }
            else if(e.getSource()==gamma && gamma.isClickable){
                Game.currentGame = 3;
            }
            else if(e.getSource()==delta && delta.isClickable){
                Game.currentGame = 4;
            }
            Game.subGame.showScreen(Game.currentGame);
        }
        
    }

}

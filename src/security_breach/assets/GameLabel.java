package security_breach.assets;

import security_breach.Panel.GameFrame;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import security_breach.Game;

public class GameLabel extends JLabel implements MouseListener{
    
    public int cooldown;
    public int cooldown_count;
    protected boolean enable;
    protected boolean action;
    
    public GameLabel(){
        enable = true;
        this.setOpaque(false);
        this.setForeground(GameFrame.SecondColor);
    }
    
    public GameLabel(String str,int text_size){
        this();
        this.setOpaque(false);
        this.setText(str);
        this.setFont(new Font("Agency FB",Font.BOLD,text_size));
        this.addMouseListener(this);
    }
    
    public void setText(String str,int text_size){
        this.setText(str);
        this.setFont(new Font("Agency FB",Font.BOLD,text_size));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}


    @Override
    public void mouseReleased(MouseEvent e) {}

     @Override
    public void mouseEntered(MouseEvent e) {
        if(this.enable) Game.fx1.play();
        this.setOpaque(true);
        this.setBackground(GameFrame.SecondColor);
        this.setForeground(GameFrame.MainColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setFG();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //mouseClicked(e);
    }
    
    public void setFG(){
        this.setOpaque(false);
        this.setForeground(GameFrame.SecondColor);
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        this.action = false;
    }

    public void setAction(boolean action) {
        this.action = action;
    
    }

    public boolean isAction() {
        return action;
    }

    public void setCooldown(int n){
        this.cooldown = n;
        this.cooldown_count = n*10;
    }
    
}

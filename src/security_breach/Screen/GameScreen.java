package security_breach.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import security_breach.Panel.GameFrame;
import security_breach.assets.audio;

public class GameScreen extends JPanel implements ActionListener,MouseListener{
    
    protected audio music;
    protected Timer timer;
    
    public GameScreen(){
        this.setOpaque(true);
        this.setBackground(GameFrame.MainColor);
        this.setBounds(0,0,MainFrame.MainWidth,MainFrame.MainHeight);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        //mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
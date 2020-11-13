package security_breach.Screen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import security_breach.Game;
import security_breach.Node.dataNode;
import security_breach.Panel.PlayScreen;

public class NodePanel extends GameScreen{
    
    public static final int PanelWidth = MainFrame.MainWidth-310;
    public static final int PanelHeight = BasePanel.BaseHeight;
    private GameScreen rearScreen;
    private GameScreen frontScreen;
    dataNode dn;
    
    public NodePanel(dataNode d){
        
        dn = d;
        this.setBounds(0,0,PanelWidth,PanelHeight);
        
        rearScreen = new GameScreen(){
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                g2.setColor(Color.BLACK);
                dn.paintComponent(g);
            }
            
        };
        rearScreen.setOpaque(false);
        rearScreen.setBounds(0,0,PanelWidth,PanelHeight);
        
        frontScreen = new GameScreen();
        frontScreen.setOpaque(false);
        for(int i=0;i<dn.getNodes().length;i++){
            frontScreen.add(dn.getNodes()[i]);
        }
        frontScreen.setBounds(rearScreen.getBounds());
        
        this.add(frontScreen);
        this.add(rearScreen);
        
    }

    public GameScreen getRearScreen() {
        return rearScreen;
    }

    public GameScreen getFrontScreen() {
        return frontScreen;
    }

}

package security_breach.assets;

import security_breach.Panel.GameFrame;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import security_breach.Game;

public class CoreButton extends JHexPanel{
    
    private GameLabel text = new GameLabel();
    public CoreButton(String t,int xCorner, int yCorner, int radius) {
        super(xCorner, yCorner, radius);
        text.setText(t,(int)(super.getInRadius()/2));
        text.setBounds(0,(super.getInRadius() - (super.getInRadius()/4)),super.getOutRadius()*2+(gap*2),(super.getInRadius()/2));
        text.setOpaque(false);
        text.setBackground(Color.red);
        text.setForeground(GameFrame.MainColor);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        this.isClickable = true;
        this.add(text);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int Ba = (int)(super.getInRadius()/2);
        int Bb = (int)(super.getOutRadius()/2);
        if(select){
            Game.fx1.play();
            g.setColor(GameFrame.SecondColor);
            for(int i=0;i<4;i++){
                int x1 = ((i%2==0)? 0:(this.getWidth()-Bb));
                int y1 = ((i>=2)? 0:(this.getHeight()-gap));
                
                int x2 = ((i%2==0)? 0:(this.getWidth()-gap));
                int y2 = ((i>=2)? (this.getHeight()-gap-Ba):gap);
                g.fillRect(x1,y1,Bb,gap);
                g.fillRect(x2,y2,gap,Ba);
            }
            for(int j=0;j<4;j++){
                int x1 = ((j%2==0)? 0:(this.getWidth()-Bb));
                int y1 = ((j>=2)? 0: (this.getHeight()-gap));
                g.fillRect(x1,y1,Bb,gap);
            }

        }
    }
    
}

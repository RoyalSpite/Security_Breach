package security_breach.assets;

import security_breach.Panel.GameFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class JHexPanel extends JComponent implements MouseListener{

    protected final Point center;
    protected Polygon[] Hex;
    protected GameLabel text = new GameLabel();
    public boolean select;
    public boolean isClickable  = true;
    private int Ax;
    private int Ay;
    private final int outRadius;
    private final int inRadius;
    protected final int gap;
    private final int border;
    
    public JHexPanel(int xCorner,int yCorner,int radius){
        Hex = new Polygon[4];
        outRadius = radius;
        inRadius = (int)((Math.sqrt(3.0)/2.0)*outRadius);
        gap = (int)(outRadius*10/100.0);
        border = (int)(outRadius*2/100.0);
        center = new Point(outRadius+(gap),inRadius+(gap));
        this.setOpaque(false);
        this.setBounds(xCorner,yCorner,outRadius*2+(gap*2),inRadius*2+(gap*2));
        for(int i=0;i<4;i++){
            Hex[i] = new Polygon();
            for(int j=1;j<=6;j++){
                Ax = (int) (center.x + ((i==0)? (outRadius):(outRadius - ((i-1)*gap)) - border)
                            * Math.cos(j * 2 * Math.PI / 6));
                Ay = (int) (center.y + ((i==0)? (outRadius):(outRadius - ((i-1)*gap)) - border)
                            * Math.sin(j * 2 * Math.PI / 6));

                Hex[i].addPoint(Ax,Ay);
            }
        }
        this.addMouseListener(this);

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(isClickable){
            
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.isClickable){
           select = true; 
        }
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.isClickable){
           select = false;
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        
        for(int i=0;i<4;i++){
            if(i==0) g.setColor(GameFrame.SecondColor);
            else g.setColor(((i%2)-1==0)? GameFrame.SecondColor : GameFrame.MainColor);
            g.fillPolygon(Hex[i]);
        }
        
    }

    public int getOutRadius() {
        return outRadius;
    }

    public int getInRadius() {
        return inRadius;
    }

    public Point getCenter() {
        return center;
    }
    
}

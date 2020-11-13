package security_breach.Node;

import security_breach.Panel.GameFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import security_breach.Game;
import security_breach.assets.GameLabel;
import security_breach.assets.JHexPanel;

public class NodeButton extends JHexPanel{

    private GameLabel CoreLabel = new GameLabel();
    private GameLabel Reference = new GameLabel();
    private Point center;
    int status;
    int phasing;
    public boolean isInfected;
    public boolean scan_complete;
    public int scanTime;
    public int cleanTime;
    public int scanprogress;
    private char letter;
    private int num;
    private boolean onButton;
    
    public NodeButton(int n,char t, int xCenter, int yCenter, int radius) {
        super(xCenter-(radius+(radius*10/100)),yCenter-((int)((Math.sqrt(3.0)/2.0)*radius)+(radius*10/100)), radius);
        center = new Point(xCenter,yCenter);
        CoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CoreLabel.setFont(new Font("Arial",Font.PLAIN,(super.getOutRadius()/2)));
        CoreLabel.setBounds(0,(super.getInRadius()/2),super.getWidth(),((super.getOutRadius()/2)));
        CoreLabel.setFG();
        CoreLabel.setForeground(GameFrame.MainColor);
        CoreLabel.setText(""+t);
        
        this.num = n;
        this.letter = t;
        
        Reference.setHorizontalAlignment(SwingConstants.CENTER);
        Reference.setBounds(0,super.getCenter().y+(super.getInRadius()/7),super.getWidth(),(super.getOutRadius()/3));
        Reference.setFG();
        Reference.setForeground(GameFrame.MainColor);
        
        this.add(CoreLabel);
        this.add(Reference);
    }
    
    public Point getCenter(){
        return this.center;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch(phasing){
            case 1:{
                for(int i=1;i<Hex.length;i++){
                    if(i==1) g.setColor(Color.WHITE);
                    else g.setColor((i==2)? (GameFrame.MainColor):(GameFrame.SecondColor));
                    this.CoreLabel.setForeground(GameFrame.MainColor);
                    this.Reference.setForeground(GameFrame.MainColor);
                    g.fillPolygon(Hex[i]);
                }
                break;
            }
            case 2:{
                for(int i=1;i<Hex.length;i++){
                if(i==1) g.setColor(GameFrame.SecondColor);
                    else g.setColor((i==2)? (Color.WHITE):(GameFrame.MainColor));
                    this.CoreLabel.setForeground(GameFrame.SecondColor);
                    this.Reference.setForeground(GameFrame.SecondColor);
                    g.fillPolygon(Hex[i]);
                }
                break;
            }
            case 3:{
                for(int i=1;i<Hex.length;i++){
                    if(i==1) g.setColor((this.isInfected && (this.scanprogress%2==1))? (Color.RED) : (GameFrame.MainColor));
                    else if(i==2) g.setColor(GameFrame.SecondColor);
                    else g.setColor((this.isInfected && (this.scanprogress%2==1))? (Color.RED):(Color.WHITE));
                    this.CoreLabel.setText(((this.isInfected && (this.scanprogress%2==1))? (Game.omega):(this.letter))+"");
                    this.CoreLabel.setForeground(GameFrame.SecondColor);
                    this.Reference.setForeground(GameFrame.SecondColor);
                    g.fillPolygon(Hex[i]);
                }
                break;
            }
            default:{
                super.paintComponent(g);
                this.CoreLabel.setText(this.letter+"");
                this.CoreLabel.setForeground(GameFrame.MainColor);
                this.Reference.setForeground(GameFrame.MainColor);
                break;
            }
            
        }
        
        if(this.onButton==true){
            for(int i=1;i<Hex.length;i++){
                if(i==1) g.setColor(Color.WHITE);
                else g.setColor((i==2)? (GameFrame.MainColor):(GameFrame.SecondColor));
                this.CoreLabel.setForeground(GameFrame.MainColor);
                this.Reference.setForeground(GameFrame.MainColor);
                g.fillPolygon(Hex[i]);
            }
        }
        
        
        if(status==1 && (Game.currentGame==this.num)) this.scan_check();
        
        if(status==2 && (Game.currentGame==this.num)) this.clean_check();
        
    }

    public void setReference(int str) {
        this.Reference.setText(""+((str>=100)? (str):("0"+str)),(int)(super.getOutRadius()/2.5));
    }
    
    public int getReference(){
        return Integer.parseInt(this.Reference.getText());
    }
       
    public void setAction(int s){
        this.status = s;
        this.phasing++;
        repaint();
    }

    public void clean_check(){
        if(this.phasing == 4){
            this.scanprogress++;
            this.phasing = 0;
            if(scanprogress == this.cleanTime){
                this.status = 0;
                scanprogress = 0;
                this.scan_complete = true;
            }
        }
    }
    
    public void scan_check(){
        if(this.phasing == 4){
            this.scanprogress++;
            this.phasing = 0;
            if(scanprogress == this.scanTime){
                this.status = 0;
                scanprogress = 0;
                this.scan_complete = true;
            }
        }  
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.isClickable==true){
            Game.fx1.play();
            onButton = true;
            repaint();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if(this.onButton==true){
            onButton = false;
            repaint();
        }
    }
}
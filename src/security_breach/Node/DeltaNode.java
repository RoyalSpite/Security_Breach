package security_breach.Node;

import security_breach.Screen.NodePanel;
import java.awt.Graphics;
import security_breach.Game;

public class DeltaNode extends dataNode{
    
    private int eye;
    
    public DeltaNode(){
        super('\u03b4',4);
        Nodes[0] = new NodeButton(4,this.NodeLetter
                ,(NodePanel.PanelWidth/2)
                ,(NodePanel.PanelHeight/2)
                ,super.outRadius);
        Nodes[0].scanTime = 2;
        Nodes[0].cleanTime = 5;
        
        this.Name = "DELTA";
        
        for(int i=1;i<Nodes.length;i++){
            int xx;
            int yy;    
            int ry = (NodePanel.PanelHeight/2)-(super.inRadius+15);
            if(i==5 || i==6) xx = (NodePanel.PanelWidth/4);
            else if(i==2 || i==3) xx = (NodePanel.PanelWidth/2)+(NodePanel.PanelWidth/4);
            else xx = (NodePanel.PanelWidth/2);
            
            if(i==2 || i==6) yy = (NodePanel.PanelHeight/4)+(super.inRadius/2);
            else if(i==3 || i==5) yy = (NodePanel.PanelHeight/2+NodePanel.PanelHeight/4)-(super.inRadius/2);
            else yy = (NodePanel.PanelHeight/2)+(i==1? (-ry):ry);
            
            Nodes[i] = new NodeButton(4,this.NodeLetter,xx,yy,super.outRadius);
            Nodes[i].scanTime = 3;
            Nodes[i].cleanTime = 5;
        }
        this.setRef();
        this.setAllclickable(false);
    }

    @Override
    public void scan() {
        super.scan();
        if(Nodes[0].scan_complete==false){
            Nodes[0].setAction(1);
            super.Node_scan_check(0);
        }
        else{
            if(Nodes[1].scan_complete==false){
                for(int i=1;i<Nodes.length;i+=2){
                    Nodes[i].setAction(1);
                    super.Node_scan_check(i);
                }
            }
            else for(int i=2;i<Nodes.length;i+=2){
                Nodes[i].setAction(1);
                super.Node_scan_check(i);
            }
        }
    }
    
    @Override
    public void setRef() {
        
        while(eye <= 100) eye = Game.rand.nextInt(500);
        
        ref[0] = eye;
        Nodes[0].setReference(ref[0]);
        
        for(int i=1;i<=5;i+=2){
            ref[i] = eye - (15+((i-1)*2));
            Nodes[i].setReference(ref[i]);
        }
        
        for(int i=2;i<=6;i+=2){
            if(i==6) ref[i] = (ref[5]/2) + (ref[1]/2);
            else ref[i] = (ref[i-1]/2) + (ref[i+1]/2);
            ref[i]+=eye;
            Nodes[i].setReference(ref[i]);
        }
         
    }

    @Override
    public void paintComponent(Graphics g) {
        int i1;
        int i2;
        for(int i=1;i<Nodes.length;i++){
            if(i%2==1){
                g.drawLine(Nodes[0].getCenter().x,Nodes[0].getCenter().y,
                    Nodes[i].getCenter().x,Nodes[i].getCenter().y);
            }
            else{
                i1 = i-1;
                if(i==6) i2 = 1;
                else  i2 = i+1;
                g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,
                    Nodes[i1].getCenter().x,Nodes[i1].getCenter().y);
                g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,
                    Nodes[i2].getCenter().x,Nodes[i2].getCenter().y);
            }
        }
    }
    
}

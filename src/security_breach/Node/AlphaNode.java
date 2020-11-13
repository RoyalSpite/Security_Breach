package security_breach.Node;

import security_breach.Screen.NodePanel;
import java.awt.Graphics;
import security_breach.Game;
import static security_breach.Screen.NodePanel.PanelHeight;

public class AlphaNode extends dataNode{

    private int center;
    private int subtractor;
    
    public AlphaNode() {
        super('\u03b1',1);
        Nodes[0] = new NodeButton(1,this.NodeLetter
                ,(NodePanel.PanelWidth/2)
                ,(NodePanel.PanelHeight/2)
                ,super.outRadius);
        Nodes[0].scanTime = 2;
        Nodes[0].cleanTime = 3;
        
        this.Name = "ALPHA";
        
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
            
            Nodes[i] = new NodeButton(1,this.NodeLetter,xx,yy,super.outRadius);
            Nodes[i].cleanTime = 7;
            Nodes[i].scanTime = 12;
        }
        this.setRef();
        this.setAllclickable(false);
        this.recover_time = (13*2)+2;
    }

    @Override
    public void scan(){
        if(Nodes[0].scan_complete==false){
            Nodes[0].setAction(1);
            super.Node_scan_check(0);
        }
        else{
            for(int i=1;i<Nodes.length;i++){
                Nodes[i].setAction(1);
                super.Node_scan_check(i);
            }
        }
        super.scan();
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        for(int i=1;i<Nodes.length;i++){
            if(Nodes[0].isVisible() && Nodes[i].isVisible()){
                g.drawLine(Nodes[0].getCenter().x
                    ,Nodes[0].getCenter().y
                    ,Nodes[i].getCenter().x,Nodes[i].getCenter().y);
            }
        }
         
    }
    
    @Override
    public void setRef(){
        while(center < 100){
            center = Game.rand.nextInt(300);
        }
        while(subtractor == 0){
            subtractor = Game.rand.nextInt(20);
        }
        Nodes[0].setReference(center);
        ref[0] = center;
        for(int i=1;i<super.NodeNumber;i++){
            ref[i] = Nodes[i-1].getReference()-this.subtractor;
            Nodes[i].setReference(ref[i]);
        }
        
    }
    
}
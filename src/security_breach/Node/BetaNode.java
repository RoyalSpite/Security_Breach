package security_breach.Node;

import security_breach.Screen.NodePanel;
import java.awt.Graphics;
import security_breach.Game;

public class BetaNode extends dataNode{

    private int begin;
    private int end;
    private int ref_begin;
    
    public BetaNode() {
        super('\u03b2',2);
        this.Name = "BETA";
        this.recover_time = (2*7);
        
        for(int i=0;i<Nodes.length;i++){
            int xx;
            int yy;    
            int rh = (NodePanel.PanelHeight/2)-(super.inRadius+15);
            xx = (int) ((NodePanel.PanelWidth/2) + rh
                            * Math.cos((i+1) * 2 * Math.PI / 7));
            yy = (int) ((NodePanel.PanelHeight/2) + rh
                            * Math.sin((i+1) * 2 * Math.PI / 7));
                
            Nodes[i] = new NodeButton(2,this.NodeLetter,xx,yy,super.outRadius);
            Nodes[i].scanTime = 2;
        }
        this.setRef();
                
        int lc = (((begin-1)<0)? (Nodes.length):(begin))-1;
        
        int rc = (((begin+1)==(Nodes.length-1))? (-1):(begin))+1;
        
        Nodes[this.begin].cleanTime = 2;
        for(int i=0;i<((Nodes.length-1)/2);i++){
            Nodes[lc].cleanTime = 2*((i+1)+1);
            Nodes[rc].cleanTime = 2*((i+1)+1);
            
            lc--; if(lc<0) lc = Nodes.length-1;
            
            rc++; if(rc==Nodes.length) rc = 0;
               
        }
        this.setAllclickable(false);
        this.recover_time = 2*7;
    }
    
    @Override
    public void scan() {
        super.scan();
        for(int i=begin,k=0;k<super.NodeNumber;i++,k++){
            if(Nodes[i].scan_complete==false){
                Nodes[i].setAction(1);
                super.Node_scan_check(i);
                break;
            }
            if(i==(super.NodeNumber-1)) i = -1;
        }
    }
    
    @Override
    public void setRef() {
        begin = Game.rand.nextInt(super.NodeNumber-1);
        end = ((begin==0)? (super.NodeNumber-1):(begin-1));
        
        while(ref_begin <= 120){
            ref_begin = Game.rand.nextInt(200);
        }
        
        for(int i=begin,k=0;k<super.NodeNumber;i++,k++){
            ref[i] = ref_begin - (k*10);
            Nodes[i].setReference(ref[i]);
            if(i==(super.NodeNumber-1)) i = -1;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i=0;i<(Nodes.length);i++){
            if(Nodes[i].isVisible()==false){
                
            }
        }
        
        for(int i=0;i<(Nodes.length);i++){
            if(i==(Nodes.length-1)){
                g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,Nodes[0].getCenter().x,Nodes[0].getCenter().y);
            }
            else{
                g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,Nodes[i+1].getCenter().x,Nodes[i+1].getCenter().y);
            }
        }
    }
    
}

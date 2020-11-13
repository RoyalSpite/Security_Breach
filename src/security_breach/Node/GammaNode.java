package security_breach.Node;

import security_breach.Screen.NodePanel;
import java.awt.Graphics;
import security_breach.Game;

public class GammaNode extends dataNode{

    private int root;
    
    public GammaNode() {
        super('\u03b3',3);
        Nodes[0] = new NodeButton(
                3
                ,this.NodeLetter
                ,(NodePanel.PanelWidth/2)
                ,super.inRadius+15
                ,super.outRadius);
        Nodes[0].scanTime = 2;
        Nodes[0].cleanTime = 3;
        
        this.Name = "GAMMA";
        
        for(int i=1;i<Nodes.length;i++){
            int xx;
            int yy;
            if(i<=2){
                yy = NodePanel.PanelHeight/2-(super.inRadius/4);
                xx = Nodes[0].getCenter().x + (int)(i%2==1? -(super.outRadius*2.5):(super.outRadius*2.5));
            }
            else{
               yy = NodePanel.PanelHeight/2 + NodePanel.PanelHeight/3;
               if(i<=4){
                   xx = Nodes[1].getCenter().x + (int)(i%2==1? -(super.outRadius*1.25):(super.outRadius*1.25));
               }
               else{
                   xx = Nodes[2].getCenter().x + (int)(i%2==1? -(super.outRadius*1.25):(super.outRadius*1.25));
               }
            }
            Nodes[i] = new NodeButton(3,this.NodeLetter,xx,yy,super.outRadius);
            Nodes[i].scanTime = ((i<=2)?  (4):(7));
            Nodes[i].cleanTime = ((i<=2)?  (5):(6));
        }
        this.setRef();
        this.setAllclickable(false);
    }

    @Override
    public void scan() {
        super.scan();
        for(int i=0;i<3;i++){
            if(Nodes[i].scan_complete==false){
                Nodes[i].setAction(1);
                super.Node_scan_check(i);
                break;
            }
            else{
                if(Nodes[(2*i)+1].scan_complete==false){
                    Nodes[(2*i)+1].setAction(1);
                    super.Node_scan_check((2*i)+1);
                    break;
                }
                if(Nodes[(2*i)+2].scan_complete==false){
                    Nodes[(2*i)+2].setAction(1);
                    super.Node_scan_check((2*i)+2);
                    break;
                }
            }   
        }

    }
    
    @Override
    public void setRef() {
        
        for(int i=0;i<super.NodeNumber;i++){
            int num = 0;
            while(num <= 20){
                num = Game.rand.nextInt(350);
            }
            ref[i] = num;
        }
        root = ref[0];
        for(int i=((super.NodeNumber/2)-1);i>=0;i--){
            heapify(ref,super.NodeNumber,i);
        }
 
        for(int i=0;i<super.NodeNumber;i++){
            Nodes[i].setReference(ref[i]);
        }
        
    }
    
    private void heapify(int[] arr, int n, int i) 
    { 
        int largest = i;
        int l = (2*i)+1;
        int r = (2*i)+2;
  
        if((l<n) && (arr[l]>arr[largest])) largest = l; 
        if((r<n) && (arr[r]>arr[largest])) largest = r; 
  
        if(largest != i){ 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 

            heapify(arr, n, largest); 
        } 
    }  

    @Override
    public void paintComponent(Graphics g) {
        for(int i=0;i<3;i++){
            g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,
                    Nodes[(2*i)+1].getCenter().x,Nodes[(2*i)+1].getCenter().y);
            
            g.drawLine(Nodes[i].getCenter().x,Nodes[i].getCenter().y,
                    Nodes[(2*i)+2].getCenter().x,Nodes[(2*i)+2].getCenter().y);
        }
    }
}
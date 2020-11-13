package security_breach.Node;

import java.awt.Graphics;

public abstract class dataNode{
    
    protected String Name;
    protected char NodeLetter;
    protected NodeButton[] Nodes;
    protected final int NodeNumber = 7;
    protected final int outRadius = 70;
    protected final int inRadius = (int)((Math.sqrt(3.0)/2.0)*outRadius);
    protected int[] ref;
    protected int dataNum;
    protected String VirusAtNode;
    protected String VirusAtReference;
    protected int recover_time = 0;
    
    dataNode(char letter,int n){
        this.NodeLetter = letter;
        Nodes = new NodeButton[NodeNumber];
        ref = new int[NodeNumber];
        dataNum = n;
    }
   
    public void scan(){
        for(int i=0;i<Nodes.length;i++){
            if(Nodes[i].isInfected==true){
                this.VirusAtNode = Nodes[i].getName();
                this.VirusAtReference = (Nodes[i].getReference()+"");
                break;
            }
        }
    }
    
    public void clean(int i){
        Nodes[i].setAction(2);
        Nodes[i].clean_check();
    }
    
    public abstract void paintComponent(Graphics g);
    
    public abstract void setRef();

    public char getNodeLetter() {
        return NodeLetter;
    }

    public void setNodeLetter(char NodeLetter) {
        this.NodeLetter = NodeLetter;
    }

    public NodeButton[] getNodes() {
        return Nodes;
    }

    public int[] getRef() {
        return ref;
    }

    public void setRef(int[] ref) {
        this.ref = ref;
    }
    
    protected void Node_scan_check(int index){
        Nodes[index].scan_check();
    }
    
    public boolean scan_complete(){
        for(int i=0;i<Nodes.length;i++){
            if(Nodes[i].scan_complete==false) return false;
        }
        return true;
    }
    
    public void reset(){
        for(int i=0;i<Nodes.length;i++){
            Nodes[i].status = 0;
            Nodes[i].phasing = 0;
            Nodes[i].repaint();
            Nodes[i].scanprogress = 0;
            Nodes[i].scan_complete = false;
        }
    }
    
    public void checking(){
        for(int i=0;i<Nodes.length;i++){
            System.out.println(NodeLetter+" "+i+" sc : " +Nodes[i].phasing+" > "+Nodes[i].scanprogress);
        }
        System.out.println();
    }

    public boolean Node_is_Infected(){
        for(int i=0;i<Nodes.length;i++){
            if(Nodes[i].isInfected==true) return true;
        }
        return false;
    }
    
    public void setAllclickable(boolean c){
        for(int i=0;i<Nodes.length;i++) Nodes[i].isClickable = c;
    }

    public String getName() {
        return Name;
    }

    public String getVirusAtNode() {
        return VirusAtNode;
    }

    public void setVirusAtNode(String VirusAtNode) {
        this.VirusAtNode = VirusAtNode;
    }

    public String getVirusAtReference() {
        return VirusAtReference;
    }

    public void setVirusAtReference(String VirusAtReference) {
        this.VirusAtReference = VirusAtReference;
    }
    
}

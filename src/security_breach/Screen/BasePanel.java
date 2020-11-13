package security_breach.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import security_breach.Game;
import security_breach.Node.dataNode;
import security_breach.Panel.GameFrame;
import security_breach.Panel.PlayScreen;
import security_breach.assets.GameLabel;

public class BasePanel extends GameScreen{
    
    private int HP = 100;
    private GameScreen control;
    private GameLabel Label_scan;
    private GameLabel Label_clean;
    private GameLabel NodeHPText;
    private GameLabel Label_back;
    public static final int BaseWidth = MainFrame.MainWidth-10;
    public static final int BaseHeight = MainFrame.MainHeight-60;
    private dataNode dn;
    private int sc;
    private int clean_index = -1;
    private int nodeC = 0;

    public BasePanel(dataNode d){
        
        dn = d;
        
        control = new GameScreen(){

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(GameFrame.SecondColor);
                g.fillRect(0,30,15,BaseHeight-60);
            }
            
        };
        control.setBounds(BaseWidth-300,0,400,BaseHeight);
        
        Label_scan = new GameLabel("SCAN",50);
        Label_scan.setBounds(20,150,control.getWidth()-25,55);
        Label_scan.addMouseListener(this);
        
        Label_clean = new GameLabel("CLEAN",50);
        Label_clean.setBounds(20,Label_scan.getY()+5+60,control.getWidth()-25,55);
        Label_clean.addMouseListener(this);
        
        NodeHPText = new GameLabel();
        NodeHPText.setText("FIREWALL : "+this.HP,55);
        NodeHPText.setBounds(20,25,control.getWidth(),60);
        
        Label_back = new GameLabel("BACK",50);
        Label_back.setBounds(20,BaseHeight-55-30,control.getWidth()-25,55);
        Label_back.addMouseListener(this);
        
        control.add(Label_back);
        control.add(Label_scan);
        control.add(Label_clean);
        control.add(NodeHPText);
       
        this.add(control);
        this.add(new NodePanel(dn));
        
        dn.setAllclickable(false);
        for(int i=0;i<dn.getNodes().length;i++){
            dn.getNodes()[i].addMouseListener(this);
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        boolean select_for_clean = false;
        if(e.getButton()==MouseEvent.BUTTON1){
            
            for(int i=0;i<dn.getNodes().length;i++){
                if(e.getSource()==dn.getNodes()[i] && (dn.getNodes()[i].isClickable==true)){
                    select_for_clean = false;
                    this.clean_index = i;
                    
                    dn.setAllclickable(false);
                    PlayScreen.clean_begin.play();
                    Label_scan.setEnable(false);
                    Label_clean.setEnable(false);
                    
                    Label_clean.setAction(true);
                    
                    sc = 0;
                }
            }
            
            if(e.getSource()==Label_back){
                dn.setAllclickable(false);
                Game.subGame.showScreen(0);    
            }
            else if(e.getSource()==Label_scan && (Label_scan.isEnable()==true)){
                Label_scan.setAction(!(Label_scan.isAction()));
                if(Label_scan.isAction()==true){
                    Label_clean.setEnable(false);
                    
                    dn.setAllclickable(false);
                    PlayScreen.scan_begin.play();
                    sc = 0;
                }
                else{
                    dn.reset();
                    Label_scan.setEnable(false);
                    Label_clean.setEnable(true);
                    Label_scan.setCooldown(10);
                }
            }
            else if(e.getSource()==Label_clean && (Label_clean.isEnable()==true)){
                select_for_clean = !(select_for_clean);

                if(select_for_clean==true){
                    PlayScreen.clean_on.play();
                    dn.setAllclickable(true);
                }  
                else{
                    PlayScreen.clean_off.play();
                    dn.setAllclickable(false);
                }
            }
        }
    }
    
    
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if(e.getSource()==Label_scan && (Label_scan.isEnable()==true)){
            Label_scan.setText(Label_scan.isAction()? ("CANCEL"):("SCAN"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if(e.getSource()==Label_scan && (Label_scan.isEnable()==true)){
            Label_scan.setText(Label_scan.isAction()? ("SCANNING"):("SCAN"));
        }
    }
    
    public void action(){
        this.nodeC++;
        this.NodeHPText.setText("FIREWALL : "+this.HP);
        int ic = 0;
        if(this.nodeC%100 == 0){
            if(dn.Node_is_Infected()==true) HP-=3;
            for(int i=0;i<dn.getNodes().length;i++){
                if(dn.getNodes()[i].getReference()!=dn.getRef()[i]){
                    ic++;
                }
                if(ic>=2) HP-=1;
            }
        }
        this.scan();
        this.clean();
    }
    
    private void scan(){
        if(Label_scan.isAction()==true){
            if(dn.scan_complete()==true){
                
                Label_scan.setAction(false);
                
                Label_clean.setEnable(true);
                Label_scan.setEnable(false);
                
                if(dn.Node_is_Infected()==true) PlayScreen.virusdetected.play();
                else PlayScreen.complete.play();
                Label_scan.setCooldown(10);
            }
            else{
                sc++;
                if(sc%4==0){
                    dn.scan();
                }
            }
        }
        else
        {
            if(Label_scan.isEnable()==false && (Label_scan.cooldown > 0)){
                if(Label_scan.cooldown_count==1){
                    Label_scan.setText("SCAN");
                    Label_scan.setEnable(true);
                    dn.reset();
                }
                else{
                    if(dn.scan_complete()){
                        if(dn.Node_is_Infected()==true){
                            if(Label_scan.cooldown > 6){
                                PlayScreen.InterruptMessage = true;
                                PlayScreen.status.setOpaque(true);
                                PlayScreen.status.setBackground(Color.red);
                                PlayScreen.status.setText("VIRUS DETECTED AT "+dn.getName()+" "+dn.getVirusAtReference(),40);
                            }
                            else{
                                PlayScreen.InterruptMessage = false;
                                PlayScreen.status.setFG();
                                
                            }
                        }
                        else{
                            if(Label_scan.cooldown > 6){
                                PlayScreen.InterruptMessage = true;
                                PlayScreen.status.setText(dn.getName()+" SCAN COMPLETE",40);
                            }
                            else{
                                PlayScreen.InterruptMessage = false;
                            }
                        }
                    }
                    if(Label_scan.cooldown > 0) Label_scan.cooldown_count--;
                    if(Label_scan.cooldown_count%10==0) Label_scan.cooldown--;
                    Label_scan.setText("SCAN CD : "+Label_scan.cooldown);
                }
            }
        }
    }
    
    private void clean(){
        if(Label_clean.isAction()==true){
            if(dn.getNodes()[this.clean_index].scan_complete==true){
                
                dn.getNodes()[this.clean_index].isInfected = false;
                dn.getNodes()[this.clean_index].setReference(dn.getRef()[this.clean_index]);
                dn.getNodes()[this.clean_index].scan_complete = false;
                
                Label_clean.setAction(false);
                
                Label_scan.setEnable(true);
                Label_clean.setEnable(false);

                PlayScreen.complete.play();
                
                Label_clean.setCooldown(dn.getNodes()[this.clean_index].cleanTime);
            }
            else{
                sc++;
                if(sc%4==0){
                    dn.clean(clean_index);
                }
            }
        }
        else
        {
            if(Label_clean.isEnable()==false && (Label_clean.cooldown > 0)){
                
                if(Label_clean.cooldown_count==1){
                    dn.reset();
                    Label_clean.setText("CLEAN");
                    Label_clean.setEnable(true);
                }
                else{
                    if(Label_clean.cooldown > 0) Label_clean.cooldown_count--;
                    if(Label_clean.cooldown_count%10==0) Label_clean.cooldown--;
                    Label_clean.setText("CLEAN CD : "+Label_clean.cooldown);
                }
                
            }
        }
    }

    public dataNode getDataNode() {
        return dn;
    }

    public int getHP() {
        return HP;
    }
    
}
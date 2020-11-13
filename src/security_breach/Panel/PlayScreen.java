package security_breach.Panel;

import java.awt.Color;
import security_breach.Screen.MainFrame;
import security_breach.Screen.GameScreen;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import security_breach.Game;
import security_breach.Node.AlphaNode;
import security_breach.Node.BetaNode;
import security_breach.Node.DeltaNode;
import security_breach.Node.GammaNode;
import security_breach.Screen.BasePanel;
import security_breach.assets.GameLabel;
import security_breach.assets.audio;

public class PlayScreen extends GameScreen{
    
    private final String[] state = {"Main","Alpha","Beta","Gamma","Delta"};
    private GameLabel fireWall;
    private GameLabel time;
    public GameLabel pause;
    public static GameLabel status = new GameLabel();
    private MainFrame playgameframe;
    private int timecheck;
    private int timetick;
    public static final audio scan_begin = new audio("scan_begin.wav");
    public static final audio complete = new audio("complete.wav");
    public static final audio virusdetected = new audio("virusdetected.wav");
    public static final audio clean_on = new audio("clean_on.wav");
    public static final audio clean_off = new audio("clean_off.wav");
    public static final audio clean_begin = new audio("clean_begin.wav");
    public static final audio gameover = new audio("mission_failed.wav");
    public static final audio gamewin = new audio("mission_passed.wav");
    private int firewall_HP;
    public static boolean InterruptMessage;
    
    public PlayScreen(){
        
        timer = new Timer(100,this);
        this.music = new audio("forgetmenot_maingame.wav");
        
        fireWall = new GameLabel();
        fireWall.setText("FIREWALL : ",40);
        fireWall.setBounds(5,5,240,45);
        
        time = new GameLabel();
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setText("TIME : 0",40);
        time.setBounds(this.getWidth()/2+150,5,150,45);
        
        pause = new GameLabel("P",40);
        pause.setHorizontalAlignment(SwingConstants.CENTER);
        pause.setBounds(this.getWidth()-50,5,45,45);
        pause.addMouseListener(this);
        
        status.setText("STATUS : ",40);
        status.setBounds((this.getWidth()/2)-350,5,500,45);        
        
        playgameframe = new MainFrame(state);
        
        playgameframe.setBounds(5,55,MainFrame.MainWidth-10,MainFrame.MainHeight-60);
        
        playgameframe.add(new MainCore(),"Main");
        playgameframe.add(new BasePanel(new AlphaNode()),"Alpha");
        playgameframe.add(new BasePanel(new BetaNode()),"Beta");
        playgameframe.add(new BasePanel(new GammaNode()),"Gamma");
        playgameframe.add(new BasePanel(new DeltaNode()),"Delta");
        
        this.add(time);
        this.add(pause);
        this.add(fireWall);
        this.add(status);
        this.add(playgameframe);
        
    }
    
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(InterruptMessage==false) status.setText("STATUS : " +((firewall_HP > 68)? ("NORMAL"):("DANGER")));
        timecheck++;
        firewall_HP = 0;
        for(int i=1;i<5;i++){
            ((BasePanel)playgameframe.getComponent(i)).action();   
            this.firewall_HP += ((BasePanel)playgameframe.getComponent(i)).getHP();
        }
        this.firewall_HP = firewall_HP/4;
        
        if(timecheck%10==0){
            if(this.firewall_HP < 60){
                gameover.play();
                Game.RealGame.getScreen().showScreen(4);
            }
            if(timetick >= 120){
                gamewin.play();
                Game.RealGame.getScreen().showScreen(5);
            }
            if(timetick>20){
                int Node_attack;
                if(Game.PrimeNumberChecker(timetick)){
                    boolean PlantVirus = Game.rand.nextBoolean();
                    Node_attack = Game.rand.nextInt(4)+1;
                    if(PlantVirus==true){
                        int cc = 0;
                        while(true){
                            
                            if(((BasePanel)playgameframe.getComponent(Node_attack)).getDataNode().Node_is_Infected()==false){
                                int PlantVirusAt = Game.rand.nextInt(7);
                                ((BasePanel)playgameframe.getComponent(Node_attack)).getDataNode().getNodes()[PlantVirusAt].isInfected = true;
                                break;
                            }
                            else cc++;
                            
                            if(cc==3) break;
                            
                        }
                    }
                    else
                    {
                        int ScrambleAt = Game.rand.nextInt(7);
                        int cc = 0;
                        while(true){
                            int NewRef = Game.rand.nextInt(501);
                            if(((BasePanel)playgameframe.getComponent(Node_attack)).getDataNode().getNodes()[ScrambleAt].getReference()!=NewRef){
                                ((BasePanel)playgameframe.getComponent(Node_attack)).getDataNode().getNodes()[ScrambleAt].setReference(NewRef);
                                break;
                            }
                            else cc++
                               ;
                            if(cc==3) break;
                        }
                    }
                }
            }
            
            timetick++;
        }
        fireWall.setText("FIREWALL : "+(firewall_HP));
        time.setText("TIME : "+(timetick));
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getSource()==pause && e.getButton()==MouseEvent.BUTTON1){
            System.out.println("PAUSE");
            this.music.stop();
            this.timer.stop();
            Game.RealGame.getScreen().showScreen(3);
        }
    }

    public MainFrame getPlaygameframe() {
        return playgameframe;
    }
    
}

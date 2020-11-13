package security_breach.Panel;

import security_breach.Panel.GameFrame;
import security_breach.Screen.MainFrame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.util.Random;
import security_breach.Game;
import security_breach.Screen.GameScreen;
import security_breach.assets.GameLabel;
import security_breach.assets.audio;

public final class MainMenu extends GameScreen{

    private GameLabel Label_play;
    private GameLabel Label_exit;
    private GameLabel Label_audio;
    private GameLabel Label_title1;
    private GameLabel Label_title2;
    private boolean isBlink;
    private boolean isSelected;
    Random rand = new Random();
    
    public MainMenu(){
        timer = new Timer(500,this);
        isBlink = true;
        Game.setAudioOn(true);
        Game.setRunning(false);
        Label_play = new GameLabel("PLAY",60);
        Label_play.setBounds((MainFrame.MainWidth/2)+100+40,(MainFrame.MainHeight/4),250,80);
        Label_play.addMouseListener(this);
        
        Label_exit = new GameLabel("EXIT",60);
        Label_exit.setBounds((MainFrame.MainWidth/2)+100+40,(MainFrame.MainHeight/2)+(MainFrame.MainHeight/4)-80,250,80);
        Label_exit.addMouseListener(this);
        
        Label_audio = new GameLabel("AUDIO : ON",60);
        Label_audio.setBounds((MainFrame.MainWidth/2)+100+40,(MainFrame.MainHeight/2)-40,250,80);
        Label_audio.addMouseListener(this);
        
        Label_title1 = new GameLabel();
        Label_title1.setText("SECURITY",100);
        Label_title1.setBounds(200,MainFrame.MainHeight/2-100,400,100);
        
        Label_title2 = new GameLabel();
        Label_title2.setText("BREACH",100);
        Label_title2.setBounds(200,MainFrame.MainHeight/2,272,100);
        
        this.music = new audio("inspired_mainmenu.wav");
        
        this.add(Label_title1);
        this.add(Label_title2);
        this.add(Label_play);
        this.add(Label_exit);
        this.add(Label_audio);
        
        music.playloop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(e.getSource()==Label_play){
                Game.RealGame.getScreen().showScreen(1);
            }
            else if(e.getSource()==Label_exit){
                System.exit(0);
            }
            else if(e.getSource()==Label_audio){
                Game.setAudioOn(!(Game.isAudioOn()));
                Label_audio.setText("AUDIO : "+(Game.isAudioOn()? ("ON"):("OFF")));
                if(Game.isAudioOn()){
                    if(music.isPlaying()==false) music.playloop();
                }
                else{
                    if(music.isPlaying()==true) 
                        music.stop();
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        isBlink = !isBlink;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isBlink){
            g.setColor(GameFrame.MainColor);
        }
        else g.setColor(GameFrame.SecondColor);

        g.fillRect(472,MainFrame.MainHeight/2+80,30,10);
        
        g.setColor(GameFrame.SecondColor);
        g.fillRect((MainFrame.MainWidth/2)+100,50,20,MainFrame.MainHeight-100);
        
    }

}
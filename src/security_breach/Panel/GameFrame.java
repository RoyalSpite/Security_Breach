package security_breach.Panel;

import security_breach.Screen.MainFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import security_breach.Game;

public class GameFrame extends Canvas{
    
    public static final String[] state = {"MainMenu","Loading","Game","Pause","GameOver","GameWin"};
    public static final Color MainColor = new Color(0,239,255);
    public static final Color SecondColor = new Color(0,60,64);
    public static final int FrameWidth = 1300;
    public static final int FrameHeight = 720;
    public static int current;
    public static JFrame frame;
    private final MainFrame screen = new MainFrame(state);
    public GameFrame(String str){
        frame = new JFrame();
        frame.setTitle(str);
        frame.setSize(FrameWidth,FrameHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setBackground(MainColor);
        this.setBounds(0,0,FrameWidth,FrameHeight);

        screen.add(new MainMenu(),"MainMenu");
        screen.add(new LoadingScreen(),"Loading");
        
        frame.add(screen);
        frame.add(this);
        
        current = 0;
        screen.showScreen(current);
        
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(SecondColor);
        g.fillRect(22,22,1250,650);
        
        g.setColor(Color.BLACK);
        g.fillRect(13,13,1250,650);
    }

    public MainFrame getScreen() {
        return screen;
    }

    public void init(){
        this.screen.add(new PlayScreen(),"Game");
        this.screen.add(new PauseScreen(),"Pause");
        this.screen.add(new GameEnd("MISSION  FAILED :("),"GameOver");
        this.screen.add(new GameEnd("MISSION  ACCOMPLISHED ;)"),"GameWin");
        
        Game.subGame = ((PlayScreen)screen.getComponent(2)).getPlaygameframe();
    }
    
    public void deinit(){
        screen.remove(5);
        screen.remove(4);
        screen.remove(3);
        screen.remove(2);
    }

}
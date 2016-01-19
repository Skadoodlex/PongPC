package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//extend jpanel to overwrite paint method 
public class Game extends JPanel {

    // game Constructor
    public Game() {
        KeyListener listener = new KeyListener() {

            @Override
            //Default constructor
            public void keyTyped(KeyEvent e) {
            }

            @Override
            //for testing 
            //using e.getKeyCode() to obtain key pressed
            //.getkeyText for text associated with the key
            //KeyEvent indicates which key stroke occured 
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed=" + 
                        KeyEvent.getKeyText(e.getKeyCode()));
                racquet.keyReleased(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased=" + 
                        KeyEvent.getKeyText(e.getKeyCode()));
                racquet.keyPressed(e);
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }
    //passes this class (Game) as a parameter
    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);
    //initial speed
    int speed = 1;

    //score is speed -1
    private int getScore() {
        return speed - 1;
    }

    //moveBall method moves the ball
    public void moveBall() {
        ball.moveBall();
        racquet.move();
    }
    
    public Image bg;

    @Override
    public void paint(Graphics g) {
        //g2d
        Graphics2D g2d = (Graphics2D) g;
        try {
            //smoother graphics
            bg = ImageIO.read(new File("src/finalproject/bg.jpg"));
        } catch (IOException ex) {
            //extreme cash of exception
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Something went wrong! Check file path.");
        }

        //clears the screen
        super.paint(g);
        
        //draw background
        g2d.drawImage(bg, 0, 0, null);
        //paints the ball and racquet
        ball.paint(g2d);
        racquet.paint(g2d);

        //Score counter: yellow
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
    }

    //game message with score count
    public void gameOver() {
        JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
                "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    //main
    public static void main(String[] args) throws InterruptedException,
            UnsupportedAudioFileException, IOException, 
            LineUnavailableException {
        
        
        //Title screen stuff
        boolean titleScreen = true;
        boolean exit = false;
        TitleScreen z = new TitleScreen();

        //JFrame is the window
        JFrame frame = new JFrame("PongPC");
        frame.add(z);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        //close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (titleScreen) {
            //close frame will exit
            exit = z.closeFrame();
            z.repaint();
            Thread.sleep(1);

            //dispose frame, title screen false
            if (exit) {
                //deletes all memory,resources destroyed 
                frame.dispose();
                titleScreen = false;
            }
        }

        //Game frame stuff
        JFrame frame2 = new JFrame("PongPC");
        //variable x from main class
        Game x = new Game();

        //audio in
        AudioInputStream audioIn = AudioSystem.getAudioInputStream
        (Game.class.getResource("bgm.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        frame2.add(x);
        frame2.setSize(300, 400);
        frame2.setVisible(true);
        frame2.setResizable(false);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //infinite loop calling moveBall method
        while (true) {
            x.moveBall();
            //repaint to see the changes in the position of the ball
            x.repaint();
            //refresh every 10 milisec
            Thread.sleep(10);
            clip.start();
        }
    }
}
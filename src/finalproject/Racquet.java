package finalproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {

    private Game game;

    //unlike Ball, racquet only moves left and right
    int x = 0;
    int xa = 0;

    //Variables for rectangle object for the position of the racquet
    private static final int Y = 330;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 10;

    //initializes the Class(Game) to the variable (this.game)
    public Racquet(Game game) {
        this.game = game;
    }

    //smiliar to ball method, this keeps the racquet in the boarder of the game
    //x is initally 0 which means the racquet will initally be on the left
    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - 120) {
            x = x + xa;
        }
    }

    //g.fillRect creates a 60 by 10 pixel at the bottom left x,y(0,330)
    //only x cordinate can change so the racquet will not move up or down.
    public void paint(Graphics2D g) {
        g.fillRect(x, 330, 120, 10);
        g.setColor(Color.red);
    }

    //if released left, it will continue left.
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -4;
        }
        //if realeased right, it will continue right.
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 4;
        }
    }

    //left key => xa = -4 to left 
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -4;
        }
        //right key => xa = 4 for right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 4;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}
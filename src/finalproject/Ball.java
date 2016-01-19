package finalproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

    private Game game;

    private static final int DIAMETER = 30;

    // x and y cordinates
    int x = 0;
    int y = 0;
    // xa = 1 goes right 
    // ya= 1 goes down 
    // 1 pixel every loop of game loop
    int xa = 1;
    int ya = 1;

    //passes the Class (Game) to a variable (this.game)
    public Ball(Game game) {
        this.game = game;
    }

    public void moveBall() {
        //if the ball goes outside the left border (left border starts at 0)
        if (x + xa < 0) {
            xa = 4; 
        }
        //if x+xa > getWidth - d
        //(when the ball is about to go out of the right border)
        //change the direction to go left 
        if (x + xa > game.getWidth() - DIAMETER) {
            xa = -4; 
        }
        //Similarly for left border
        if (y + ya < 0) {
            ya = 4;
        }
        //if the ball's location is greater than height(i.e. the bottom) - d 
        //do .gameOver method
        if (y + ya > game.getHeight() - DIAMETER) {
            game.gameOver();
        }
        //if collision occours between ball and racquet, 
        //send ball upwards
        if (collision()) {
            ya = -4;
            //The "Racquet" getTopY() method gives us the position in the 
            // "y" axis of the upper part of the racquet, 
            // discounting the DIAMETER(bottom border)
            // gives exact position to put above the racquet when hit. 
            y = game.racquet.getTopY() - DIAMETER;
            game.speed += 1;
        }

        //ball moving, pixels per loop get added up
        x = (x + xa);
        y = (y + ya);
    }

//returns a rectangle type of object, indiciating the position of the racquet
    //used for position of the racquet and this way to detect collsion
    //makes rectangle occupied by the racquet
    //if the rectangle and bounds intersect. 
    private boolean collision() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    //creates the ball 30 by 30 pixels
    public void paint(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.blue);
    }

    //method which returns true when two rectangles occupy the same space
    //so the ball is actually interactive through a square around it.
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class TitleScreen extends JPanel {

    int mouseX, mouseY;

    public TitleScreen() {

        // mouse listener
        MouseListener mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
            }
            @Override
            public void mousePressed(MouseEvent me) {
            }
            @Override
            public void mouseReleased(MouseEvent me) {
            }
            @Override
            public void mouseEntered(MouseEvent me) {
            }
            @Override
            public void mouseExited(MouseEvent me) {
            }
        };
        addMouseListener(mouse);
        setFocusable(true);
    }

    // checks mouse x and y to make sure to you click on the button to start and exits the start jframe
    public boolean closeFrame() {
        boolean exit = false;
        if ((mouseX > 70 && mouseX < 220) && (mouseY > 300 && mouseY < 350)) {
            exit = true;
        }
        return exit;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.fill3DRect(70, 300, 150, 50, true);
        g.setColor(Color.yellow);
        Font fonts = new Font("Comic Sans", Font.BOLD, 36);
        g.setFont(fonts);
        g.drawString("START", 87, 337);
        g.setColor(Color.black);

        fonts = new Font("Arial", Font.PLAIN, 18);
        g.setFont(fonts);
        g.drawString("* L/R keys to move horizontally", 25, 150);
        g.drawString("* Avoid dropping the ball", 25, 190);
        fonts = new Font("Arial", Font.BOLD, 40);
        g.setFont(fonts);
        g.drawString("Instructions:", 25, 100);

    }
}

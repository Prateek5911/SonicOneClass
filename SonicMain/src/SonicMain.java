import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SonicMain extends JPanel implements ActionListener {

    private int xPos, yPos;
    private int dir;
    private final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private ImageIcon sonicUp, sonicDown, sonicLeft, sonicRight;
    private ImageIcon background;
    private Timer sonicTimer;

    public SonicMain() {
        dir = DOWN;
        xPos = 0;
        yPos = 0;
        
        // The images
        sonicDown = new ImageIcon("images/Sonic_down.gif");
        sonicUp = new ImageIcon("images/Sonic_up.gif");
        sonicLeft = new ImageIcon("images/Sonic_left.gif");
        sonicRight = new ImageIcon("images/Sonic_right.gif");
        background = new ImageIcon("images/background.jpg");
        
        JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.setSize(background.getIconWidth(), background.getIconHeight());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        sonicTimer = new Timer(50, this);
        sonicTimer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(background.getImage(), 0, 0, this);
        if (dir == UP) {
            g2.drawImage(sonicUp.getImage(), xPos, yPos, this);
        } else if (dir == DOWN) {
        	
            g2.drawImage(sonicDown.getImage(), xPos, yPos, this);
        } else if (dir == LEFT) {
        	
            g2.drawImage(sonicLeft.getImage(), xPos, yPos, this);
        } else {
        	
            g2.drawImage(sonicRight.getImage(), xPos, yPos, this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sonicTimer) {
            if (dir == UP) {
                yPos -= 10;
                if (yPos <= 0) {
                    dir = LEFT;
                }
            } else if (dir == DOWN) {
                yPos += 10;
                if (yPos + sonicDown.getIconHeight() + 15 >= background.getIconHeight()) {
                    yPos += 40;
                    dir = RIGHT;
                }
            } else if (dir == LEFT) {
                xPos -= 10;
                if (xPos <= 0) {
                    dir = DOWN;
                }
            } else {
                xPos += 10;
                if (xPos + sonicRight.getIconWidth() >= background.getIconWidth()) {
                    xPos -= 10;
                    dir = UP;
                }
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        new SonicMain();
    }
}
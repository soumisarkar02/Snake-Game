package snake;

import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener
{
    public static Snake snake;
    
    public JFrame jframe;
    public RenderPanel renderpanel;
    public Timer timer = new Timer(20,this);
    
    public static final int UP = 0, DOWN = 1,LEFT = 2,RIGHT = 3, SCALE = 10;
    public int tick,direction, score, taillength,time;
    public boolean over,paused;

    public Point head,cherry;
    
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    
    public Random random;
    public Dimension dim;
    
    public Snake()
    {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(805,700);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2,dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderpanel = new RenderPanel());
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame();
    }
    
    public void startGame()
    {
        over = false;
        paused = false;
        score = 0;
        tick = 0;
        time = 0;
        taillength = 0;
        direction = DOWN;
        head = new Point(0,-1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79),random.nextInt(66));
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        renderpanel.repaint();
        
        tick++;
        
        if(tick % 2 == 0 && head != null && over != true && paused != true)
        {
            time++;
            snakeParts.add(new Point(head.x,head.y));
            if(direction == UP && noTailAt(head.x,head.y - 1))
            {
                if(head.y - 1 >= 0)
                    head = new Point(head.x,head.y - 1);
                else
                    over = true;
            }
            if(direction == DOWN && noTailAt(head.x,head.y + 1))
            {
                if(head.y + 1 < 67)
                    head = new Point(head.x,head.y + 1);
                else
                    over = true;
            }
            if(direction == LEFT && noTailAt(head.x - 1,head.y))
            {
                if(head.x - 1 >= 0)
                    head = new Point(head.x - 1,head.y);
                else
                    over = true;
            }
            if(direction == RIGHT && noTailAt(head.x + 1,head.y))
            {
                if(head.x + 1 < 80)
                    head = new Point(head.x + 1,head.y);
                else
                    over = true;
            }
            
            if(snakeParts.size() > taillength)
            {
                snakeParts.remove(0);
            }
            
            if(cherry != null)
            {
                if(head.equals(cherry))
                {
                    score+=10;
                    taillength++;
                    cherry.setLocation(random.nextInt(79),random.nextInt(66));
                }
            }
        }
    }
    
    public static void main(String[] args) 
    {
        snake = new Snake();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i == KeyEvent.VK_LEFT && direction != RIGHT)
            direction = LEFT;
        if(i == KeyEvent.VK_RIGHT && direction != LEFT)
            direction = RIGHT;
        if(i == KeyEvent.VK_UP && direction != DOWN)
            direction = UP;
        if(i == KeyEvent.VK_DOWN && direction != UP)
            direction = DOWN;
        if(i == KeyEvent.VK_SPACE)
            if(over)
            {
                jframe.setVisible(false);
                jframe.dispose();
                snake = new Snake();
            }
            else
                paused = !paused;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean noTailAt(int x, int y) 
    {
        for(Point point : snakeParts)
        {
            if(point.equals(new Point(x,y)))
            {
                return false;
            }
        }
        return true;
    }
}
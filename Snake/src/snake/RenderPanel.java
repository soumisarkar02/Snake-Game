package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Point;
import static java.awt.font.TextAttribute.FONT;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{
    public static Color green = new Color(1666073);
    public static Color brown = new Color(9127187);
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0,0,805,700);
        
        Snake snake = Snake.snake;
        g.setColor(Color.ORANGE);
        for(Point point : snake.snakeParts)
        {
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,Snake.SCALE,Snake.SCALE);
        }
        
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,Snake.SCALE,Snake.SCALE);
        g.setColor(Color.RED);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,Snake.SCALE,Snake.SCALE);
        
        if(snake.over == true)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif",Font.BOLD, 20));
            String string = "Score: " + snake.score;
            g.drawString(string, 365, 260);
            string =  "Length: " + snake.taillength;
            g.drawString(string, 365, 281);
            string =  "Time: " + snake.time/20;
            g.drawString(string, 365, 302);
            g.setColor(Color.RED);
            string = "GAME OVER!!!";
            g.setFont(new Font("SansSerif",Font.BOLD, 32));
            g.drawString(string, 300, 340);
            string = "Press Space Bar To Start a NEW GAME";
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif",Font.BOLD, 20));
            g.drawString(string, 230, 367);
        }
        else
        {
            String string = "Score: " + snake.score + ", Length: " + snake.taillength + ", Time: " + snake.time/20;
            g.setColor(Color.WHITE);
            g.drawString(string, (int)(getWidth() / 2 - string.length() * 2.5f) , 10);
            
            if(snake.paused == true)
            {
                g.setColor(Color.RED);
                string = "GAME PAUSED!!!";
                g.setFont(new Font("SansSerif",Font.BOLD, 32));
                g.drawString(string, 280, 340);
                string = "Press Space Bar To CONTINUE";
                g.setColor(Color.WHITE);
                g.setFont(new Font("SansSerif",Font.BOLD, 20));
                g.drawString(string, 260, 367);
            }
        }
    }
}
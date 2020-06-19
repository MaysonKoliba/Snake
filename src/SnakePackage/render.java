package SnakePackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class render extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Snake snake = Snake.snake;
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,900,800);
		g.setColor(Color.WHITE);
		
		for(Point point : snake.snakeParts) {
			g.fillRect(point.x*Snake.SCALE,point.y*Snake.SCALE,15,15);
		}
		
		g.fillRect(snake.head.x*Snake.SCALE,snake.head.y*Snake.SCALE,15,15);
		
		g.setColor(Color.RED);
		g.fillOval(snake.cherry.x*Snake.SCALE,snake.cherry.y*Snake.SCALE,15,15);
		
		if(snake.gameOver == true) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("GAME OVER!", 400, 20);
			g.drawString("Score: " + snake.score, 425, 40);
		}
		else if(snake.pause == true) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("PAUSED", 400, 20);
			g.drawString("Score: " + snake.score, 402, 40);
		}
		else {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,16));
			g.drawString("Score: " + snake.score, 400, 20);
		}
	}
}
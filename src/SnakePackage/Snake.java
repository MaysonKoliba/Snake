package SnakePackage;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
	
	public JFrame window;
	public static Snake snake;
	public render renderPanel;
	public Timer timer = new Timer(40,this);
	public int direction = DOWN;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public ArrayList<Point> snakeParts = new ArrayList();
	public Point head;
	public Point cherry;
	public Random location = new Random();
	public int tail = 6;
	public int score = 0;
	public boolean gameOver = false;
	public boolean pause = true;
	
	public Snake() {
		window = new JFrame("colors");
		window.setSize(900,800);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.add(renderPanel = new render());
		window.addKeyListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cherry = new Point(location.nextInt(80)/SCALE, location.nextInt(70)/SCALE);
		head = new Point(0,0);
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		timer.start();
	}
	
	public void newGame() {
		direction = DOWN;
		snakeParts.clear();
		head = new Point(0,0);
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		snakeParts.add(new Point(head.x,head.y));
		cherry = new Point(location.nextInt(80), location.nextInt(70));
		tail = 6;
		score = 0;
		gameOver = false;
		pause = true;
		timer.start();
	}
	
	public boolean tailCollision(int x, int y) {
		
		for(Point point : snakeParts) {
			if(point.x == x && point.y == y) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		renderPanel.repaint();
		
		if(!gameOver && !pause) {
			
			snakeParts.add(new Point(head.x,head.y));
			
			if(snakeParts.size() > tail) {
				snakeParts.remove(0);
			}
			
			if(direction == RIGHT) {
				if(head.x+1 > 88 || head.x+1 < 0 || tailCollision(head.x+1, head.y)) {
					gameOver = true;
				}
				else {
					head = new Point(head.x+1,head.y);
				}
			}
			else if(direction == LEFT) {
				if(head.x-1 > 88 || head.x-1 < 0 || tailCollision(head.x-1, head.y)) {
					gameOver = true;
				}
				else {
					head = new Point(head.x-1,head.y);
				}
			}
			else if(direction == UP) {
				if(head.y-1 > 75 || head.y-1 < 0 || tailCollision(head.x, head.y-1)) {
					gameOver = true;
				}
				else {
					head = new Point(head.x,head.y-1);
				}
			}
			else if(direction == DOWN) {

				if(head.y+1 > 75 || head.y+1 < 0 || tailCollision(head.x, head.y+1)) {
					gameOver = true;
				}
				else {
					head = new Point(head.x,head.y+1);
				}
			}
			
			if(snakeParts.size() > tail) {
				snakeParts.remove(0);
			}
			
			if(new Rectangle(head.x*SCALE,head.y*SCALE,15,15).intersects(new Rectangle(cherry.x*SCALE,cherry.y*SCALE,15,15))) {
				cherry = new Point(location.nextInt(80), location.nextInt(70));
				tail++;
				score++;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(i == KeyEvent.VK_RIGHT && direction != LEFT) {
			direction = RIGHT;
		}
		if(i == KeyEvent.VK_LEFT && direction != RIGHT) {
			direction = LEFT;
		}
		if(i == KeyEvent.VK_UP && direction != DOWN) {
			direction = UP;
		}
		if(i == KeyEvent.VK_DOWN && direction != UP) {
			direction = DOWN;
		}
		if(i == KeyEvent.VK_SPACE) {
			if(gameOver == true) {
				newGame();
			}
			else {
				pause = !pause;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public static void main(String[] args) {
		snake = new Snake();
	}
}
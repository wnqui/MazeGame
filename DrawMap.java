import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawMap implements ActionListener, KeyListener {

	JFrame JF;
	JP JP;
	static DrawMap drawMap;
	Maze maze = new Maze();
	Player cpu1 = new Player();
	Player cpu2 = new Player();
	Player cpu3 = new Player();
	Player p1 = new Player();

	public static final int WEDTH = 700;
	public static final int HEIGHT = 740;
	public static final int BLOCK = 40;


	public int ticks = 0;
	public boolean lost = false;
	public boolean win = false;
	public boolean onLight = false;
	AlphaComposite ac;

	public DrawMap() {

		JF = new JFrame("Maze Exercise");
		JP = new JP();
		JF.setBounds(100, 100, WEDTH, HEIGHT);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JF.setResizable(false);
		JF.setAlwaysOnTop(true);
		JF.setVisible(true);
		JF.add(JP);
		JF.addKeyListener(this);

		Timer t = new Timer(20, this);
		t.start();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;

		if (ticks % 10 == 0) {

			cpu1.forward_right(maze);
			cpu2.forward_random(maze);
			cpu3.forward_random(maze);

		}
		


		JP.repaint();

	}

	public void repaint(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WEDTH, HEIGHT);
		
		g.setColor(Color.white);
		g.setFont(new Font("",1,20));
		g.drawString("W↑   A←   S↓   D→      R reset ",50,680);
		
		
		//畫迷宮
		drawMaze(maze,g);
		
		//畫玩家
		drawPlayer( p1.y, p1.x,g);
		drawCpu1( cpu1.y, cpu1.x,g);
		drawCpu2( cpu2.y, cpu2.x,g);
		drawCpu3 (cpu3.y, cpu3.x,g);
		
		//drawLight(p1.y,p1.x,g);
		
		if(!win && (cpu1.goEnd(maze) || cpu2.goEnd(maze) || cpu3.goEnd(maze)) ) {		
			drawGameEnd(g);	
			lost = true;
		}
		if(p1.goWin(maze) && !lost) {
			drawWin(g);
			win = true;
			
		}
		
		
	}

	// 畫迷宮
	public void drawMaze(Maze maze, Graphics g) {

		for (int i = 0; i < maze.map.length; i++) {
			for (int j = 0; j < maze.map[i].length; j++) {

				if (maze.map[i][j] == 0) {
					g.setColor(Color.green);
					g.fillRect(50 + j * BLOCK, 50 + i * BLOCK, BLOCK, BLOCK);
				}
				if (maze.map[i][j] == 1) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(50 + j * BLOCK, 50 + i * BLOCK, BLOCK, BLOCK);
				}
				if (maze.map[i][j] == 5) {
					g.setColor(Color.yellow);
					g.fillRect(50 + j * BLOCK, 50 + i * BLOCK, BLOCK, BLOCK);
				}
				if (maze.map[i][j] == 7) {
					g.setColor(Color.red);
					g.fillRect(50 + j * BLOCK, 50 + i * BLOCK, BLOCK, BLOCK);
				}
			}
		}
	}

	// 畫玩家
	public void drawPlayer(int x, int y, Graphics g) {
		g.setColor(Color.red.darker());
		g.fillOval(50 + x * 40, 50 + y * 40, 40, 40);
	}

	public void drawCpu1(int x, int y, Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(50 + x * 40, 50 + y * 40, 40, 40);
	}

	public void drawCpu2(int x, int y, Graphics g) {
		g.setColor(Color.pink);
		g.fillOval(55 + x * 40, 55 + y * 40, 30, 30);
	}

	public void drawCpu3(int x, int y, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(60 + x * 40, 60 + y * 40, 20, 20);
	}
	//畫輸贏
	public void drawGameEnd(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("",1,60));
		g.drawString("Game Over", 200, 350);
	}
	public void drawWin(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("",1,60));
		g.drawString("You Win !", 200, 350);
	}
	//畫遮罩
	public void drawLight(int x ,int y, Graphics g) {
		
		if(!onLight) {
			Graphics2D g2 = (Graphics2D) g;
			ac = AlphaComposite.getInstance(AlphaComposite.CLEAR , 1);
			
			g2.setColor(Color.blue);
			g2.setComposite(ac);
			g2.fill(new Ellipse2D.Double(50+ x *40, 50+ y*40, 100, 100));
			
			g2.setColor(Color.black);
			g2.fill(new Rectangle2D.Double(50,50,600,600));
			
			
			
		    //g2.setClip(new Ellipse2D.Double(50+ x *40, 50+ y*40, 100, 100));
	    
		}
		
	}

	public static void main(String[] args) {

		drawMap = new DrawMap();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(!p1.goWin(maze)) {			
			if (e.getKeyCode() == KeyEvent.VK_W) {
				p1.goWay(p1.north, maze);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				p1.goWay(p1.east, maze);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				p1.goWay(p1.south, maze);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				p1.goWay(p1.weat, maze);
			}
		}
		//遊戲重置
		if (e.getKeyCode() == KeyEvent.VK_R) {
			p1.reSet();
			cpu1.reSet();
			cpu2.reSet();
			cpu3.reSet();
			maze = new Maze();
			win = false;
			lost = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_O) {
			
			onLight = !onLight;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

class JP extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {

		DrawMap.drawMap.repaint(g);

	}

}

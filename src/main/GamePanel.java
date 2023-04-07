package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// screen settings
	final int originalTileSize = 16; //16*16 tiles
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; //48*48 tile (character) 
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(gameThread != null) {
			// update information such as character position
			update();
			// draw the screen with the update information
			repaint();
		}
	}
	public void update() {
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		} else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		} else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		} else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		
		g2.fillRect(playerX, playerY, tileSize,tileSize);
		
		g2.dispose(); //good practice to save memories
	}
}

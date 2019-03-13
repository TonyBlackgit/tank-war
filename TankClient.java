import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TankClient extends Frame {
	public static final int GAME_HEIGHT = 600, GAME_WIDTH = 800;
	Tank myTank = new Tank(390, 550, this, true);

	Explode e = new Explode(200, 200, this);

	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	
	Wall w1 = new Wall(100,200,20,150,this),
			w2 = new Wall(300,500,150,20,this);
	
	Blood b=new Blood();
	
	Image offScreenImage = null;

	public void paint(Graphics g) {
		if(tanks.size()<=5){
			for(int i=0;i<5;i++){
				tanks.add(new Tank(70+40*(i+1),50,this,false));
			} 
		}
		g.drawString("missiles count: " + missiles.size(), 10, 50);
		g.drawString("explodes count: "+explodes.size(), 10, 70);
		g.drawString("tanks    count: "+tanks.size(), 10, 90);
		g.drawString("tanks    blood: "+myTank.getLife(), 10, 110);

		w1.draw(g);
		w2.draw(g);
		
		b.draw(g);
		
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
		}

		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.collideWithWall(w1);
			t.collideWithWall(w2);
			t.collideWithTanks(tanks);
			t.draw(g);
		}
		myTank.collideWithWall(w1);
		myTank.collideWithWall(w2);
		myTank.eat(b);
		myTank.draw(g);
	}

	public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void lunchFrame() {
		
		for(int i=0;i<10;i++){
			tanks.add(new Tank(70+40*(i+1),50,this,false));
		} 
		
		this.setTitle("TankWar");
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);

		this.addKeyListener(new KeyMonitor());
		setVisible(true);
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lunchFrame();
	}

	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

	}
}

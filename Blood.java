import java.awt.*;

public class Blood {
	private static final int WIDTH = 10, HEIGHT = 10;
	int x, y;
	int step = 0;
	private boolean live;
	
	private TankClient tc;
	
	private int[][] pos = { { 300, 250 }, { 300, 270 }, { 300, 290 }, { 300, 310 }, { 320, 310 }, { 340, 310 },
			{ 360, 310 }, { 360, 290 }, { 360, 270 }, { 360, 250 } };

	public Blood() {
		live = true;
		x = pos[0][0];
		y = pos[0][1];
	}

	public void draw(Graphics g) {
		if(!live)return;
		Color c = g.getColor();
		g.setColor(Color.pink);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		move();
	}

	private void move() {
		if(step>pos.length-1)
			step=0;
		this.x=pos[step][0];
		this.y=pos[step][1];
		step++;
		
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}

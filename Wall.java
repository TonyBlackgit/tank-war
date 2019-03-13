import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	private int x,y,width,height;
	TankClient tc;
	
	public Wall(int x, int y, int width, int height, TankClient tc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
}

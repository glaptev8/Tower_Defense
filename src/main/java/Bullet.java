import javax.swing.*;
import java.awt.*;

public class Bullet {
	private int x = -1;
	private int y = -1;
	private final String pathToBullet = "src/main/resources/bullet.png";
	private final Defender defender;
	private final int speed = 5;
	private int damage = 20;
	private Image image;

	public Bullet(int x, int y, Defender defender) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(this.pathToBullet).getImage();
		this.defender = defender;
		this.damage = defender.getDamage();
	}

	public int getX() {
		return x;
	}

	public int getDamage() {
		return damage;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public int getStartX() {
		return defender.getX();
	}

	public int getStartY() {
		return defender.getY();
	}

	public void setSpeed() {
		this.x += speed;
	}
}

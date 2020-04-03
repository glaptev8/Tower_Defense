import javax.swing.*;
import java.awt.*;

public class Bullet {
	private int x;
	private int y;
	final String pathToBullet = "src/main/resources/rocket.png";
	private final Defender defender;
	private final int speed = 5;
	final int DAMAGE;
	private Image image;

	public Bullet(int x, int y, Defender defender) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(this.pathToBullet).getImage();
		this.defender = defender;
		this.DAMAGE = defender.getDamage();
	}

	public int getX() {
		return x;
	}

	public int getDamage() {
		return DAMAGE;
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

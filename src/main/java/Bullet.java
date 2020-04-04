import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Bullet {
	private int		x;
	private int		y;
	private int		speed = 5;
	final int		DAMAGE;
	final String	pathToBullet = "rocket.png";
	private Defender defender;
	private URL iconURL = ClassLoader.getSystemResource(pathToBullet);
	private Image	image;

	public Bullet(int x, int y, Defender defender) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(this.iconURL).getImage();
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

	public void setSpeed() {
		this.x += speed;
	}
}

import javax.swing.*;
import java.awt.*;
import java.net.URL;

abstract class Defender {
	private int		x;
	private int		y;
	private int		damage;
	private int		price;
	private Image	image;
	private URL		iconURL;

	Defender(int x, int y, int damage, int price, String pathToDefender) {
		this.damage = damage;
		this.price = price;
		this.x = x;
		this.y = y;
		this.iconURL = ClassLoader.getSystemResource(pathToDefender);
		this.image = new ImageIcon(this.iconURL).getImage();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPrice() {
		return this.price;
	}

	public Image getImage() {
		return image;
	}

	public int getDamage() {
		return this.damage;
	}
}

class Defender1 extends Defender {
	Defender1(int x, int y) {
		super(x, y, 50, 100, "defender1.png");
	}
}

class Defender2 extends Defender {
	Defender2(int x, int y) {
		super(x, y, 150, 200, "defender2.png");
	}
}

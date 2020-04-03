import javax.swing.*;
import java.awt.*;

abstract class Defender {
	private int		x;
	private int		y;
	private int		DAMAGE;
	private int		PRICE;
	private Image	image;

	Defender(int x, int y, int DAMAGE, int PRICE, String pathToDefender) {
		this.DAMAGE = DAMAGE;
		this.PRICE = PRICE;
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(pathToDefender).getImage();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPrice() {
		return this.PRICE;
	}

	public Image getImage() {
		return image;
	}

	public int getDamage() {
		return this.DAMAGE;
	}
}

class Defender1 extends Defender {
	Defender1(int x, int y) {
		super(x, y, 50, 100, "src/main/resources/defender1.png");
	}
}

class Defender2 extends Defender {
	Defender2(int x, int y) {
		super(x, y, 150, 200, "src/main/resources/defender2.png");
	}
}

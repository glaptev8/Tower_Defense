import javax.swing.*;
import java.awt.*;

abstract class Defender {
	protected int x;
	protected int y;
	protected Image image;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static int getPrice() {
		return 1;
	}

	public Image getImage() {
		return image;
	}

	public int getDamage() {
		return 1;
	}
}

class Defender1 extends Defender {
	final String pathToDefender = "src/main/resources/defender1.png";
	final int DAMAGE = 50;
	final static int PRICE = 100;

	Defender1(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(this.pathToDefender).getImage();
	}

	public static int getPrice() {
		return PRICE;
	}

	public int getDamage() {
		return DAMAGE;
	}
}

class Defender2 extends Defender {
	final String pathToDefender = "src/main/resources/defender2.png";
	final int DAMAGE = 100;
	final static int PRICE = 200;

	Defender2(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(this.pathToDefender).getImage();
	}

	public static int getPrice() {
		return PRICE;
	}

	public int getDamage() {
		return DAMAGE;
	}
}

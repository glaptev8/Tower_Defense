import javax.swing.*;
import java.awt.*;

abstract class Defender {
	protected int x;
	protected int y;
	protected Image image;
	protected int damage = 20;
	protected String pathToDefender;

	public int getDamage() {
		return this.damage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
}

class Defender1 extends Defender {
	private String pathToDefender = "src/main/resources/defender.png";

	Defender1(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 10;
		this.image = new ImageIcon(this.pathToDefender).getImage();
	}
}

class Defender2 extends Defender {
	private String pathToDefender = "src/main/resources/defender.png";

	Defender2(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 100;
		this.image = new ImageIcon(this.pathToDefender).getImage();
	}
}

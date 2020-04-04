import javax.swing.*;
import java.awt.*;
import java.net.URL;

abstract class Enemy {
	private double speed;
	private int x;
	private int y;
	private int hp;
	private int money;
	private Image image;
	private URL iconURL;

	Enemy (double speed, Cell startCell, int hp, int price, String pathToEnemy) {
		this.speed = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.hp = hp;
		this.money = price;
		this.iconURL = ClassLoader.getSystemResource(pathToEnemy);
		this.image = new ImageIcon(this.iconURL).getImage();
	}

	public Image getImage() {
		return image;
	}

	public void setHp(int hp) {
		this.hp -= hp;
	}

	public void setX() {
		this.x -= speed;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMoney() {
		return money;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

class Enemy1 extends Enemy{
	public Enemy1(double speed, Cell startCell) {
		super(speed, startCell, 200, 100, "shipNLO.png");
	}
}

class Enemy2 extends Enemy{
	public Enemy2(double speed, Cell startCell) {
		super(speed, startCell, 100, 50, "enemy.png");
	}
}

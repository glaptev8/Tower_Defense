import javax.swing.*;
import java.awt.*;

abstract class Enemy {
	private double speed;
	protected int x;
	protected int y;
	protected int hp;
	protected int money;
	protected Image image;

	Enemy (double speed, Cell startCell, int hp, int price, String pathToEnemy) {
		this.speed = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.hp = hp;
		this.money = price;
		this.image = new ImageIcon(pathToEnemy).getImage();
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
		super(speed, startCell, 200, 100, "src/main/resources/shipNLO.png");
	}
}

class Enemy2 extends Enemy{
	public Enemy2(double speed, Cell startCell) {
		super(speed, startCell, 100, 50, "src/main/resources/enemy.png");
	}
}

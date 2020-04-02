import javax.swing.*;
import java.awt.*;

abstract class Enemy {
	protected int x;
	protected int y;
	protected float speed;
	protected int hp;
	protected int money;
	protected Image image;

	public Image getImage() {
		return image;
	}

	public void setHp(int hp) {
		this.hp -= hp;
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

	public void setX() {
		this.x -= this.speed;
	}

	public int getY() {
		return y;
	}
}

class Enemy1 extends Enemy{
	private final String pathToEnemy = "src/main/resources/shipNLO.png";

	public Enemy1(int speed, Cell startCell) {
		this.speed = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.hp = 100;
		this.money = 50;
		this.image = new ImageIcon(this.pathToEnemy).getImage();
	}
}

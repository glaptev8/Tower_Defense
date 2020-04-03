import javax.swing.*;
import java.awt.*;

abstract class Enemy {
	protected int x;
	protected int y;
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

	public int getY() {
		return y;
	}

	public void setX() {
	}
}

class Enemy1 extends Enemy{
	private final String pathToEnemy = "src/main/resources/shipNLO.png";
	protected double speed_enemy;
	public Enemy1(double speed, Cell startCell) {
		this.speed_enemy = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.hp = 150;
		this.money = 50;
		this.image = new ImageIcon(this.pathToEnemy).getImage();
	}

	public void setX() {
		this.x -= speed_enemy;
	}
}

class Enemy2 extends Enemy{
	private final String pathToEnemy = "src/main/resources/enemy.png";
	protected double speed_enemy;
	public Enemy2(double speed, Cell startCell) {
		this.speed_enemy = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.hp = 100;
		this.money = 50;
		this.image = new ImageIcon(this.pathToEnemy).getImage();
	}

	public void setX() {
		this.x -= speed_enemy;
	}
}

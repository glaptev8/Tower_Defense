import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy {
	private int x;
	private int y;
	private float speed;
	private int hp = 100;
	private final String pathToEnemy = "src/main/resources/enemy.png";
	private Image image;
	Timer t;

	public Enemy(int speed, Cell startCell) {
		this.speed = speed;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.image = new ImageIcon(this.pathToEnemy).getImage();
	}

	public Image getImage() {
		return image;
	}

	public void setHp(int hp) {
		this.hp -= hp;
	}

	public int getHp() {
		return this.hp;
	}

	public int getX() {
		return x;
	}

	public void setX() {
		this.x -= this.speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
}

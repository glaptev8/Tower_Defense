import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy {
	private int x;
	private int y;
	private int width;
	private int height;
	private float speed;
	private Cell startCell;
	private final String pathToEnemy = "src/main/resources/enemy.png";
	private Image image;
	Timer t;

	public Enemy(int width, int height, int speed, Cell startCell) {
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.startCell = startCell;
		this.x = startCell.getX();
		this.y = startCell.getY();
		this.image = new ImageIcon(this.pathToEnemy).getImage();
	}

	public Image getImage() {
		return image;
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

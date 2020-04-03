import javax.swing.*;
import java.awt.*;

class Cell {
	private boolean			defender;
	private int				x;
	private int				y;
	private Image			image;
	private final String	pathToGrass = "src/main/resources/grass.png";
	private final String	pathToWater = "src/main/resources/water.png";
	private final String	pathToMud = "src/main/resources/mud.png";

	public Cell(int x, int y, String type) {
		this.x = x;
		this.y = y;
		switch (type) {
			case ("grass"):
				this.image = new ImageIcon(pathToGrass).getImage();
				break;
			case ("water"):
				this.image = new ImageIcon(pathToWater).getImage();
				break;
			case ("mud"):
				this.image = new ImageIcon(pathToMud).getImage();
				break;
			default:
				break;
		}
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

	public void setDefender(boolean defender) {
		this.defender = defender;
	}

	public boolean getDefender() {
		return defender;
	}
}
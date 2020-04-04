import javax.swing.*;
import java.awt.*;
import java.net.URL;

class Cell {
	private boolean			defender;
	private int				x;
	private int				y;
	private Image			image;
	private final String	pathToGrass = "grass.png";
	private final String	pathToWater = "water.png";
	private final String	pathToMud = "mud.png";
	private URL				iconURL;

	public Cell(int x, int y, String type) {
		this.x = x;
		this.y = y;
		switch (type) {
			case ("grass"):
				this.iconURL = ClassLoader.getSystemResource(pathToGrass);
				break;
			case ("water"):
				this.iconURL = ClassLoader.getSystemResource(pathToWater);
				break;
			case ("mud"):
				this.iconURL = ClassLoader.getSystemResource(pathToMud);
				break;
			default:
				break;
		}
		if (iconURL != null)
			this.image = new ImageIcon(this.iconURL).getImage();
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
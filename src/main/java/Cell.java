import javax.swing.*;
import java.awt.*;

class Cell {
	private int				x;
	private int				y;
	private String			type;
	private Image image;
	private final String	pathToGrass = "src/main/resources/grass.png";
	private final String	pathToWater = "src/main/resources/water.png";
	private final String	pathToMud = "src/main/resources/mud.png";

	public Cell(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
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
//		if ("grass".equals(type))
//			this.image = new ImageIcon(pathToGrass).getImage();
//		else if ("water".equals(type))
//			this.image = new ImageIcon(pathToWater).getImage();
//		else if ("mud".equals(type))
//			this.image = new ImageIcon(pathToMud).getImage();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
import sun.lwawt.macosx.CWarningWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class Game extends JPanel implements ActionListener {

	private Image		grass;
	private Cell		cell;
	private final int	SIZE_CELL = 64;
	private Map			map;
	int x = 0;
	int y = 0;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Cell[][] cell = map.getMap();

		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				g.drawImage(cell[i][j].getImage(), cell[i][j].getX(), cell[i][j].getY(), null);
			}
		}
	}

	Game() throws InterruptedException {
		map = new Map();
		init();
//		cell = new Cell(0, 0, TypeCell.Grass.type);
//		cell = new Cell(0, 0, TypeCell.Grass.type);
	}

	public void init() {
		Timer t = new Timer(250, this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}

class Map {
	Cell[][] map;

	Map() {
		map = new Cell[20][14];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Cell(i * 64, j * 64, TypeCell.Grass.type);
			}
		}
	}

	public Cell[][] getMap() {
		return map;
	}

	public void setCell(int x, int y, Cell cell) {
		this.map[x][y] = cell;
	}
}

class Cell {
	private int				x;
	private int				y;
	private String			type;
	private Image			image;
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